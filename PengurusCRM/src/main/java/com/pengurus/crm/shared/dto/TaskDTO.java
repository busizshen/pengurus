package com.pengurus.crm.shared.dto;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.client.service.TaskServiceAsync;

public class TaskDTO implements IsSerializable {

	private Long id;
	private StatusDTO status;
	private TranslatorDTO expert;
	private Date deadline;
	private TranslationDTO translation;
	private Integer amount;
	private PriceDTO price;
	private String description;
	private JobDTO job;
	private RatingDTO rating;
	private String comment;

	public TaskDTO() {
		super();
	}

	public TaskDTO(StatusDTO status, TranslatorDTO expert, Date deadline,
			TranslationDTO translation, Integer amount, PriceDTO price,
			String description, JobDTO job, RatingDTO rating, String comment) {
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

	public TranslatorDTO getExpert() {
		return expert;
	}

	public void setExpert(TranslatorDTO expert) {
		this.expert = expert;
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

	public JobDTO getJob() {
		return job;
	}

	public void setJob(JobDTO job) {
		this.job = job;
	}

	public RatingDTO getRating() {
		return rating;
	}

	public void setRating(RatingDTO rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void create(TaskDTO taskDTO) {
		AsyncCallback<TaskDTO> callback = new AsyncCallback<TaskDTO>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");

			}

			@Override
			public void onSuccess(TaskDTO taskDTO) {
			}
		};
		TaskServiceAsync service = (TaskServiceAsync) GWT
				.create(TaskService.class);
		service.createTask(this, callback);
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
		TaskServiceAsync service = (TaskServiceAsync) GWT
				.create(TaskService.class);
		service.updateStatus(this, callback);
	}

}
