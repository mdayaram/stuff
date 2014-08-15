package cc.noj.stufftoget.databeans;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PhotoBean {
	public static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList( new String[] {
			".jpg", ".gif", ".JPG"
	} ));
	
	private static final int BOGUS_ID = -1;

	private final int id;
	private byte[] bytes;
	private String contentType;

	public PhotoBean(int id) {
		this.id = id;
	}
	
	public PhotoBean() {
		this.id = BOGUS_ID;
	}
	
    public byte[] getBytes() { 
    	return bytes;
    }
    
    public String getContentType() {
    	return contentType;
    }
    
    public int getId() { 
    	return id;
    }
    
    public void setBytes(byte[] a) { 
    	bytes = a; 
    }
    
    public void setContentType(String s) {
    	contentType = s;
    }
}

