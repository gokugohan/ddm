package com.heldermenezes.activeandroidlibrary;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="Person01")
public class Person01 extends Model{

	@Column(name="personName")
	private String personName;
	@Column(name="personAge")
	private int personAge;
	
	public Person01() {
		super();
	}
	
	public Person01(String personName, int personAge){
		super();
		this.setPersonName(personName);
		this.setPersonAge(personAge);
	}

	public int getPersonAge() {
		return personAge;
	}

	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
}
