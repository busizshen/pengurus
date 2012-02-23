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
public class TaskCRUDTest {

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
    private Job job;
    private static int change = 0;
    private Price price;

    @Before
    public void before() {
        Language to = new Language("Danish" + Integer.toString(change));
        Language from = new Language("French" + Integer.toString(change));
        languageDAO.create(from);
        languageDAO.create(to);

        TranslationType translationType = new TranslationType("description",
                "unit");
        translationTypeDAO.create(translationType);

        price = new Price(200 + change, new CurrencyType("$"
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

        translator = new Translator(userRoles, "TaskTranslator"
                + Integer.toString(change), "pass", "desc", personalData,
                new HashSet<Translation>());
        translatorDAO.create(translator);

        job = new Job();
        job.setAmount(10000);
        job.setDeadline(Calendar.getInstance().getTime());
        job.setDescription("description");
        job.setStatus(Status.closed);
        jobDAO.create(job);
        change++;

    }

    @Test
    public void simpleCreateTask() {
        Task task = new Task(Status.accepted, translator, Calendar
                .getInstance().getTime(), translation, 100, price, "desc", job,
                Rating.bad, "comm");
        taskDAO.create(task);
        Assert.assertNotNull(task.getId());
    }

    @Test
    public void simpleGetTask() {
        // prepare data
        Task task = new Task(Status.accepted, translator, Calendar
                .getInstance().getTime(), translation, 100, price, "desc"
                + Integer.toString(change), job, Rating.bad, "comm");
        taskDAO.create(task);

        // test
        Task task2 = taskDAO.read(task.getId());
        Assert.assertEquals(task2.getComment(), task.getComment());
        Assert.assertEquals(task2.getDescription(), task.getDescription());
        Assert.assertEquals(task2.getRating(), task.getRating());
        Assert.assertEquals(task2.getAmount(), task.getAmount());
        Assert.assertEquals(task2.getStatus(), task.getStatus());
        Assert.assertEquals(task2.getExpert().getUsername(), task.getExpert()
                .getUsername());
        Assert.assertEquals(task2.getJob().getId(), task.getJob().getId());
        Assert.assertEquals(task2.getPrice().getId(), task.getPrice().getId());
        Assert.assertEquals(task2.getTranslation().getId(), task
                .getTranslation().getId());

    }
    
    @Test
    public void simpleUpdateTask(){
        // prepare data
        Task task = new Task(Status.accepted, translator, Calendar
                .getInstance().getTime(), translation, 100, price, "desc"
                + Integer.toString(change), job, Rating.bad, "comm");
        taskDAO.create(task);
        
        // test
        task.setAmount(20000);
        task.setComment("new comment");
        task.setDescription("new description");
        task.setRating(Rating.good);
        task.setStatus(Status.resolved);
        taskDAO.update(task);
        
        Task task2 = taskDAO.read(task.getId());
        Assert.assertEquals(task2.getComment(), task.getComment());
        Assert.assertEquals(task2.getDescription(), task.getDescription());
        Assert.assertEquals(task2.getRating(), task.getRating());
        Assert.assertEquals(task2.getAmount(), task.getAmount());
        Assert.assertEquals(task2.getStatus(), task.getStatus());
        Assert.assertEquals(task2.getExpert().getUsername(), task.getExpert()
                .getUsername());
        Assert.assertEquals(task2.getJob().getId(), task.getJob().getId());
        Assert.assertEquals(task2.getPrice().getId(), task.getPrice().getId());
        Assert.assertEquals(task2.getTranslation().getId(), task
                .getTranslation().getId());
    }
    
    @Test
    public void simpleDeleteTask(){
        // prepare data
        Task task = new Task(Status.accepted, translator, Calendar
                .getInstance().getTime(), translation, 100, price, "desc"
                + Integer.toString(change), job, Rating.bad, "comm");
        taskDAO.create(task);
        
        // test
        taskDAO.delete(task);
        Assert.assertNull(taskDAO.read(task.getId()));
    }

}
