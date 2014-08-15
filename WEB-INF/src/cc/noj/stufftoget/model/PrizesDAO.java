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


public class PrizesDAO {
	
	private BeanFactory<PrizesBean> myFactory;
	
	public PrizesDAO(String prizesTableName) throws DAOException {
		BeanTable<PrizesBean> prizesTable;
		
		try{
			prizesTable = BeanTable.getInstance(PrizesBean.class, prizesTableName);
			if(!prizesTable.exists())
				prizesTable.create("id");

			myFactory = prizesTable.getFactory();
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	
	public PrizesBean lookup(int id) throws DAOException {
		try{
			return myFactory.lookup(id);
		}catch (RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public boolean exists(String name) throws DAOException {
		if(name == null)
			return false;
		
		try {
			PrizesBean[] prize = myFactory.match(MatchArg.contains("name", name));
			return prize.length > 0;
		} catch (RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public PrizesBean[] list() throws DAOException {
		try{
			return myFactory.match();
		} catch (RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public PrizesBean create(PrizesBean prizesData) throws DAOException {
		if(prizesData == null)
			return null;
		
		PrizesBean prize;	
		
		try {
			Transaction.begin();
			
			prize = myFactory.create();
			myFactory.copyInto(prizesData, prize);
			
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) 
				Transaction.rollback();
		}
		
		return prize;
	}
	
	public PrizesBean updateByName(PrizesBean prizesData) throws DAOException {
		if(prizesData == null)
			return null;
		
		PrizesBean[] prize;	
		
		try {
			Transaction.begin();
			
			prize = myFactory.match(MatchArg.contains("name", prizesData.getName()));
			if(prize.length > 0)
				myFactory.copyInto(prizesData, prize[0]);
			
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) 
				Transaction.rollback();
		}
		
		if(prize.length > 0)
			return prize[0];
		return null;
	}

}
