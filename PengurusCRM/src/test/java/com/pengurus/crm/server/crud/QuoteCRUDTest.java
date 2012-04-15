package com.pengurus.crm.server.crud;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

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
import com.pengurus.crm.daos.QuoteDAO;
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
import com.pengurus.crm.entities.Quote;
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
public class QuoteCRUDTest {

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
    private QuoteDAO quoteDAO;

    private Set<Job> jobs;
    private IndividualClient individualClient;
    private Worker supervisor;
    private static int change = 0;

    @Before
    public void before() {
        Language to = new Language("DanishQuote" + Integer.toString(change));
        Language from = new Language("FrenchQuote" + Integer.toString(change));
        languageDAO.create(from);
        languageDAO.create(to);

        TranslationType translationType = new TranslationType("description",
                "unit");
        translationTypeDAO.create(translationType);

        Price price = new Price(200 + change, new CurrencyType("$$Quote"
                + Integer.toString(change)));
        PriceDAO.create(price);

        Translation translation = new Translation(translationType, from, to,
                price);
        translationDAO.create(translation);

        Set<UserRoleDTO> userRoles = new HashSet<UserRoleDTO>();
        userRoles.add(UserRoleDTO.ROLE_EXPERT);
        userRoles.add(UserRoleDTO.ROLE_VERIFICATOR);

        Set<String> phoneNumbers = new HashSet<String>();
        phoneNumbers.add("00-000-000");
        phoneNumbers.add("00-001-001");
        Set<String> emails = new HashSet<String>();
        emails.add("john@yahoo.com");
        emails.add("john2@yahoo.com");
        PersonalData personalData = new PersonalData("Firstame", "Lastname",
                "Address", phoneNumbers, emails);

        Translator translator = new Translator(userRoles, "QuoteTranslator"
                + Integer.toString(change), "pass", "desc", personalData,
                new HashSet<Translation>());
        translatorDAO.create(translator);

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

        Set<UserRoleDTO> clientRoles = new HashSet<UserRoleDTO>();
        clientRoles.add(UserRoleDTO.ROLE_CLIENT);
        individualClient = new IndividualClient(clientRoles,
                "IndividualClientQuote" + Integer.toString(change), "pass",
                "individual client", personalData);
        individualClientDAO.create(individualClient);

        Set<UserRoleDTO> supervisorRoles = new HashSet<UserRoleDTO>();
        supervisorRoles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
        supervisorRoles.add(UserRoleDTO.ROLE_EXECUTIVE);
        supervisor = new Worker(supervisorRoles, "QuoteSupervisor"
                + Integer.toString(change), "pass", "supervisor description",
                personalData);
        workerDAO.create(supervisor);

        change++;

    }

    @Test
    public void simpleCreateQuote() {
        Quote quote = new Quote(Status.in_progress, individualClient,
                supervisor, jobs, "Quote description");
        quoteDAO.create(quote);
        Assert.assertNotNull(quote.getId());

    }

    @Test
    public void simpleGetQuote() {
        // prepare data
        Quote quote = new Quote(Status.in_progress, individualClient,
                supervisor, jobs, "Quote description");
        quoteDAO.create(quote);

        // test
        Quote quote2 = quoteDAO.read(quote.getId());
        Assert.assertEquals(quote.getDescription(), quote2.getDescription());
        Assert.assertEquals(quote.getClient().getId(), quote2.getClient()
                .getId());
        Assert.assertEquals(quote.getStatus(), quote2.getStatus());
        Assert.assertEquals(quote.getSupervisor().getId(), quote2
                .getSupervisor().getId());
    }
    
    @Test
    public void simpleUpdateQuote() {
        // prepare data
        Quote quote = new Quote(Status.in_progress, individualClient,
                supervisor, jobs, "Quote description");
        quoteDAO.create(quote);
        
        // test
        quote.setDescription("new quote description");
        quote.setStatus(Status.open);
        quote.setClient(null);
        quoteDAO.update(quote);
        
        Quote quote2 = quoteDAO.read(quote.getId());   
        Assert.assertEquals(quote.getDescription(), quote2.getDescription());
        Assert.assertNull(quote2.getClient());
        Assert.assertEquals(quote.getStatus(), quote2.getStatus());
        Assert.assertEquals(quote.getSupervisor().getId(), quote2
                .getSupervisor().getId());
    }
    
    @Test
    public void simpleDeleteQuote(){
        // prepare data
        Quote quote = new Quote(Status.in_progress, individualClient,
                supervisor, jobs, "Quote description");
        quoteDAO.create(quote);
        
        //test
        quoteDAO.delete(quote);
        Assert.assertNull(quoteDAO.read(quote.getId()));
    }

}
