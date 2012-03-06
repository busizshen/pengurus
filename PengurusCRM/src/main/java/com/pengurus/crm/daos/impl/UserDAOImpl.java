package com.pengurus.crm.daos.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.BusinessClient;
import com.pengurus.crm.entities.Client;
import com.pengurus.crm.entities.IndividualClient;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.entities.Translator;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.entities.Worker;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	protected static final Logger log = LoggerFactory
			.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		this.type = User.class;
	}

	@Override
	public User findByUsername(String username)
			throws UsernameNotFoundException {
		try {
			String query = "select u from User u where u.username = '"
					+ username + "'";
			return (User) getHibernateTemplate().find(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new UsernameNotFoundException(username);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session.createQuery("from User");
			List<User> list = query.list();
			for (User u : list) {
				u.getAuthorities().size();
				if (u instanceof Worker) {
					Worker worker = (Worker) u;
					worker.getPersonalData().getEmail();
					worker.getPersonalData().getPhoneNumber();
					if (u instanceof Translator) {
						Translator translator = (Translator) u;
						translator.getTranslations().size();
					}
				} else if (u instanceof Client) {
					if (u instanceof BusinessClient) {
						BusinessClient businessClient = (BusinessClient) u;
						businessClient.getAgents().size();
						for (PersonalData pd : businessClient.getAgents()) {
							pd.getPhoneNumber().size();
							pd.getEmail().size();
						}
					}
					if (u instanceof IndividualClient) {
						IndividualClient individualClient = (IndividualClient) u;
						individualClient.getPersonalData().getEmail().size();
						individualClient.getPersonalData().getPhoneNumber()
								.size();
					}
				}
			}
			session.close();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

}
