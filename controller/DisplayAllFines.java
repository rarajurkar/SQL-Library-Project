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

import to.FineResultsTO;
import to.SearchOutputTO;

/**
 * Servlet implementation class DisplayAllFines
 */
@WebServlet("/DisplayAllFines")
public class DisplayAllFines extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllFines() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String url = "jdbc:mysql://localhost:3306/library";
		String username = "root";
		String password = "rucha771992";
		System.out.println("below forname..");
		List<FineResultsTO> searchList = new ArrayList<FineResultsTO>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt=conn.createStatement();
			
			String sql ="select Card_no, isbn, fines.loan_id as loan_id, Fine_amt, paid from book_loans, fines where fines.Loan_id=book_loans.loan_id AND paid=0";
		    ResultSet rs = stmt.executeQuery(sql);   
			while(rs.next()){
				FineResultsTO soto= new  FineResultsTO();
		           soto.setCardNo(rs.getString("Card_no"));
		           System.out.println(soto.getCardNo());
		           soto.setBookId(rs.getString("Isbn"));
		           System.out.println(soto.getBookId());
		           soto.setLoanId(rs.getString("loan_id"));
		           System.out.println(soto.getLoanId());
		           soto.setFineAmt(rs.getDouble("Fine_amt"));
		           System.out.println(soto.getFineAmt());
		           soto.setPaid(rs.getShort("paid"));		           
		           searchList.add(soto);
		     }  
		     conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("searchList", searchList);
		request.getRequestDispatcher("DisplayAllFines.jsp").forward(request, response);
	}

}
