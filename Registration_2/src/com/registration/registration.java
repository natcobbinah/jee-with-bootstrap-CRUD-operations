package com.registration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registration
 */
@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append(request.getParameter("name"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get button pressed values
		String insert = request.getParameter("insert");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
		String show = request.getParameter("show");

		String useridfrom_form = request.getParameter("id");
		String surname = request.getParameter("surname");
		String firstname = request.getParameter("firstname");
		String gender = "";
		if (request.getParameter("gender") != null) {
			if (request.getParameter("gender").equals("Male")) {
				gender = "Male";
			}
			if (request.getParameter("gender").equals("Female")) {
				gender = "Female";
			}
		}
		String date = request.getParameter("dateofbirth");
		String email = request.getParameter("email");
		String country = request.getParameter("country");

		// instantiate database class
		Database db = null;
		try {
			db = new Database();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// connect to databases
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// store form parameters to Student Bean
		GenderCategory genderCat = null;
		if (gender.equalsIgnoreCase("Male")) {
			genderCat = genderCat.MALE;
		} else if (gender.equalsIgnoreCase("Female")) {
			genderCat = genderCat.FEMALE;
		}
		
		//store retrieved user ids and display on screen
		List<String> ids = new LinkedList<>();

		int id = RandomIdGenerator.getId();
		Student student = new Student(id, surname, firstname, genderCat, date, email, country);
		db.addStudent(student);

		// insert student record to database
		if (insert != null) {
			try {
				db.insertStudent();
			} catch (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
			}
			System.out.println("Insert button pressed");
		}

		// update student record to database
		if (update != null) {
			try {
				int idvalue = Integer.parseInt(useridfrom_form);
				db.updateStudent(idvalue, surname, firstname, genderCat, date, email, country);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Update button pressed");
		}

		// delete student record to database
		if (delete != null) {
			try {
				int idvalue = Integer.parseInt(useridfrom_form);
				db.deleteStudent(idvalue);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Delete  button pressed");
		}

		// show all student records
		if (show != null) {
			try {
				db.loadStudents();
			
				for(Student s : db.getStudent()) {
					System.out.println(s.getId());
					ids.add("IDs" + Integer.toString(s.getId()) + " " +  s.getSurname() + " " + s.getFirstname() + " " + s.getGenderCat() + " " + s.getDate() + " " + s.getDate() + " " + s.getEmail() + " " + s.getCountry());
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Show Students button pressed");
		}

		// disconnect from database
		try {
			db.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(surname + firstname + gender + date + email + country);

		request.setAttribute("ids", ids);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

	}

}
