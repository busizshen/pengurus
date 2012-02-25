package com.pengurus.crm.shared.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class JobDTO implements IsSerializable {
	  private Long id;
	    private StatusDTO status;
	    private Date deadline;
	    private TranslationDTO translation;
	    private Integer amount;
	    private PriceDTO price;
	    private String description;
	    private Set<TaskDTO> task = new HashSet<TaskDTO>();

	    public JobDTO() {
	        super();
	    }

	    public JobDTO(StatusDTO status, Date deadline, TranslationDTO translation,
	               Integer amount, PriceDTO price, String description,
	               Set<TaskDTO> task){
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

	    public StatusDTO getStatus() {
	        return status;
	    }

	    public void setStatus(StatusDTO status) {
	        this.status = status;
	    }

	    public Date getDeadline() {
	        return deadline;
	    }

	    public void setDeadline(Date deadline) {
	        this.deadline = deadline;
	    }

	    public TranslationDTO getTranslation() {
	        return translation;
	    }

	    public void setTranslation(TranslationDTO translation) {
	        this.translation = translation;
	    }

	    public Integer getAmount() {
	        return amount;
	    }

	    public void setAmount(Integer amount) {
	        this.amount = amount;
	    }

	    public PriceDTO getPrice() {
	        return price;
	    }

	    public void setPrice(PriceDTO price) {
	        this.price = price;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public Set<TaskDTO> getTask() {
	        return task;
	    }

	    public void setTask(Set<TaskDTO> task) {
	        this.task = task;
	    }

}
