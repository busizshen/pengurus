package com.pengurus.crm.daos.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.TaskDAO;
import com.pengurus.crm.entities.Task;

public class TaskDAOImpl extends GenericDAOImpl<Task> implements TaskDAO {

	protected static final Logger log = LoggerFactory
			.getLogger(TaskDAOImpl.class);

	public TaskDAOImpl() {
		this.type = Task.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> loadAllByExpertId(Long id) {
		List<Task> tasks = getHibernateTemplate().find(
				"select t from Task t " + "where t.expert = " + id);
		return tasks;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> loadAllByReviewerId(Long id) {
		List<Task> tasks = getHibernateTemplate().find(
				"select t from Task t " + "where t.reviewer = " + id);
		return tasks;
	}
}
