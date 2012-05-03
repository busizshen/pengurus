package com.pengurus.crm.shared.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.client.service.JobServiceAsync;

public class JobDTO implements IsSerializable {

	  private Long id;
	    private StatusJobDTO status;
	    private Date deadline;
	    private TranslationDTO translation;
	    private Integer amount;
	    private PriceDTO price;
	    private String description;
	    private Set<TaskDTO> task = new HashSet<TaskDTO>();

	    public JobDTO() {
	        super();
	    }

	    public JobDTO(StatusJobDTO status, Date deadline, TranslationDTO translation,
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

	    public StatusJobDTO getStatus() {
	        return status;
	    }

	    public void setStatus(StatusJobDTO status) {
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

		public void update() {
			AsyncCallback<JobDTO> callback = new AsyncCallback<JobDTO>() {

				public void onFailure(Throwable t) {
					Window.Location.assign("/spring_security_login");
					MessageBox.info("Internal error", "Cannot update status", null);
				}

				@Override
				public void onSuccess(JobDTO result) {
					//reload 
				}
			
			};
			JobServiceAsync service = (JobServiceAsync) GWT
					.create(JobService.class);
			service.updateJob(this, callback);
		}
		
		public void updateStatus() {
			AsyncCallback<Void> callback = new AsyncCallback<Void>() {

				public void onFailure(Throwable t) {
					Window.Location.assign("/spring_security_login");

				}

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
				}
			};
			JobServiceAsync service = (JobServiceAsync) GWT
					.create(JobService.class);
			service.updateStatus(this, callback);
		}

		public boolean checked() {
			if(price == null)
				return false;
			if(amount == null)
				return false;
			if(translation == null)
				return false;
			if(deadline == null)
				return false;
			if(description == null)
				return false;
			return true;
		}
	    
}
