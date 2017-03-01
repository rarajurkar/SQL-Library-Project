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

import dao.BorrowerManagementDAO;
import to.BorrowerManagementTO;

/**
 * Servlet implementation class BorrowerManagementController
 */
@WebServlet("/BorrowerManagementController")
public class BorrowerManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside borrower controller..");
		BorrowerManagementTO bmto= new BorrowerManagementTO();
		bmto.setfName(request.getParameter("fname"));
		bmto.setlName(request.getParameter("lname"));
		bmto.setSsn(request.getParameter("ssn"));
		bmto.setAddress(request.getParameter("address"));
		bmto.setPhone(request.getParameter("phone"));
		System.out.println(bmto.getfName()+" "+bmto.getlName()+" "+bmto.getAddress());
		
		//check if ssn already there
		
		String url = "jdbc:mysql://localhost:3306/library";
		String username = "root";
		String password = "rucha771992";
		System.out.println("below forname..");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			String sql = "select count(*) as no from borrower where ssn = '" + bmto.getSsn().trim()+"'";
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			int number=0;
			while(rs.next()){
				number = Integer.parseInt(rs.getString("no"));
			}
			if(number > 0){
				String message = "SSN already exists..";
				request.setAttribute("message", message);
				request.getRequestDispatcher("Error.jsp").forward(request, response);

			}
			else{
				BorrowerManagementDAO bmdao = new BorrowerManagementDAO();
				boolean sf = bmdao.insertBorrower(bmto);
				if(sf){
					String message = "Borrower added successfully..";
					request.setAttribute("message", message);
					request.getRequestDispatcher("Success.jsp").forward(request, response);
				}
				else{
					String message = "Error..";
					request.setAttribute("message", message);
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
