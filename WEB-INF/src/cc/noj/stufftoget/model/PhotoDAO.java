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


public class PhotoDAO {
	
	private BeanFactory<PhotoBean> myFactory;
	
	public PhotoDAO(String photoTableName) throws DAOException {
		BeanTable<PhotoBean> photoTable;
		
		try{
			photoTable = BeanTable.getInstance(PhotoBean.class, photoTableName);
			if(!photoTable.exists())
				photoTable.create("id");

			myFactory = photoTable.getFactory();
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	
	public PhotoBean lookup(int id) throws DAOException {
		try{
			return myFactory.lookup(id);
		}catch (RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public PhotoBean create(PhotoBean photoData) throws DAOException {
		if(photoData == null)
			return null;
		
		PhotoBean photo;	
		
		try {
			Transaction.begin();
			
			photo = myFactory.create();
			myFactory.copyInto(photoData, photo);
			
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) 
				Transaction.rollback();
		}
		
		return photo;
	}

}
