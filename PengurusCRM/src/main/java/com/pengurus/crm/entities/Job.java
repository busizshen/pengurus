package com.pengurus.crm.entities;

import java.util.Date;
import java.util.Set;

import com.pengurus.crm.enums.Status;

public class Job {

    private Long id;
    private Status status;
    private Date deadline;
    private Translation translation;
    private Integer amount;
    private Price price;
    private String description;
    private Set<Task> task;

    public Job() {
        super();
    }

    public Job(Status status, Date deadline, Translation translation,
               Integer amount, Price price, String description,
               Set<Task> task){
        super();
        this.status = status;
        this.deadline = deadline;
        this.translation = translation;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.task = task;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }
    
}
