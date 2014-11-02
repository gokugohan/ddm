package com.heldermenezes.activeandroidlibrary;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="Person02")
public class Person02 extends Model{
	
	@Column(name="personName")
	private String personName;
	@Column(name="personAge")
	private int personAge;
	@Column(name="personScore")
	private Score personScore;
	
	public Person02() {
		super();
	}
	
	public Person02(String personName, int personAge,Score personScore){
		super();
		this.setPersonName(personName);
		this.setPersonAge(personAge);
		this.setPersonScore(personScore);
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

	public Score getPersonScore() {
		return personScore;
	}
	
	public void setPersonScore(Score personScore) {
		this.personScore = personScore;
	}
	
}
