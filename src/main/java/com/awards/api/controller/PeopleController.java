package com.awards.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.awards.api.exception.PeopleNotFoundException;
import com.awards.api.model.People;
import com.awards.api.service.impl.PeopleServiceImpl;

import org.springframework.validation.FieldError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@Api(value = "People api methods", description = "This API has a CRUD for people")
public class PeopleController {
	
	@Autowired
	private PeopleServiceImpl peopleService;
	
	@GetMapping("/people")
	@ApiOperation(value = "Get list of people", notes = "Return all people" )
	public ResponseEntity<Object> peopleLists() {
		return new ResponseEntity<>(peopleService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/people")
	public ResponseEntity<Object> createPeople(@RequestBody People people) throws PeopleNotFoundException {
		peopleService.create(people);
	    return new ResponseEntity<>(people, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Get people by id", notes = "Return one people by id if exis" )
	@RequestMapping(value="/people/{id}",method = RequestMethod.GET)
	@GetMapping(value = "/people/{id}")
	public ResponseEntity<Object> getPeople(@PathVariable Long id) {
        People people = peopleService.findById(id);
        if (people == null) {
            return new ResponseEntity<>("People with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(people, HttpStatus.OK);
	}
	
	@PutMapping(value = "/people/{id}")
	public ResponseEntity<Object> getPeople(@PathVariable Long id, @RequestBody People updatePeople) {		        
        People people = peopleService.update(id, updatePeople);
        if(people == null) {
        	return new ResponseEntity<>("People with id " + id + " not found", HttpStatus.NOT_FOUND);
        }        
        
        return new ResponseEntity<Object>(people, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete people by id", notes = "Return all people" )
	@RequestMapping(value="/people/{id}",method = RequestMethod.DELETE)
	@DeleteMapping(value = "/people/{id}")
	public ResponseEntity<Object> deletePeople(@PathVariable Long id) {        
        peopleService.delete(id); 
        return new ResponseEntity<Object>(peopleService.findAll(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Assign awards to people", notes = "Return all people" )
	@RequestMapping(value="/people/assignAwards",method = RequestMethod.POST)
	@PostMapping(value = "/people/assignAwards")
	public ResponseEntity<Object> assignAwards() {   
		if(peopleService.assignAwards() == 1) {
			return new ResponseEntity<Object>(peopleService.findAll(), HttpStatus.OK);	
		} else {
			return new ResponseEntity<Object>("ERROR_TRY_ASSIGN_AWARDS_TO_PEOPLE", HttpStatus.OK);
		}
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
