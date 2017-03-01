package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import to.FineInsert;
import to.FineResultsTO;

public class FineDAO {

	public void calculateFine() {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/library";
		String username = "root";
		String password = "rucha771992";
		System.out.println("below forname..");
		ResultSet rs1;
		//Map<Integer,Double> m= new HashMap<Integer,Double>();
		List<FineInsert> l = new ArrayList<FineInsert>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			String getDataSql = "select loan_id,((Date_in-Due_date) * 0.25) as Fine_amt from book_loans where Date_in-Due_date > 0";
			rs1 = stmt.executeQuery(getDataSql);
			while(rs1.next()){
				FineInsert fi = new FineInsert();
				fi.setLoan_id(Integer.parseInt(rs1.getString("loan_id")));
				fi.setFine_amt(Double.parseDouble(rs1.getString("Fine_amt")));
				l.add(fi);
			}
			System.out.println(l.size());
			for(FineInsert f : l ){
				String sql = "insert into Fines values("+ f.getLoan_id() +","+ f.getFine_amt()+","+"0)";
				stmt.executeUpdate(sql);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void updateFine() {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/library";
		String username = "root";
		String password = "rucha771992";
		System.out.println("below forname..");
		ResultSet rs1;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
