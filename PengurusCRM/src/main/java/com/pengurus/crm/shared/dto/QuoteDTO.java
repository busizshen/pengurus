package com.pengurus.crm.shared.dto;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;

public class QuoteDTO implements IsSerializable {

	private Long id;
	private StatusDTO status;
	private ClientDTO client;
	private WorkerDTO supervisor;
	private Set<JobDTO> jobs = new HashSet<JobDTO>();
	private String description;

	public QuoteDTO() {
	}

	public QuoteDTO(Long id, StatusDTO status, ClientDTO client,
			WorkerDTO supervisor, Set<JobDTO> jobs, String description) {
		super();
		this.id = id;
		this.status = status;
		this.client = client;
		this.supervisor = supervisor;
		this.jobs = jobs;
		this.description = description;
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

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public WorkerDTO getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(WorkerDTO supervisor) {
		this.supervisor = supervisor;
	}

	public Set<JobDTO> getJobs() {
		return jobs;
	}

	public void setJobs(Set<JobDTO> jobs) {
		this.jobs = jobs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object get(String property) {
		// TODO Auto-generated method stub
		return null;
	}

	public Listener<DomEvent> changeStatus(final QuoteDTO quoteDTO) {

		return new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				if (quoteDTO.status != null)
					quoteDTO.status = quoteDTO.status.increase();
				else
					quoteDTO.status = StatusDTO.getFirstStatus();
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {

					public void onFailure(Throwable t) {
						Window.Location.assign("/spring_security_login");
					}

					@Override
					public void onSuccess(Void arg0) {

					}
				};
				QuoteServiceAsync service = (QuoteServiceAsync) GWT
						.create(QuoteService.class);
				service.updateQuoteStatus(quoteDTO, callback);

			}
		};
	}

	public Listener<DomEvent> backStatus(final QuoteDTO quoteDTO) {
		return new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				if (quoteDTO.status != null)
					quoteDTO.status = quoteDTO.status.decrease();
				else
					quoteDTO.status = StatusDTO.getFirstStatus();
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {

					public void onFailure(Throwable t) {
						Window.Location.assign("/spring_security_login");
					}

					@Override
					public void onSuccess(Void arg0) {

					}
				};
				QuoteServiceAsync service = (QuoteServiceAsync) GWT
						.create(QuoteService.class);
				service.updateQuoteStatus(quoteDTO, callback);

			}
		};
	}

	public void update() {

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");

			}

			@Override
			public void onSuccess(Void arg0) {
			}
		};
		QuoteServiceAsync service = (QuoteServiceAsync) GWT
				.create(QuoteService.class);
		service.updateQuote(this, callback);

	}

	public void delete() {
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");

			}

			@Override
			public void onSuccess(Void arg0) {
			}
		};
		QuoteServiceAsync service = (QuoteServiceAsync) GWT
				.create(QuoteService.class);
		service.deleteQuote(this, callback);
	}

	public boolean check() {
		if (this.client == null) {
			return false;
		}
		/*
		 * if (this.supervisor == null) { return false; }
		 */
		return true;
	}

}
