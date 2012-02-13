package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pengurus.crm.daos.JobDAO;
import com.pengurus.crm.entities.Job;

public class JobDAOImpl extends GenericDAOImpl<Job> implements JobDAO {

    protected static final Logger log = LoggerFactory.getLogger(JobDAOImpl.class);
    
    public JobDAOImpl(){
        this.type = Job.class;
    }
}
