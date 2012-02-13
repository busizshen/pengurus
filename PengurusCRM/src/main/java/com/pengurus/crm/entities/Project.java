package com.pengurus.crm.entities;

import java.util.Set;

import com.pengurus.crm.enums.Status;


public class Project {

    private Long id;
    private Status status;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Client client;
    private Worker supervisor;
    private Set<Worker> projectManagers;
    private Set<Translator> experts;
    private Set<Translator> freelancers;
    private Set<Job> jobs;
    private String description;

    public Project() {
        super();
    }

    public Project(Status status, Client client, Worker supervisor,
                   Set<Worker> projectManagers, Set<Translator> experts,
                   Set<Translator> freelancers, Set<Job> jobs,
                   String description) {
        super();
        this.status = status;
        this.client = client;
        this.supervisor = supervisor;
        this.projectManagers = projectManagers;
        this.experts = experts;
        this.freelancers = freelancers;
        this.jobs = jobs;
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Worker getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Worker supervisor) {
        this.supervisor = supervisor;
    }

    public Set<Worker> getProjectManagers() {
        return projectManagers;
    }

    public void setProjectManagers(Set<Worker> projectManagers) {
        this.projectManagers = projectManagers;
    }

    public Set<Translator> getExperts() {
        return experts;
    }

    public void setExperts(Set<Translator> experts) {
        this.experts = experts;
    }

    public Set<Translator> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(Set<Translator> freelancers) {
        this.freelancers = freelancers;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
