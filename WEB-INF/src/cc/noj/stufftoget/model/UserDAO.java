package cc.noj.stufftoget.model;

/**
 * The UserDAO.  Here we have methods to manipulate the user
 * database.
 * 
 * @author Manoj Dayaram <mdayaram@andrew.cmu.edu>
 * @date February 25th, 2010
 * @class 15-437
 */

import org.mybeans.dao.DAOException;



import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;
import cc.noj.stufftoget.databeans.*;


public class UserDAO {
	
	private BeanFactory<UserBean> myFactory;
	
	public UserDAO(String userTableName) throws DAOException {
		BeanTable<UserBean> userTable;
		
		try{
			userTable =	BeanTable.getInstance(UserBean.class, userTableName);
			if(!userTable.exists())
				userTable.create("id");

			myFactory = userTable.getFactory();
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	
	/**
	 * Return the first user found with the given email.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws DAOException
	 */
	public UserBean lookup(String emailAddress) throws DAOException{
		if(emailAddress == null)
			return null;
		
		UserBean[] myUsers;
		MatchArg marg;
		marg = MatchArg.containsIgnoreCase("emailAddress", emailAddress);
		try {
			myUsers = myFactory.match(marg);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		
		if (myUsers.length == 0) {
			return null;
		} else {
			return myUsers[0];
		}
	}
	
	public UserBean lookup(int id) throws DAOException {
		try{
			return myFactory.lookup(id);
		}catch (RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public boolean exists(String emailAddress) throws DAOException{
		return lookup(emailAddress) != null;
	}
	
	public UserBean create(UserBean userData) throws DAOException {
		if(userData == null)
			return null;
		
		UserBean user;	
		
		try {
			Transaction.begin();
			
			if(lookup(userData.getEmailAddress()) != null){
				throw new DAOException("User with email addres '"
						+userData.getEmailAddress()+"' already exists.");
			}
			
			user = myFactory.create();
			myFactory.copyInto(userData, user);
			
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) 
				Transaction.rollback();
		}
		
		return user;
	}
	
	public UserBean updatePoints(int userID, int points) 
		throws DAOException{
		
		if(points < 0)
			throw new DAOException("Setting negative points to user: "+userID);
		
		UserBean user = null;
		try {
			Transaction.begin();
			user = myFactory.lookup(userID);
		
			if(user == null)
				throw new DAOException("No such user.");
		
			user.setPoints(points);
			Transaction.commit();
		} catch (RollbackException e){
			throw new DAOException (e);
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return user;
	}
	
	public UserBean addPoints(int userID, int points)
		throws DAOException {

		UserBean user = null;
		try {
			Transaction.begin();
			user = myFactory.lookup(userID);
			
			if(user == null)
				throw new DAOException("No such user.");
			
			user.setPoints(user.getPoints() + points);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException (e);
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return user;
	}
	
	public UserBean removePoints(int userID, int points)
		throws DAOException {

		UserBean user = null;
		try {
			Transaction.begin();
			user = myFactory.lookup(userID);
			
			if(user == null)
				throw new DAOException("No such user.");
			
			if (user.getPoints() < points) 
				throw new DAOException("Not enough points to remove.");
			
			user.setPoints(user.getPoints() - points);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException (e);
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return user;
	}
	
	public UserBean[] listUsers() throws DAOException {
		try {
			return myFactory.match();
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public UserBean[] listTopTen() throws DAOException {
		UserBean[] users = listUsers();
		for(int i = 0; i < users.length; i ++){
			for(int j = i; j < users.length; j++){
				if (users[j].getPoints() > users[i].getPoints()){
					UserBean tmp = users[j];
					users[j] = users[i];
					users[i] = tmp;
				}
			}
		}
		
		if(users.length <= 10){
			return users;
		}else{
			UserBean[] top = new UserBean[10];
			for(int i = 0; i < top.length; i++){
				top[i] = users[i];
			}
			return top;
		}
	}
	
	public UserBean updatePassword(int userID, String password) 
		throws DAOException{
		
		if(password == null)
			throw new DAOException("Setting null password to user: "+userID);
		
		UserBean user = null;
		try {
			Transaction.begin();
			user = myFactory.lookup(userID);
		
			if(user == null)
				throw new DAOException("No such user.");
		
			user.setPasswordHash(password);
			Transaction.commit();
		} catch (RollbackException e){
			throw new DAOException (e);
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return user;
	}

}
