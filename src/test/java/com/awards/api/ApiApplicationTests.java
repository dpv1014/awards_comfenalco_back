package com.awards.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.awards.api.controller.AwardController;
import com.awards.api.controller.PeopleController;


@SpringBootTest
class ApiApplicationTests {

	@Autowired
	private PeopleController peopleController;
	
	@Autowired
	private AwardController awardController;
	
	@Test
	void contextLoads() {
	}

}
