package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FineDAO;
import to.FineResultsTO;

/**
 * Servlet implementation class FineController
 */
@WebServlet("/FineController")
public class FineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FineController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String borrowerId = request.getParameter("borrower_id");
		FineDAO fDao = new FineDAO();
		fDao.updateFine();
		//fDao.calculateFine();
		String url = "jdbc:mysql://localhost:3306/library";
		String username = "root";
		String password = "rucha771992";
		System.out.println("below forname..");
		List<FineResultsTO> list = new ArrayList<FineResultsTO>();
		ResultSet rs;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			String sql = "select fines.loan_id, paid, fine_amt from fines, book_loans where fines.loan_id = book_loans.loan_id and card_no = '" + borrowerId +"'";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				FineResultsTO f = new FineResultsTO();
				f.setLoanId(rs.getString("loan_id"));
				f.setPaid(rs.getInt("paid"));
				f.setFineAmt(rs.getDouble("fine_amt"));
				list.add(f);
			}
			request.setAttribute("searchList", list);
			request.setAttribute("id", borrowerId);
			request.getRequestDispatcher("FineResult.jsp").forward(request, response);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
