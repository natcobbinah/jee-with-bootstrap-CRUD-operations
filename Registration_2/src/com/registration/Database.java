package com.registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Database {

	private List<Student> students;

	public Database() throws Exception {
		students = new LinkedList<Student>();
	}

	private Connection con;

	public void connect() throws Exception {
		if (con != null)
			return;
		// when using derby database driver
		/*
		 * try { // load the driver for an embedded Derby database
		 * Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); } catch
		 * (ClassNotFoundException e) {
		 * System.err.println("Database driver not found!"); }
		 * 
		 * //for creating the database for the first time //String dbUrl =
		 * "jdbc:derby:StudentDB;create=true";
		 * 
		 * String dbUrl = "jdbc:derby:StudentDB"; String username = ""; String password
		 * = ""; con = DriverManager.getConnection(dbUrl, username, password);
		 * //System.out.println("Database created successfully");
		 * System.out.println("User credentials accepted to database successful");
		 */

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String username="root";
		String password="";
		String url = "jdbc:mysql://localhost:3306/StudentDB";

		con = DriverManager.getConnection(url, username, password);
	}

	public void disconnect() throws Exception {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}

		}
	}

	/*
	 * //when using derby database public void createTable() throws SQLException {
	 * Statement statement = con.createStatement();
	 * 
	 * String createQuery = "create table Students(" + "StudentId int primary key,"
	 * + "Surname    varchar(64)," + "Firstname  varchar(64)," +
	 * "Gender     varchar(64)," + "Date       varchar(64)," +
	 * "Email      varchar(64)," + "Country    varchar(15))";
	 * 
	 * statement.executeUpdate(createQuery);
	 * System.out.println("create table successful"); }
	 */

	public void insertStudent() throws SQLException {
		// String checkSql = "select count(*) as count from Students where StudentId
		// =?";
		// PreparedStatement checkStmt = con.prepareStatement(checkSql);

		String insertSql = "insert into Students(StudentId, Surname, Firstname, Gender, DateofBirth, Email, Country)"
				+ "values(?,?,?,?,?,?,?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);

		for (Student student : students) {
			int id = student.getId();
			String surname = student.getSurname();
			String firstname = student.getFirstname();
			GenderCategory gender = student.getGenderCat();
			String date = student.getDate();
			String email = student.getEmail();
			String country = student.getCountry();

			// checkStmt.setInt(1, id);
			// ResultSet checkResult = checkStmt.executeQuery();
			// checkResult.next();

			// int count = checkResult.getInt(1);

			// if (count == 0) {
			// insert into database
			System.out.println("inserting to database");
			int col = 1;
			insertStatement.setInt(col++, id);
			insertStatement.setString(col++, surname);
			insertStatement.setString(col++, firstname);
			insertStatement.setString(col++, gender.name());
			insertStatement.setString(col++, date);
			insertStatement.setString(col++, email);
			insertStatement.setString(col++, country);

			insertStatement.executeUpdate();

			System.out.println("Student insertion successful");
		}
		insertStatement.close();
	}

	public void updateStudent(int id, String surname, String firstname, GenderCategory genderCat, String date,
			String email, String country) throws SQLException {

		String updateSql = "update Students set Surname=?, Firstname=?, Gender=?, DateofBirth=?, Email=?, Country=? where StudentId=?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);

		int col = 1;
		updateStatement.setString(col++, surname);
		updateStatement.setString(col++, firstname);
		updateStatement.setString(col++, genderCat.name());
		updateStatement.setString(col++, date);
		updateStatement.setString(col++, email);
		updateStatement.setString(col++, country);
		updateStatement.setInt(col++, id);

		updateStatement.executeUpdate();
		updateStatement.close();

		System.out.println("update successful");
	}

	public void deleteStudent(int index) throws SQLException {
		//students.remove(index);

		String deleteSQL = "delete from Students where StudentId=?";
		PreparedStatement deleteStatement = con.prepareStatement(deleteSQL);

		int id = index;
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();

		deleteStatement.close();

		System.out.println("deletion successful");
	}

	public void loadStudents() throws SQLException {
		students.clear();

		String selectSQL = "select StudentId, Surname, Firstname, Gender, DateofBirth, Email, Country  from Students";
		Statement selectStatement = con.createStatement();

		ResultSet results = selectStatement.executeQuery(selectSQL);

		while (results.next()) {
			int id = results.getInt("StudentId");
			String surname = results.getString("Surname");
			String firstname = results.getString("Firstname");
			String gender = results.getString("Gender");
			String date = results.getString("DateofBirth");
			String email = results.getString("Email");
			String country = results.getString("Country");

			Student student = new Student(id, surname, firstname, GenderCategory.valueOf(gender), date, email, country);
			students.add(student);
		}
		System.out.println("loading students successful");
	}

	public List<Student> getStudent() {
		return students;
	}

	public void addStudent(Student student) {
		students.add(student);
	}

}
