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
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import cc.noj.stufftoget.databeans.*;


public class RedeemedPrizesDAO {
	
	private BeanFactory<RedeemedPrizesBean> myFactory;
	
	public RedeemedPrizesDAO(String redeemedPrizesTableName) throws DAOException {
		BeanTable<RedeemedPrizesBean> redeemedPrizesTable;
		
		try{
			redeemedPrizesTable = BeanTable.getInstance(RedeemedPrizesBean.class, redeemedPrizesTableName);
			if(!redeemedPrizesTable.exists())
				redeemedPrizesTable.create("id");

			myFactory = redeemedPrizesTable.getFactory();
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	
	public RedeemedPrizesBean lookup(int id) throws DAOException {
		try{
			return myFactory.lookup(id);
		}catch (RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public RedeemedPrizesBean create(RedeemedPrizesBean redeemedPrizesData) throws DAOException {
		if(redeemedPrizesData == null)
			return null;
		
		RedeemedPrizesBean prize;	
		
		try {
			Transaction.begin();
			
			prize = myFactory.create();
			myFactory.copyInto(redeemedPrizesData, prize);
			
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) 
				Transaction.rollback();
		}
		
		return prize;
	}

}
