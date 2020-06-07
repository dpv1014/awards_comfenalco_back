package com.awards.api.service.impl;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.awards.api.model.People;
import com.awards.api.service.PeopleService;
import com.awards.api.utils.Util;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PeopleServiceImplTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PeopleServiceImplTest.class);
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private PeopleService peopleService;
		
	@Test
	public void whenCreatePeopleWithoutNameThenShouldReceiveException() {
		People people = Util.createPeople();
		people.setName(null);
		try {
		    entityManager.persist(people);
		    LOGGER.info("asdasdasd");
		    fail("Expected an Exception null constraint to be thrown");
		} catch (Exception exception) {
		    Assert.assertEquals(exception.getClass().getName(), "javax.persistence.PersistenceException");
		}
	}
	
	@Test
	public void whenCreatePeopleWithoutDocumentThenShouldReceiveException() {
		People people = Util.createPeople();
		people.setDocument(null);
		try {
		    entityManager.persist(people);
		    fail("Expected an Exception null constraint name to be thrown");
		} catch (Exception exception) {
		    Assert.assertEquals(exception.getClass().getName(), "javax.persistence.PersistenceException");
		}
	}		
}
