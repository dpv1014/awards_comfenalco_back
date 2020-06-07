package com.awards.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.awards.api.model.Award;


@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {
	
	@Query(value = "SELECT * FROM awards AS a WHERE a.count > 0", nativeQuery = true)
	public List<Award> findAllWithQuanty();
	
	@Query(value = "SELECT SUM(count) FROM awards", nativeQuery = true)
    public Long getSumWithCountGreatherThan();
}