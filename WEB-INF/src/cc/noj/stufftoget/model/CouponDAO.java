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


public class CouponDAO {
	
	private BeanFactory<CouponBean> myFactory;
	
	public CouponDAO(String itemTableName) throws DAOException {
		BeanTable<CouponBean> couponTable;
		
		try{
			couponTable = BeanTable.getInstance(CouponBean.class, itemTableName);
			if(!couponTable.exists())
				couponTable.create("code");

			myFactory = couponTable.getFactory();
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	
	public CouponBean lookup(String code) throws DAOException {
		try{
			return myFactory.lookup(code);
		}catch (RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public CouponBean create(CouponBean couponData) throws DAOException {
		if(couponData == null)
			return null;
		
		CouponBean coupon;	
		
		try {
			Transaction.begin();
			
			coupon = myFactory.create();
			myFactory.copyInto(couponData, coupon);
			
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) 
				Transaction.rollback();
		}
		
		return coupon;
	}

}
