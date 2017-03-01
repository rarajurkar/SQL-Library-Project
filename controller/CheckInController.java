package controller;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dao.CheckInDAO;
import dao.SearchInputDAO;
import to.CheckInTO;
import to.SearchInputTO;

/**
 * Servlet implementation class CheckInController
 */
@WebServlet("/CheckInController")
public class CheckInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckInController() {
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
		System.out.println("inside check in controller..");
		CheckInTO cito= new CheckInTO();
		cito.setIsbn(request.getParameter("isbn"));
		cito.setCardNo(request.getParameter("cno"));
		cito.setbName(request.getParameter("bName"));
		
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
			String cno=cito.getCardNo();//cardno.getText();
            String bkid=cito.getIsbn();//bookid.getText();
            String brid=cito.getbName();//Borrower_Name.getText();
           
            //String outdate=dateout.getText();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7;
            List<CheckInTO> list = new ArrayList<CheckInTO>(); 
           if("".equals(cno) && "".equals(bkid))
            {
             String bname =cito.getbName();//Borrower_Name.getText();
             String val= "select temp.isbn as Book_Id,temp1.Fname as Borrower_name, temp.Card_no as Card_Number from BOOK_LOANS as temp, BORROWER as temp1 where temp1.Fname like '%"+bname+"%' AND temp1.Card_no = temp.Card_no";
             
             rs=stmt.executeQuery(val);
             //String book_id = rs.getString("Book_Id");
            
             while(rs.next()){
                CheckInTO cTOout = new CheckInTO();
                cTOout.setIsbn(rs.getString("Book_Id"));
                cTOout.setbName(rs.getString("Borrower_name"));
                cTOout.setCardNo(rs.getString("Card_Number"));
                list.add(cTOout);
                //model.insertRow(model.getRowCount(), new Object[]{,,} );
            }
            } 
            else if("".equals(cno) && "".equals(brid)){
                //bookid.getText();
                String val6= "select temp.isbn as book_id,temp1.Fname as Borrower_name, temp.Card_no as Card_Number from BOOK_LOANS as temp, BORROWER as temp1 where temp.isbn like '%"+bkid+"%' AND temp1.Card_no = temp.Card_no";
                rs5=stmt.executeQuery(val6);
                
            while(rs5.next()){
            	CheckInTO cTOout = new CheckInTO();
                cTOout.setIsbn(rs5.getString("Book_Id"));
                cTOout.setbName(rs5.getString("Borrower_name"));
                cTOout.setCardNo(rs5.getString("Card_Number"));
                list.add(cTOout);
 
            	// model.insertRow(model.getRowCount(), 
                //new Object[]{rs5.getString("Book_Id"),rs5.getString("Borrower_name"),rs5.getString("Card_Number")} );
                
            }
            
            }
            else if("".equals(bkid) && "".equals(brid)){
               // cardno.getText();
                String val7= "select temp.isbn as book_id,temp1.Fname as Borrower_name, temp.Card_no as Card_Number from BOOK_LOANS as temp, BORROWER as temp1 where temp.Card_no like '%"+cno+"%' AND temp1.Card_no = temp.Card_no";
                rs6=stmt.executeQuery(val7);
                
            while(rs6.next()){
            	CheckInTO cTOout = new CheckInTO();
                cTOout.setIsbn(rs6.getString("Book_Id"));
                cTOout.setbName(rs6.getString("Borrower_name"));
                cTOout.setCardNo(rs6.getString("Card_Number"));
                list.add(cTOout);
 
            	// model.insertRow(model.getRowCount(), new Object[]{rs6.getString("Book_Id"),rs6.getString("Borrower_name"),rs6.getString("Card_Number")} );
                
            }
            }
            else if("".equals(bkid)){
                //Borrower_Name.getText();
                //cardno.getText();
                String val4="select temp.isbn as book_id,temp1.Fname as Borrower_name, temp.Card_no as Card_Number from BOOK_LOANS as temp, BORROWER as temp1 where temp1.Fname like '%"+brid+"%' AND temp.Card_no like '%"+cno+"%' AND temp1.Card_no = temp.Card_no";
                rs1=stmt.executeQuery(val4);
                while(rs1.next())
                {
                	CheckInTO cTOout = new CheckInTO();
                    cTOout.setIsbn(rs1.getString("Book_Id"));
                    cTOout.setbName(rs1.getString("Borrower_name"));
                    cTOout.setCardNo(rs1.getString("Card_Number"));
                    list.add(cTOout);
     
                	// model.insertRow(model.getRowCount(), new Object[]{rs1.getString("Book_Id"),rs1.getString("Borrower_name"),rs1.getString("Card_Number")} );
                
                }
             }
            else if("".equals(brid)){
                 //cardno.getText();
                 //bookid.getText();
                 String val3="select temp.isbn as book_id,temp1.Fname as Borrower_name, temp.Card_no as Card_Number from BOOK_LOANS as temp, BORROWER as temp1 where temp.isbn like '%"+bkid+"%' AND temp.Card_no like '%"+cno+"%' AND temp1.Card_no = temp.Card_no AND Date_in = null";
                rs2=stmt.executeQuery(val3);
                
                while(rs2.next())
                {
                	CheckInTO cTOout = new CheckInTO();
                    cTOout.setIsbn(rs2.getString("Book_Id"));
                    cTOout.setbName(rs2.getString("Borrower_name"));
                    cTOout.setCardNo(rs2.getString("Card_Number"));
                    list.add(cTOout);
     
                	//model.insertRow(model.getRowCount(), new Object[]{rs2.getString("Book_Id"),rs2.getString("Borrower_name"),rs2.getString("Card_Number")} );
                
                }
            }
            else if("".equals(cno)){
                //Borrower_Name.getText();
                //bookid.getText();
                String val2="select temp.isbn as book_id,temp1.Fname as Borrower_name, temp.Card_no as Card_Number from BOOK_LOANS as temp, BORROWER as temp1 where BOOK_LOANS.isbn like '%"+bkid+"%' AND BORROWER.Fname like '%"+brid+"%' AND BORROWER.Card_no = BOOK_LOANS.Card_no"; 
                rs3=stmt.executeQuery(val2);
                while(rs3.next())
                {
                	CheckInTO cTOout = new CheckInTO();
                    cTOout.setIsbn(rs3.getString("Book_Id"));
                    cTOout.setbName(rs3.getString("Borrower_name"));
                    cTOout.setCardNo(rs3.getString("Card_Number"));
                    list.add(cTOout);
       
                	//model.insertRow(model.getRowCount(), new Object[]{rs3.getString("Book_Id"),rs3.getString("Borrower_name"),rs3.getString("Card_Number")} );
                 
                }
            }
              else
              {
                 //Borrower_Name.getText();
                 //cardno.getText();
                 //bookid.getText();
                String val5="select temp.isbn as book_id,temp1.Fname as Borrower_name, temp.Card_no as Card_Number from BOOK_LOANS as temp, BORROWER as temp1 where temp.isbn like '%"+bkid+"%' AND temp.Card_no like '%"+cno+"%' AND temp1.Fname like '%"+brid+"%'  AND temp1.Card_no = temp.Card_no";
                rs4=stmt.executeQuery(val5);
                
                while(rs4.next()){
                	CheckInTO cTOout = new CheckInTO();
                    cTOout.setIsbn(rs4.getString("Book_Id"));
                    cTOout.setbName(rs4.getString("Borrower_name"));
                    cTOout.setCardNo(rs4.getString("Card_Number"));
                    list.add(cTOout);
     
                	// model.insertRow(model.getRowCount(), new Object[]{rs4.getString("Book_Id"),rs4.getString("Borrower_name"),rs4.getString("Card_Number")} );
                      
              }
              }
              request.setAttribute("searchList", list);
   		      request.getRequestDispatcher("DisplayCheckIn.jsp").forward(request, response);
   		      connection.close(); 
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
