package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import to.BorrowerManagementTO;

public class BorrowerManagementDAO {

	public boolean insertBorrower(BorrowerManagementTO bmto) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("inside BorrowerManagement DAO...");
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
		Connection connection = DriverManager.getConnection(url, username, password);
		String getIdSql = "select card_no from borrower order by card_no desc limit 1";
		Statement stmt=connection.createStatement();
		ResultSet rs1 = stmt.executeQuery(getIdSql);
		String lastId = null;
		while(rs1.next()){
			lastId = rs1.getString(1);//rs1.getString("card_no")); 
		}//logic to get the card number 
		int lastNo = Integer.parseInt(lastId.substring(3));
		lastNo++;
		String newId ="ID"+String.format("%06d", lastNo); 
		String sql = "insert into BORROWER values('"+ newId +"',?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, bmto.getSsn());
		ps.setString(2, bmto.getfName());
		ps.setString(3, bmto.getlName());
		ps.setString(4, bmto.getAddress());
		ps.setString(5, bmto.getPhone());
		int n = ps.executeUpdate();
		boolean sf;
		if(n==1){
			System.out.println("Inserted correctly..");
			sf= true;
		} else{
			System.out.println("");
			sf= false;
		}
		return sf;
	}

}
