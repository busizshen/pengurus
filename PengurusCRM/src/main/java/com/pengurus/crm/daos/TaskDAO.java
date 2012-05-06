package com.pengurus.crm.daos;

import java.util.List;

import com.pengurus.crm.entities.Task;

public interface TaskDAO extends GenericDAO<Task> {

	List<Task> loadAllByExpertId(Long id);
	List<Task> loadAllByReviewerId(Long id);
}
