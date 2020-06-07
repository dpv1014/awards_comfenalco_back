package com.awards.api.service;

import java.util.List;

import com.awards.api.model.Award;
import com.awards.api.model.People;


public interface PeopleService {
	public abstract List<People> findAll();
	public abstract void create(People product);
	public abstract People update(Long id, People product);
	public abstract People update(Long id, People product, Award award);
	public abstract int assignAwards();
	public abstract void delete(long id);
}
