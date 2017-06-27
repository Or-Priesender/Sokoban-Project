package model.data.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class CommonDBManager implements DataBaseManager {
		Session session;
		SessionFactory factory;
		Transaction transaction;

	}

	
	


