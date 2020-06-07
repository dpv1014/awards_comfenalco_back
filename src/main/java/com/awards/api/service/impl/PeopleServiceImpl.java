package com.awards.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awards.api.model.Award;
import com.awards.api.model.People;
import com.awards.api.repository.AwardRepository;
import com.awards.api.repository.PeopleRepository;
import com.awards.api.service.PeopleService;


@Service
public class PeopleServiceImpl implements PeopleService{
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	@Autowired
	private AwardRepository awardRepository;
	
	public List<People> findAll(){
		return peopleRepository.findAll();
	}

	@Override
	public void create(People people) {
		peopleRepository.save(people);
	}
		
	@Override
	public People update(Long id, People updatePeople) {
		// TODO Auto-generated method stub
		
		People people = this.findById(id);
		if(people == null) {
			return null;
		}
		
		people.setTypedocument(updatePeople.getTypedocument());
        people.setDocument(updatePeople.getDocument());
        people.setName(updatePeople.getName());
        people.setLastname(updatePeople.getLastname());
        people.setBirthdate(updatePeople.getBirthdate());
        
        return peopleRepository.save(people);
	}
	
	@Override
	public People update(Long id, People updatePeople, Award award) {
		// TODO Auto-generated method stub
		
		People people = this.findById(id);
		if(people == null) {
			return null;
		}
		
		if(award.getCount() <= 0) {
			return null;
		}
		
		people.setTypedocument(updatePeople.getTypedocument());
        people.setDocument(updatePeople.getDocument());
        people.setName(updatePeople.getName());
        people.setLastname(updatePeople.getLastname());
        people.setBirthdate(updatePeople.getBirthdate());
        people.setAward(award);
        
        return peopleRepository.save(people);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		People people = this.findById(id);
		if(people != null && people.getAward() == null) {
			peopleRepository.delete(people);	
		}
	}
	
	//1 -> Achievement of awards -->  ALL OK
	//-1 -> There are not enough stocks for the number of people --> ERROR
	@Override
	public int assignAwards() {
		List<Award> awardsList = awardRepository.findAllWithQuanty();
		List<People> peopleList = peopleRepository.findAllWithoutAwards();
		
		
		Long totalAwards = awardRepository.getSumWithCountGreatherThan();
		int totalPeople = peopleList.size();
		
		if(peopleList.isEmpty()) {
			return 1;
		}
		
		if(awardsList.isEmpty() || totalAwards < totalPeople) {
			return -1;
		}
		
		Random randomInstance = new Random();

		for (People people : peopleList) {
			int randomValue = randomInstance.nextInt(awardsList.size());
			Award award = awardsList.get(randomValue);
			
			people.setAward(award);
			peopleRepository.save(people);
			
			award.setCount(award.getCount() - 1);
			awardRepository.save(award);
			
			if(award.getCount() == 0) {
				awardsList.remove(randomValue);
			}
			
        }
		
		return 1;
	}
	
	public People findById(Long id) {
		Optional<People> people = peopleRepository.findById(id);
		if(people.isPresent())
			return people.get();
		return null;
	}
		
}
