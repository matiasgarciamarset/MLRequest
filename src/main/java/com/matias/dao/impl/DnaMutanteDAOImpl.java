package com.matias.dao.impl;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matias.config.HibernateConfig;
import com.matias.dao.DnaDAO;
import com.matias.dao.error.DBException;
import com.matias.model.Dna;
import com.matias.service.type.TipoDna;

@Component
public class DnaMutanteDAOImpl implements DnaDAO {
	
	@Autowired
	private HibernateConfig config;

	@Override
	public void save(Dna name) throws DBException {
		Session session = null;
		try{
			session = config.getCurrentSession();
			session.beginTransaction();
			session.save(name);
			session.getTransaction().commit();
		} catch(Exception sqlException) {
			if(null != session.getTransaction()) {
				session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
			throw new DBException(sqlException);
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public Long count() throws DBException {
		Long count;
		Session session = null;
		try{
			session = config.getCurrentSession();
			session.beginTransaction();
			count = (Long) session.createCriteria("com.matias.model.impl.DnaMutante").setProjection(Projections.rowCount())
					.setCacheable(true).setCacheRegion("MUTANT_COUNT_CACHE").uniqueResult();
			session.getTransaction().commit();
			return count;
		} catch(Exception sqlException) {
			if(null != session.getTransaction()) {
				session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
			throw new DBException(sqlException);
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public TipoDna support() {
		return TipoDna.MUTANTE;
	}

}
