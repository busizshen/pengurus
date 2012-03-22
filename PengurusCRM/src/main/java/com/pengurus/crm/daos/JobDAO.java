package com.pengurus.crm.daos;

import java.util.List;

import com.pengurus.crm.entities.Job;

public interface JobDAO extends GenericDAO<Job>{

	Job getById(Long id);
	List<Job> loadAllByExpertId(Long id);

}
