package controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dao.CheckInDAO;
import dao.SearchInputDAO;
import to.CheckInTO;
import to.CheckOutTO;
import to.SearchInputTO;

/**
 * Servlet implementation class CheckOutController
 */
@WebServlet("/CheckOutController")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutController() {
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
		System.out.println("inside check out controller..");
		CheckOutTO coto= new CheckOutTO();
		coto.setIsbn(request.getParameter("isbn"));
		coto.setBranchId(Integer.parseInt(request.getParameter("branchId")));//(request.getParameter("cno"));
		coto.setCardNo(request.getParameter("cno"));
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
		
		Statement stmt = connection.createStatement();
        String cno=coto.getCardNo();//cardno.getText();
        String bkid=coto.getIsbn();//bookid.getText();
        //String brid=;//branchid.getText();
        int b=coto.getBranchId();//Integer.parseInt(brid);
        //String outdate=bid.getText();
        String output;
        String output2;
        int bc = 0;
        String book_count="select count(*) from Book_loans where Card_no='"+cno+"';";
        ResultSet rs, rs1, rs2;
        String n1 = null;
        String n2=null;
        String n3=null;
        rs=stmt.executeQuery(book_count);
        while(rs.next()){
        n1=rs.getString("count(*)");
        }   
        int count = Integer.parseInt(n1); //counted number of books borrowed
        String borrowed_books = "select count(*) from Book_loans where Isbn='"+bkid+"';";
        rs1=stmt.executeQuery(borrowed_books);
        while(rs1.next()){
        	n2=rs1.getString("count(*)");
        } 
        int borrowed = Integer.parseInt(n2);
        String available_books = "select no_of_copies from Book_copies where book_id='"+bkid+"' and branch_id ="+ b +";";
        rs2=stmt.executeQuery(available_books);
        while(rs2.next()){
        	n3=rs2.getString("no_of_copies");
        } 
        int available = Integer.parseInt(n3);
        if(available <= borrowed){
        	String st="Book not available";
            request.setAttribute("message", st);
    		request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
        else if(count<3){
	        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date outdate = new Date();
			Date out = new Date();
			//Date date = parser.parse(new Date());
			Date duedate=addDays(outdate,14);
			System.out.println(outdate +" " + duedate);
			output=formatter.format(duedate);
			output2 = parser.format(out);
			String add= "insert into Book_Loans(isbn,branch_id,card_no,date_out,due_date) values('"+bkid+"',"+b+",'"+cno+"','"+output2+"','"+output+"');";
			stmt.executeUpdate(add);
			//PreparedStatement ps = connection.prepareStatement(add);
			//int n = ps.executeUpdate();
			System.out.println("Inserted correctly..");
			String message = "Successfully Checked Out..";
			request.setAttribute("message", message);
			request.getRequestDispatcher("Success.jsp").forward(request, response);
		    }
        else{
            String st="Cannot loan books beyond limit";
            request.setAttribute("message", st);
    		request.getRequestDispatcher("Error.jsp").forward(request, response);
                
    }
        connection.close();
		//CheckOutDAO cdao = new CheckOutDAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Date addDays(Date d,int days)
	{
	d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
	return d;
	}

}
