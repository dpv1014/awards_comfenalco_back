package com.awards.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.awards.api.exception.AwardNotFoundException;
import com.awards.api.model.Award;
import com.awards.api.service.impl.AwardServiceImpl;

import org.springframework.validation.FieldError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@Api(value = "Awards api methods", description = "This API has a CRUD for award")
public class AwardController {
	
	@Autowired
	private AwardServiceImpl awardService;
	
	@GetMapping("/awards")
	@ApiOperation(value = "Get list of awards", notes = "Return all award" )
	public ResponseEntity<Object> awardsLists() {
		return new ResponseEntity<>(awardService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/awards")
	public ResponseEntity<Object> createAward(@RequestBody Award award) throws AwardNotFoundException {
		awardService.create(award);
	    return new ResponseEntity<>(award, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Get award by id", notes = "Return one award by id if exis" )
	@RequestMapping(value="/awards/{id}",method = RequestMethod.GET)
	@GetMapping(value = "/awards/{id}")
	public ResponseEntity<Object> getAward(@PathVariable Long id) {
        Award award = awardService.findById(id);
        if (award == null) {
            return new ResponseEntity<>("Award with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(award, HttpStatus.OK);
	}
	
	@PutMapping(value = "/awards/{id}")
	public ResponseEntity<Object> getAward(@PathVariable Long id, @RequestBody Award updateAward) {		        
        Award award = awardService.update(id, updateAward);
        if(award == null) {
        	return new ResponseEntity<>("Award with id " + id + " not found", HttpStatus.NOT_FOUND);
        }        
        
        return new ResponseEntity<Object>(award, HttpStatus.OK);
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
