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

import com.pengurus.crm.daos.JobDAO;
import com.pengurus.crm.daos.LanguageDAO;
import com.pengurus.crm.daos.PriceDAO;
import com.pengurus.crm.daos.TaskDAO;
import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.daos.TranslationTypeDAO;
import com.pengurus.crm.daos.TranslatorDAO;
import com.pengurus.crm.entities.CurrencyType;
import com.pengurus.crm.entities.Job;
import com.pengurus.crm.entities.Language;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.entities.Price;
import com.pengurus.crm.entities.Task;
import com.pengurus.crm.entities.Translation;
import com.pengurus.crm.entities.TranslationType;
import com.pengurus.crm.entities.Translator;
import com.pengurus.crm.enums.Rating;
import com.pengurus.crm.enums.Status;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class JobCRUDTest {

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
    private TaskDAO taskDAO;

    private Translation translation;
    private Translator translator;
    private Set<Task> tasks;
    private static int change = 0;
    private Price price;

    @Before
    public void before() {
        Language to = new Language("Danishjob" + Integer.toString(change));
        Language from = new Language("Frenchjob" + Integer.toString(change));
        languageDAO.create(from);
        languageDAO.create(to);

        TranslationType translationType = new TranslationType("description",
                "unit");
        translationTypeDAO.create(translationType);

        price = new Price(200 + change, new CurrencyType("$$"
                + Integer.toString(change)));
        PriceDAO.create(price);

        translation = new Translation(translationType, from, to, price);
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

        translator = new Translator(userRoles, "JobTranslator"
                + Integer.toString(change), "pass", "desc", personalData,
                new HashSet<Translation>());
        translatorDAO.create(translator);

        Job job = new Job();
        jobDAO.create(job);

        Task task = new Task(Status.accepted, translator, Calendar
                .getInstance().getTime(), translation, 100, price, "desc", job,
                Rating.bad, "comm");
        Task task2 = new Task(Status.accepted, translator, Calendar
                .getInstance().getTime(), translation, 100, price, "desc2",
                job, Rating.bad, "comm2");
        taskDAO.create(task);
        taskDAO.create(task2);
        tasks = new HashSet<Task>();
        tasks.add(task2);
        tasks.add(task);
        change++;

    }

    @Test
    public void simpleCreateJob() {
        /*Job job = new Job(Status.accepted, Calendar.getInstance().getTime(),
                translation, 1000, price, "job descriptio", tasks);
        jobDAO.create(job);
        Assert.assertNotNull(job.getId());*/
    }

    @Test
    public void simpleGetJob() {
        // prepare data
        Job job = new Job(Status.accepted, Calendar.getInstance().getTime(),
                translation, 1000, price, "job descriptio", tasks);
        jobDAO.create(job);

        // test
        Job job2 = jobDAO.read(job.getId());
        Assert.assertEquals(job2.getDescription(), job.getDescription());
        Assert.assertEquals(job2.getAmount(), job.getAmount());
        Assert.assertEquals(job2.getStatus(), job.getStatus());
        Assert.assertEquals(job2.getTranslation().getId(), job.getTranslation()
                .getId());
        Assert.assertEquals(job2.getPrice().getId(), job.getPrice().getId());
    }

    @Test
    public void simpleUpdateJob() {
        // prepare data
        Job job = new Job(Status.accepted, Calendar.getInstance().getTime(),
                translation, 1000, price, "job descriptio", tasks);
        jobDAO.create(job);

        // test
        job.setAmount(123123);
        job.setDescription("new awesome description");
        job.setStatus(Status.resolved);
        job.setPrice(new Price(100, new CurrencyType("some currency"
                + Integer.toString(change))));

        jobDAO.update(job);
        Job job2 = jobDAO.read(job.getId());
        Assert.assertEquals(job2.getDescription(), job.getDescription());
        Assert.assertEquals(job2.getAmount(), job.getAmount());
        Assert.assertEquals(job2.getStatus(), job.getStatus());
        Assert.assertEquals(job2.getTranslation().getId(), job.getTranslation()
                .getId());
        Assert.assertEquals(job2.getPrice().getId(), job.getPrice().getId());
    }

    @Test
    public void simpleDeleteJob() {
        // prepare data
        Job job = new Job(Status.accepted, Calendar.getInstance().getTime(),
                translation, 1000, price, "job descriptio", tasks);
        jobDAO.create(job);

        // test
        jobDAO.delete(job);
        Assert.assertNull(jobDAO.read(job.getId()));
    }
}
