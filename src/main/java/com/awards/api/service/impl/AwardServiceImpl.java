package com.awards.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awards.api.model.Award;
import com.awards.api.repository.AwardRepository;
import com.awards.api.service.AwardService;


@Service
public class AwardServiceImpl implements AwardService{
	
	@Autowired
	private AwardRepository awardRepository;
	
	public List<Award> findAll(){
		return awardRepository.findAll();
	}

	@Override
	public void create(Award award) {
		awardRepository.save(award);
	}

	@Override
	public Award update(Long id, Award updateAward) {
		// TODO Auto-generated method stub
		
		Award award = this.findById(id);
		if(award == null) {
			return null;
		}
		
		award.setCode(updateAward.getCode());
        award.setDescription(updateAward.getDescription());
        award.setCount(updateAward.getCount());
        
        return awardRepository.save(award);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		Award award = awardRepository.getOne(id);
		awardRepository.delete(award);
		
	}

	public Award findById(Long id) {
		Optional<Award> award = awardRepository.findById(id);
		if(award.isPresent())
			return award.get();
		return null;
	}
		
}
