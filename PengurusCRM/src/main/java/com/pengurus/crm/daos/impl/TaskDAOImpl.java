package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pengurus.crm.daos.TaskDAO;
import com.pengurus.crm.entities.Task;

public class TaskDAOImpl extends GenericDAOImpl<Task> implements TaskDAO{
    
    protected static final Logger log = LoggerFactory.getLogger(TaskDAOImpl.class);
    
    public TaskDAOImpl(){
        this.type = Task.class;
    }

}
