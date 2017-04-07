package model.data.database;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.data.level.Level;


public class SokobanDBManager implements DataBaseManager {

	Session session;
	SessionFactory factory;
	Transaction transaction;
	

	public SokobanDBManager(){
		factory = new Configuration().configure().buildSessionFactory();
		transaction = null;
		
	}
	@Override//doesnt work
	public boolean userExists(String username) {
		boolean calledFromManager = false;
		boolean result = false;
		if(!session.isOpen() && !transaction.isActive()){
		session = factory.openSession();
		transaction = session.beginTransaction();
		calledFromManager = true;
		}
		
		User u = session.get(User.class, username);
		if(u != null)
			result = true;
		else
			result = false;
		if(calledFromManager)
			return result;
		else{
			session.close();
			return result;
		}
		
	}
	
	@Override
	public List getGameSessionTableForLevel(Level lvl) {
		
		session = factory.openSession();
		transaction = session.beginTransaction();
		
		List table = session.createQuery("FROM GameSession g").list();//TODO how to add with level name ??!
		return table;
		
	}

	@Override
	public void saveUserAndLevel(User user, Level lvl) {
				try{
		session = factory.openSession();
		transaction = session.beginTransaction();
		
		session.save(user);
		Level l = (Level) session.get(Level.class, lvl.getLevelName());
		if(l == null)
			session.save(lvl);
		
		GameSession game = new GameSession(user,lvl);
		
		
		
		session.save(game);
		transaction.commit();
		}
		catch(HibernateException e){
			
			transaction.rollback();
			
		}
		finally{
			session.close();
		}
	
		
		
	}
	
	
	
	@Override
	public void addUser(User u) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		
		int userId = (int) session.save(u);
		transaction.commit();
		session.close();

	}
	@Override
	public void addLevel(Level l) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		
		session.save(l);
		transaction.commit();
		session.close();
	}
	@Override
	public void deleteUser(int userId) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		
		User u = session.get(User.class, userId);
		session.delete(u);
		transaction.commit();
		session.close();
		
	}
	@Override
	public void deleteLevel(String levelName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void closeDB() {
		
		factory.close();
		
		
	}
	
	

}
