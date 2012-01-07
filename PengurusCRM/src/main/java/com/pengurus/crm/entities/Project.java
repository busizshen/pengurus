package com.pengurus.crm.entities;

import java.util.List;
import com.pengurus.crm.enums.Status;


public class Project {

    private Status status;
    private Client client;
    private Worker supervisor;
    private List<Worker> projectManagers;
    private List<Translator> experts;
    private List<Translator> freelancers;
    private List<Job> jobs;
    private String description;

    public Project() {
        super();
    }

    public Project(Status status, Client client, Worker supervisor,
                   List<Worker> projectManagers, List<Translator> experts,
                   List<Translator> freelancers, List<Job> jobs,
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

    public List<Worker> getProjectManagers() {
        return projectManagers;
    }

    public void setProjectManagers(List<Worker> projectManagers) {
        this.projectManagers = projectManagers;
    }

    public List<Translator> getExperts() {
        return experts;
    }

    public void setExperts(List<Translator> experts) {
        this.experts = experts;
    }

    public List<Translator> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(List<Translator> freelancers) {
        this.freelancers = freelancers;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
