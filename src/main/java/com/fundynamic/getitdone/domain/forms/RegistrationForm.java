package com.fundynamic.getitdone.domain.forms;

public class RegistrationForm {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String[] skills;
	boolean agreedWithTerms;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public boolean isAgreedWithTerms() {
		return agreedWithTerms;
	}

	public void setAgreedWithTerms(boolean agreedWithTerms) {
		this.agreedWithTerms = agreedWithTerms;
	}


}
