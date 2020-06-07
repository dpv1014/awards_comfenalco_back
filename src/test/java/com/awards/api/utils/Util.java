package com.awards.api.utils;

import java.util.Date;

import com.awards.api.model.People;

public class Util {
	public static People createPeople() {
			People people = new People();
			people.setTypedocument("CC");
			people.setDocument("1088326156");
			people.setName("David");
			people.setLastname("Pulgarin Valencia");
			people.setBirthdate(new Date());
			return people;
	}
	
}
