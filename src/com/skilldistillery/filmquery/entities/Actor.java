package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Actor {
	//create fields to correspond to db table columns
	private int id;
	private String firstName;
	private String lastName;
	//default ctor
	public Actor() {
		
	}
	
	public Actor(int id, String fn, String ln) {
		this.id = id;
		this.firstName = fn;
		this.lastName  =ln;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "Actor Info: \n------------------\n Id: " + id + "\n First Name: " + firstName + "\n Last Name: " + lastName;
	}

}
