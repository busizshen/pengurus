package com.pengurus.entities;

import java.util.Calendar;

import com.pengurus.enums.Rating;
import com.pengurus.enums.Status;

public class Task {
    
    private Status status;
    private Translator expert;
    private Calendar deadline;
    private Translation translation;
    private Integer amount;
    private Price price;
    private String description;
    private Job job;
    private Rating rating;
    private String comment;

    public Task() {
        super();
    }

    public Task(Status status, Translator expert, Calendar deadline,
                Translation translation, Integer amount, Price price,
                String description, Job job, Rating rating, String comment) {
        super();
        this.status = status;
        this.expert = expert;
        this.deadline = deadline;
        this.translation = translation;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.job = job;
        this.rating = rating;
        this.comment = comment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Translator getExpert() {
        return expert;
    }

    public void setExpert(Translator expert) {
        this.expert = expert;
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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
