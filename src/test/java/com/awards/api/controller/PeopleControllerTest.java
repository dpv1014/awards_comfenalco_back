package com.awards.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.awards.api.model.People;
import com.awards.api.service.impl.PeopleServiceImpl;
import com.awards.api.utils.Util;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PeopleControllerTest {
	
	private MockMvc mvc;
	
    @InjectMocks
    private PeopleController peopleController;
	
    @Mock
    private PeopleServiceImpl peopleService;
    
    @Autowired
    private TestEntityManager entityManager;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<People> jsonPeople;
    
    @Before
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(peopleController)
                .build();
        //peopleService = Mockito.mock(PeopleServiceImpl.class);
    }
    
    @Test
    public void whenGetPeopleByIdThenReturn200() throws Exception {
    	setup();
        Mockito.when(peopleService.findById(1L)).thenReturn(Util.createPeople());
        
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/people/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
        		jsonPeople.write(Util.createPeople()).getJson()
        );
    }

    @Test
    public void whenGetPeopleByIdThatNotExistThenReturn200() throws Exception {
    	setup();
        
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/people/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
    
    @Test
    public void whenGetPeopleListByIdThenReturn200() throws Exception {
    	setup();
    	List<People> peopleList = new ArrayList<People>();
    	peopleList.add(Util.createPeople());
    	
        Mockito.when(peopleService.findAll()).thenReturn(peopleList);
        
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/people")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    
    
}
