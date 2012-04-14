package com.pengurus.crm.daos.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.QuoteDAO;
import com.pengurus.crm.entities.Quote;

public class QuoteDAOImpl extends GenericDAOImpl<Quote> implements
        QuoteDAO {

    protected static final Logger log = LoggerFactory
            .getLogger(QuoteDAOImpl.class);

    public QuoteDAOImpl() {
        this.type = Quote.class;
    }

    @Override
    public Quote getById(long id) {
        try{
            Session session = getHibernateTemplate().getSessionFactory().openSession();
            Quote quote = (Quote) session.get(type, id);
            quote.getJobs().size();
            session.close();
            return quote;
        } catch(Exception e) {
            return null;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Quote> loadAllBySupervisorId(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String hql = "select q from Quote q " +
				"where q.supervisor = " + id;
		Query query = session.createQuery(hql);
		List<Quote> quotes = query.list();
		return quotes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quote> loadAllByClientId(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String hql = "select q from Quote q " +
				"where q.client = " + id;
		Query query = session.createQuery(hql);
		List<Quote> quotes = query.list();
		return quotes;
	}

}