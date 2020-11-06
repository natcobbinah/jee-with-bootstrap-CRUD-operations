package com.registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDerbydatabase {

	public static void main(String[] args) {

		try {
			// load the driver for an embedded Derby database
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Database driver not found!");
		}

		getConnection();

		disConnect();
	}

	private static Connection getConnection() {

		Connection conn = null;
		try {

			// if necessary, set the home directory for Derby

			// create and return the connection.
			String dbUrl = "jdbc:derby:StudentDB;create=true";
			String username = "";
			String password = "";
			conn = DriverManager.getConnection(dbUrl, username, password);

			// create table successful
			/*
			 * Statement statement = conn.createStatement();
			 * 
			 * String createQuery = "create table Students(" + "StudentId int primary key,"
			 * + "Surname    varchar(64)," + "Firstname  varchar(64)," +
			 * "Gender     varchar(64)," + "Date       varchar(64)," +
			 * "Email      varchar(64)," + "Country    varchar(15))";
			 * 
			 * statement.executeUpdate(createQuery);
			 * System.out.println("create table successful");
			 */
			// end creating table

			// insert to table Students Successful

			/*
			 * String insertSql =
			 * "insert into Students(StudentId, Surname, Firstname, Gender, Date, Email, Country)"
			 * + "values(?,?,?,?,?,?,?)"; PreparedStatement insertStatement =
			 * conn.prepareStatement(insertSql); insertStatement.setInt(1, 3);
			 * insertStatement.setString(2, "Cobbinah"); insertStatement.setString(3,
			 * "Nathaniel"); insertStatement.setString(4, "Male");
			 * insertStatement.setString(5, "12-12-2005"); insertStatement.setString(6,
			 * "heck@gmail.com"); insertStatement.setString(7, "Ghana");
			 * 
			 * insertStatement.executeUpdate(); insertStatement.close();
			 * System.out.println("Insert student records to table successful");
			 */

			// end inserting into table

			// selecting from students table successful

			String selectSQL = "select StudentId, Surname, Firstname, Gender, Date, Email, Country  from Students";
			Statement selectStatement = conn.createStatement();

			ResultSet results = selectStatement.executeQuery(selectSQL);

			while (results.next()) {
				int id = results.getInt("StudentId");
				String surname = results.getString("Surname");
				String firstname = results.getString("Firstname");
				String gender = results.getString("Gender");
				String date = results.getString("Date");
				String email = results.getString("Email");
				String country = results.getString("Country");

				System.out.println(id + " : " + surname + " : " + firstname + " : " + gender + " : " + date + " : "
						+ email + " : " + country);
			}

			// end selecting from table

			// deletion from table successful
			/*
			 * String deleteSQL = "delete from Students where StudentId=?";
			 * PreparedStatement deleteStatement = conn.prepareStatement(deleteSQL);
			 * 
			 * int id = 2; deleteStatement.setInt(1, id); deleteStatement.executeUpdate();
			 * 
			 * deleteStatement.close();
			 * 
			 * System.out.println("deletion successful");
			 */

			// end deletion from table

		} catch (SQLException e) {
			for (Throwable t : e) {
				e.printStackTrace();
				return null;
			}
		}
		return conn;
	}

	public static boolean disConnect() {

		try {

			// on a successful shutdown, this throws an exception
			String shutdownURL = "jdbc:derby:;shutdown=true";
			DriverManager.getConnection(shutdownURL);

		} catch (SQLException e) {
			if (e.getMessage().equals("Derby system shutdown"))
				return true;
		}
		return false;
	}

}
