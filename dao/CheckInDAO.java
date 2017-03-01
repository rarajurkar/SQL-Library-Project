package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import to.CheckInTO;

public class CheckInDAO {

	public void checkIn(CheckInTO cito) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inside check in dao");
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

	}

}
