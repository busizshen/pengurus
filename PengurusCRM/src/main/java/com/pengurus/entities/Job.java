package com.pengurus.entities;

import java.util.Calendar;
import java.util.List;

import com.pengurus.enums.Status;

public class Job {

    private Status status;
    private Calendar deadline;
    private Translation translation;
    private Integer amount;
    private Price price;
    private String description;
    private List<Task> task;

    public Job() {
        super();
    }

    public Job(Status status, Calendar deadline, Translation translation,
               Integer amount, Price price, String description,
               List<Task> task){
        super();
        this.status = status;
        this.deadline = deadline;
        this.translation = translation;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.task = task;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
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

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
    
}
