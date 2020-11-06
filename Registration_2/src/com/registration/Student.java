package com.registration;

public class Student {

	private int id;
	private String surname;
	private String firstname;
	private GenderCategory genderCat;
	private String date;
	private String email;
	private String country;

	public Student(int id,String surname, String firstname, GenderCategory genderCat,  String date, String email, String country) {
		
		this.id = id;
		this.surname = surname;
		this.firstname = firstname;
		this.genderCat = genderCat;
		this.date = date;
		this.email = email;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public GenderCategory getGenderCat() {
		return genderCat;
	}

	public void setGenderCat(GenderCategory genderCat) {
		this.genderCat = genderCat;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
