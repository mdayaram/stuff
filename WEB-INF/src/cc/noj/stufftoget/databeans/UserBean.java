package cc.noj.stufftoget.databeans;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Manoj Dayaram <mdayaram@andrew.cmu.edu>
 * @class 15-437
 */

public class UserBean {
	
	private static final int BOGUS_ID = -1;

	private final int id;
	
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String passwordHash;
	private int points;
	
	public UserBean(int id){
		this.id = id;
		this.points = 0;
	}
	
	public UserBean(){
		id = BOGUS_ID;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress.trim();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String password) {
		this.passwordHash = password.trim();
	}

	public int getId() {
		return id;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

	public boolean checkPassword(String password2) {
		return passwordHash.equals(hash(password2.trim()));
	}
	
	public static String hash(String string){
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return String.format("%1$032X", string.hashCode());
		}
		byte[] data = (string).getBytes();   //issues when trying to use salt...
		m.update(data,0,data.length);
		BigInteger i = new BigInteger(1,m.digest());
		return String.format("%1$032X", i);

	}
	
}
