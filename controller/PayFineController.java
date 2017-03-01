package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PayFineController
 */
@WebServlet("/PayFineController")
public class PayFineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayFineController() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String borrowerId = request.getParameter("borrower_id");
		String url = "jdbc:mysql://localhost:3306/library";
		String username = "root";
		String password = "rucha771992";
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
			String val = "UPDATE Fines SET  paid = 1 WHERE loan_id IN(select loan_id from book_loans where card_no='ID000406')";
            stmt.executeUpdate(val);
            String message = "Successfully paid..";
    		request.setAttribute("message", message);
    		request.getRequestDispatcher("Success.jsp").forward(request, response);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
