package model.data.database;


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import model.data.level.Level;


public class SokobanDBManager extends CommonDBManager {
	
	public SokobanDBManager(){
		factory = new Configuration().configure().buildSessionFactory();
		transaction = null;
		
	}
	
	@Override
	public List getGameSessionTableForLevel(String levelName) {
		
		session = factory.openSession();
		transaction = session.beginTransaction();
		
		List table = (List) session.createQuery("FROM GameSession g WHERE g.levelName LIKE '" + levelName+"' ORDER BY g.steps,g.time DESC").list();
		session.close();
		return table;
		
	}
	
	@Override
	public List getGameSessionTableForUser(String username) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		
		List table = session.createQuery("FROM GameSession g WHERE g.username='" +username + "' ORDER BY g.steps,g.time,g.levelName DESC").list();
		session.close();
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
		if(session != null && session.isConnected())
			session.close();
		factory.close();
		
		
	}

	@Override
	public String getSolution(Level lvl) {
		// TODO //GET SOLUTION SOMEHOW
		return null;
	}
	
	

}
