package com.pengurus.crm.entities;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.enums.Rating;
import com.pengurus.crm.enums.Status;
import com.pengurus.crm.server.services.UserServiceImpl;
import com.pengurus.crm.shared.dto.TaskDTO;

public class Task {
   
	protected static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	   
	
    private Long id;
    private Status status;
    private Translator expert;
    private Translator reviewer;
    private Date deadline;
    private Translation translation;
    private Integer amount;
    private Price price;
    private String description;
    private Job job;
    public Translator getReviewer() {
		return reviewer;
	}

	public void setReviewer(Translator reviewer) {
		this.reviewer = reviewer;
	}

	private Rating rating;
    private String comment;

    public Task() {
        super();
    }

    public Task(Status status, Translator expert, Translator reviewer, Date deadline,
                Translation translation, Integer amount, Price price,
                String description, Job job, Rating rating, String comment) {
        super();
        this.status = status;
        this.expert = expert;
        this.reviewer = reviewer;
        this.deadline = deadline;
        this.translation = translation;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.job = job;
        this.rating = rating;
        this.comment = comment;
    }
   
    public Task(TaskDTO taskDTO) {
    	super();
    	this.id = taskDTO.getId();
    	if(taskDTO.getExpert() != null)
    		this.expert = new Translator(taskDTO.getExpert());
    	if(taskDTO.getReviewer() != null){
    		this.reviewer = new Translator(taskDTO.getReviewer());
    	}
    	this.deadline = taskDTO.getDeadline();
    	if(taskDTO.getTranslation() != null)
    		this.translation = new Translation(taskDTO.getTranslation());
    	if(taskDTO.getPrice() != null)
    		this.price = new Price(taskDTO.getPrice());
    	this.amount = taskDTO.getAmount();
    	this.description = taskDTO.getDescription();
    	if(taskDTO.getJob() != null)
    		this.job = new Job(taskDTO.getJob().getId());
    	if(taskDTO.getRating() != null)
    		this.rating = Rating.toRating(taskDTO.getRating());
    	if(taskDTO.getStatus() != null)
    		this.status = Status.toStatus(taskDTO.getStatus());
    	this.comment = taskDTO.getComment();
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

    public Translator getExpert() {
        return expert;
    }

    public void setExpert(Translator expert) {
        this.expert = expert;
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
    
    public TaskDTO toDTO(){
    	TaskDTO tDTO = new TaskDTO();
    	tDTO.setAmount(this.amount);
    	tDTO.setComment(this.comment);
    	tDTO.setDeadline(this.deadline);
    	tDTO.setDescription(this.description);
    	tDTO.setId(this.id);
    	if(this.expert != null)
    		tDTO.setExpert(this.expert.toDTO());
    	if(this.reviewer != null)
    		tDTO.setReviewer(this.reviewer.toDTO());
    	if(this.job != null)
    		tDTO.setJob(this.job.toDTOLazy());
    	if(price != null)
    		tDTO.setPrice(price.toDTO());
    	if(rating != null)
    		tDTO.setRating(rating.toDTO());
    	if(status != null)
    		tDTO.setStatus(status.toDTO());
    	if(translation != null)
    		tDTO.setTranslation(translation.toDTO());
    	return tDTO;
    }
    
}
