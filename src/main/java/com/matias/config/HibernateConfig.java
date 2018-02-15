package com.matias.config;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

@Component
public class HibernateConfig {
	
	private final String FILE_CONFIG = "hibernate.cfg.xml";
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;
	private Session session;
	
	@PostConstruct
	private void buildSessionFactory() {
		Configuration configObj = new Configuration();
		configObj.configure(FILE_CONFIG);
		configObj.addAnnotatedClass(com.matias.model.impl.DnaHumano.class);
		configObj.addAnnotatedClass(com.matias.model.impl.DnaMutante.class);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
		sessionFactory = configObj.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
	}
	
	private void openSessionIfClose() {
		if (!session.isOpen()) {
			if (sessionFactory.isClosed()) {
				buildSessionFactory();
			} else {
				session = sessionFactory.openSession();
			}
		}
	}
	
	public Session getCurrentSession() {
		openSessionIfClose();
		return session;
	}
	
}
