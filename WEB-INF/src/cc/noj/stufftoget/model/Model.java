package cc.noj.stufftoget.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanTable;

import cc.noj.stufftoget.databeans.UserBean;

public class Model {
	
	private UserDAO myUserDAO;
	private ItemDAO myItemDAO;
	private CouponDAO myCouponDAO;
	private PrizesDAO myPrizesDAO;
	private RedeemedPrizesDAO myRedeemedDAO;
	private PhotoDAO myPhotoDAO;

	public Model(ServletConfig config) throws ServletException{
		String jdbcDriver = config.getInitParameter("jdbcDriverName");
		//String jdbcURL    = config.getInitParameter("jdbcURL");
		//For Heroku we use the environment variable.
		String jdbcURL = System.getenv("DATABASE_URL");
		BeanTable.useJDBC(jdbcDriver,jdbcURL);
		
		String userTableName = config.getInitParameter("user_table");
		String itemTableName = config.getInitParameter("item_table");
		String couponTableName = config.getInitParameter("coupon_table");
		String prizesTableName = config.getInitParameter("prizes_table");
		String redeemedTableName = config.getInitParameter("redeemed_table");
		String photoTableName = config.getInitParameter("photo_table");
		
		
		try {
			myUserDAO = new UserDAO(userTableName);
			myItemDAO = new ItemDAO(itemTableName);
			myCouponDAO = new CouponDAO(couponTableName);
			myPrizesDAO = new PrizesDAO(prizesTableName);
			myRedeemedDAO = new RedeemedPrizesDAO(redeemedTableName);
			myPhotoDAO = new PhotoDAO(photoTableName);
		} catch (DAOException e){
			throw new ServletException(e);
		}
		
		try {
			initUserNoj(config);
		} catch (DAOException e){
			try {
				UserBean noj = myUserDAO.lookup("i.am.noj@gmail.com");
				myUserDAO.updatePassword(noj.getId(), UserBean.hash(config.getInitParameter("noj_pass")));
			} catch (DAOException e1) {
				throw new ServletException(e1);
			}
		}
	}
	
	private void initUserNoj(ServletConfig config) throws DAOException {
		UserBean noj = new UserBean();
		noj.setEmailAddress(config.getInitParameter("noj_email"));
		noj.setFirstName(config.getInitParameter("noj_firstname"));
		noj.setLastName(config.getInitParameter("noj_lastname"));
		noj.setPasswordHash(UserBean.hash(config.getInitParameter("noj_pass")));
		myUserDAO.create(noj);
	}

	public UserDAO getUserDAO() {
		return myUserDAO;
	}

	public ItemDAO getItemDAO() {
		return myItemDAO;
	}

	public CouponDAO getCouponDAO() {
		return myCouponDAO;
	}

	public PrizesDAO getPrizesDAO() {
		return myPrizesDAO;
	}

	public RedeemedPrizesDAO getRedeemedDAO() {
		return myRedeemedDAO;
	}
	
	public PhotoDAO getPhotoDAO() {
		return myPhotoDAO;
	}
}