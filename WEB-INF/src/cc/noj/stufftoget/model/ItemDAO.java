package cc.noj.stufftoget.model;

/**
 * The UserDAO.  Here we have methods to manipulate the user
 * database.
 * 
 * @author Manoj Dayaram <mdayaram@andrew.cmu.edu>
 * @date February 25th, 2010
 * @class 15-437
 */

import java.util.ArrayList;

import org.mybeans.dao.DAOException;


import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import cc.noj.stufftoget.databeans.*;


public class ItemDAO {
	
	private BeanFactory<ItemBean> myFactory;
	
	public ItemDAO(String itemTableName) throws DAOException {
		BeanTable<ItemBean> itemTable;
		
		try{
			itemTable =	BeanTable.getInstance(ItemBean.class, itemTableName);
			if(!itemTable.exists())
				itemTable.create("id");

			myFactory = itemTable.getFactory();
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	
	public ItemBean[] list() throws DAOException {
		try {
			return myFactory.match();
		} catch(RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public ItemBean lookup(int id) throws DAOException {
		try{
			return myFactory.lookup(id);
		}catch (RollbackException e){
			throw new DAOException(e);
		}
	}
	
	public ItemBean[] lookupByUserAdded(int userID) throws DAOException {
		ArrayList<ItemBean> itemsList = new ArrayList<ItemBean>();
		try {
			ItemBean[] items = myFactory.match();
			for(int i = 0; i < items.length; i++){
				if (items[i].getAddedById() == userID) {
					itemsList.add(items[i]);
				}
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		
		return itemsList.toArray(new ItemBean[itemsList.size()]);
	}
	
	public ItemBean[] lookupByUserPurchased(int userID) throws DAOException {
		ArrayList<ItemBean> itemsList = new ArrayList<ItemBean>();
		try {
			ItemBean[] items = myFactory.match();
			for(int i = 0; i < items.length; i++){
				if (items[i].getPurchasedById() == userID) {
					itemsList.add(items[i]);
				}
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		
		return itemsList.toArray(new ItemBean[itemsList.size()]);
	}
	
	public ItemBean[] lookupByStatus(int status) throws DAOException {
		ArrayList<ItemBean> itemsList = new ArrayList<ItemBean>();
		try {
			ItemBean[] items = myFactory.match();
			for(int i = 0; i < items.length; i++){
				if (items[i].getStatus() == status) {
					itemsList.add(items[i]);
				}
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		
		return itemsList.toArray(new ItemBean[itemsList.size()]);
	}
	
	public ItemBean[] lookupByRank(int rank) throws DAOException {
		ArrayList<ItemBean> itemsList = new ArrayList<ItemBean>();
		try {
			ItemBean[] items = myFactory.match();
			for(int i = 0; i < items.length; i++){
				if (items[i].getRank() == rank) {
					itemsList.add(items[i]);
				}
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		
		return itemsList.toArray(new ItemBean[itemsList.size()]);
	}
	
	public ItemBean[] search(String keywords) throws DAOException {
		String[] words = keywords.split("[ ,:;><]");
		ArrayList<ItemBean> itemsList = new ArrayList<ItemBean>();
		try {
			ItemBean[] items = myFactory.match();
			for(int i = 0; i < items.length; i++){
				for (int j = 0; j < words.length; j++) {
					if (items[j].getTags().indexOf(words[j]) >= 0){
						itemsList.add(items[i]);
						break;
					}
					if (items[j].getComment().indexOf(words[j]) >= 0){
						itemsList.add(items[i]);
						break;
					}
					if (items[j].getName().indexOf(words[j]) >= 0){
						itemsList.add(items[i]);
						break;
					}
				}
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		
		return itemsList.toArray(new ItemBean[itemsList.size()]);
	}
	
	public ItemBean create(ItemBean itemData) throws DAOException {
		if(itemData == null)
			return null;
		
		ItemBean item;	
		
		try {
			Transaction.begin();
			
			item = myFactory.create();
			myFactory.copyInto(itemData, item);
			
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) 
				Transaction.rollback();
		}
		
		return item;
	}
	
	public ItemBean update(ItemBean itemData) throws DAOException {
		if(itemData == null)
			return null;
		
		ItemBean item;	
		
		try {
			Transaction.begin();
			
			item = myFactory.lookup(itemData.getId());
			if (item == null)
				throw new DAOException("Item doesn't exist.");
			
			myFactory.copyInto(itemData, item);
			
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) 
				Transaction.rollback();
		}
		
		return item;
	}

}
