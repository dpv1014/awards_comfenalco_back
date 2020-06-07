package com.awards.api.service;

import java.util.List;

import com.awards.api.model.Award;


public interface AwardService {
	public abstract List<Award> findAll();
	public abstract void create(Award product);
	public abstract Award update(Long id, Award product);
	public abstract void delete(long id);
}
