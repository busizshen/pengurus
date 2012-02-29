package com.pengurus.crm.server.crud;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.IndividualClientDAO;
import com.pengurus.crm.daos.JobDAO;
import com.pengurus.crm.daos.LanguageDAO;
import com.pengurus.crm.daos.PriceDAO;
import com.pengurus.crm.daos.ProjectDAO;
import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.daos.TranslationTypeDAO;
import com.pengurus.crm.daos.TranslatorDAO;
import com.pengurus.crm.daos.WorkerDAO;
import com.pengurus.crm.entities.CurrencyType;
import com.pengurus.crm.entities.IndividualClient;
import com.pengurus.crm.entities.Job;
import com.pengurus.crm.entities.Language;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.entities.Price;
import com.pengurus.crm.entities.Project;
import com.pengurus.crm.entities.Task;
import com.pengurus.crm.entities.Translation;
import com.pengurus.crm.entities.TranslationType;
import com.pengurus.crm.entities.Translator;
import com.pengurus.crm.entities.Worker;
import com.pengurus.crm.enums.Rating;
import com.pengurus.crm.enums.Status;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectCRUDTest {

    @Autowired
    private TranslationDAO translationDAO;
    @Autowired
    private TranslatorDAO translatorDAO;
    @Autowired
    private LanguageDAO languageDAO;
    @Autowired
    private TranslationTypeDAO translationTypeDAO;
    @Autowired
    private PriceDAO PriceDAO;
    @Autowired
    private JobDAO jobDAO;
    @Autowired
    private IndividualClientDAO individualClientDAO;
    @Autowired
    private WorkerDAO workerDAO;
    @Autowired
    private ProjectDAO projectDAO;

    private Set<Job> jobs;
    private Set<Translator> experts;
    private Set<Translator> freelancers;
    private Set<Worker> projectManagers;
    private IndividualClient individualClient;
    private Worker supervisor;
    private static int change = 0;

    @Before
    public void before() {
        Language to = new Language("DanishProject" + Integer.toString(change));
        Language from = new Language("FrenchProject" + Integer.toString(change));
        languageDAO.create(from);
        languageDAO.create(to);

        TranslationType translationType = new TranslationType("description",
                "unit");
        translationTypeDAO.create(translationType);

        Price price = new Price(200 + change, new CurrencyType("$$Project"
                + Integer.toString(change)));
        PriceDAO.create(price);

        Translation translation = new Translation(translationType, from, to,
                price);
        translationDAO.create(translation);

        // create user roles
        Set<UserRoleDTO> userRoles = new HashSet<UserRoleDTO>();
        userRoles.add(UserRoleDTO.ROLE_EXPERT);
        userRoles.add(UserRoleDTO.ROLE_VERIFICATOR);

        Set<UserRoleDTO> userRoles2 = new HashSet<UserRoleDTO>();
        userRoles2.add(UserRoleDTO.ROLE_FREELANCER);
        userRoles2.add(UserRoleDTO.ROLE_VERIFICATOR);

        Set<UserRoleDTO> userRolesPM = new HashSet<UserRoleDTO>();
        userRoles2.add(UserRoleDTO.ROLE_PROJECTMNAGER);

        // create personal data
        Set<String> phoneNumbers = new HashSet<String>();
        phoneNumbers.add("00-000-000");
        phoneNumbers.add("00-001-001");
        Set<String> emails = new HashSet<String>();
        emails.add("john@yahoo.com");
        emails.add("john2@yahoo.com");
        PersonalData personalData = new PersonalData("Firstame", "Lastname",
                "Address", phoneNumbers, emails);

        // create translators
        Translator translator = new Translator(userRoles, "projectTranslator"
                + Integer.toString(change), "pass", "desc", personalData,
                new HashSet<Translation>());
        Translator translator2 = new Translator(userRoles, "2projectTranslator"
                + Integer.toString(change), "pass", "desc", personalData,
                new HashSet<Translation>());
        translatorDAO.create(translator);
        translatorDAO.create(translator2);
        experts = new HashSet<Translator>();
        experts.add(translator);
        experts.add(translator2);

        // create project managers
        Worker projectManager = new Worker(userRolesPM, "ProjectManager"
                + Integer.toString(change), "pass", "supervisor description",
                personalData);
        Worker projectManager2 = new Worker(userRolesPM, "1ProjectManager"
                + Integer.toString(change), "pass", "supervisor description",
                personalData);
        workerDAO.create(projectManager);
        workerDAO.create(projectManager2);
        projectManagers = new HashSet<Worker>();
        projectManagers.add(projectManager);
        projectManagers.add(projectManager2);

        // create freelancers
        Translator freelancer = new Translator(userRoles, "projectFreelancer"
                + Integer.toString(change), "pass", "desc", personalData,
                new HashSet<Translation>());
        Translator freelancer2 = new Translator(userRoles, "2projectFreelancer"
                + Integer.toString(change), "pass", "desc", personalData,
                new HashSet<Translation>());
        translatorDAO.create(freelancer);
        translatorDAO.create(freelancer2);
        freelancers = new HashSet<Translator>();
        freelancers.add(freelancer);
        freelancers.add(freelancer2);

        // create task
        Task task = new Task(Status.accepted, translator, Calendar
                .getInstance().getTime(), translation, 100, price, "desc",
                null, Rating.bad, "comm");
        Task task2 = new Task(Status.accepted, translator, Calendar
                .getInstance().getTime(), translation, 100, price, "desc2",
                null, Rating.bad, "comm2");
        Set<Task> tasks = new HashSet<Task>();
        tasks.add(task);
        Set<Task> tasks2 = new HashSet<Task>();
        tasks2.add(task2);

        // create jobs
        Job job = new Job(Status.closed, Calendar.getInstance().getTime(),
                translation, 1000, price, "Job description", tasks);
        Job job2 = new Job(Status.open, Calendar.getInstance().getTime(),
                translation, 21000, price, "Job description2", tasks2);
        task.setJob(job);
        task2.setJob(job2);
        jobDAO.create(job);
        jobDAO.create(job2);

        jobs = new HashSet<Job>();
        jobs.add(job2);
        jobs.add(job);

        // create client
        Set<UserRoleDTO> clientRoles = new HashSet<UserRoleDTO>();
        clientRoles.add(UserRoleDTO.ROLE_CLIENT);
        individualClient = new IndividualClient(clientRoles,
                "IndividualClientProject" + Integer.toString(change), "pass",
                "individual client", personalData);
        individualClientDAO.create(individualClient);

        // create supervisor
        Set<UserRoleDTO> supervisorRoles = new HashSet<UserRoleDTO>();
        supervisorRoles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
        supervisorRoles.add(UserRoleDTO.ROLE_EXECUTIVE);
        supervisor = new Worker(supervisorRoles, "ProjectSupervisor"
                + Integer.toString(change), "pass", "supervisor description",
                personalData);
        workerDAO.create(supervisor);

        change++;

    }

    @Test
    public void simpleProjectCreate() {
        Project project = new Project(Status.inProgress, individualClient,
                supervisor, projectManagers, experts, freelancers, jobs,
                "Project description");
        projectDAO.create(project);
        Assert.assertNotNull(project.getId());
    }

    @Test
    public void simpleProjectGet() {
        // prepare data
        Project project = new Project(Status.inProgress, individualClient,
                supervisor, projectManagers, experts, freelancers, jobs,
                "Project description");
        projectDAO.create(project);
        Assert.assertNotNull(project.getId());

        // test
        Project project2 = projectDAO.read(project.getId());
        Assert.assertEquals(project2.getId(), project.getId());
        Assert.assertEquals(project2.getClient().getId(), project.getClient()
                .getId());
        Assert.assertEquals(project2.getDescription(), project.getDescription());
        Assert.assertEquals(project2.getStatus(), project.getStatus());
        Assert.assertEquals(project2.getSupervisor().getId(), project
                .getSupervisor().getId());
    }

    @Test
    public void simpleProjectUpdate() {
        // prepare data
        Project project = new Project(Status.inProgress, individualClient,
                supervisor, projectManagers, experts, freelancers, jobs,
                "Project description");
        projectDAO.create(project);
        Assert.assertNotNull(project.getId());

        // test
        project.setDescription("New description");
        Set<String> emails = new HashSet<String>();
        emails.add("john@doe.com");
        Set<String> phoneNumbers = new HashSet<String>();
        emails.add("00-222-222-222");
        PersonalData personalData2 = new PersonalData("FirstName", "LastName",
                "address", phoneNumbers, emails);
        IndividualClient newClient = new IndividualClient(null,
                "UpdateProjectClient", "pass", "desc", personalData2);
        individualClientDAO.create(newClient);
        project.setClient(newClient);
        projectDAO.update(project);
        
        Project project2 = projectDAO.read(project.getId());
        Assert.assertEquals(project2.getId(), project.getId());
        Assert.assertEquals(project2.getClient().getId(), project.getClient()
                .getId());
        Assert.assertEquals(project2.getDescription(), project.getDescription());
        Assert.assertEquals(project2.getStatus(), project.getStatus());
        Assert.assertEquals(project2.getSupervisor().getId(), project
                .getSupervisor().getId());
    }
    
    @Test
    public void simpleProjectDelete(){
     // prepare data
        Project project = new Project(Status.inProgress, individualClient,
                supervisor, projectManagers, experts, freelancers, jobs,
                "Project description");
        projectDAO.create(project);
        Assert.assertNotNull(project.getId());

        // test
        projectDAO.delete(project);
        Assert.assertNull(projectDAO.read(project.getId()));
    }
}
