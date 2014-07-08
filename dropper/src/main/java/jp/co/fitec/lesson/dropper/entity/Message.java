package jp.co.fitec.lesson.dropper.entity;

import java.util.Date;

public interface Message {

	public int getIsActive() ;
		
	public void setIsActive(int isActive) ;
	
	public long getNumber() ;

	public void setNumber(long number) ;

	public double getLatitude() ;

	public void setLatitude(double lattitude) ;

	public double getLongitude() ;

	public void setLongitude(double longitude) ;

	public Date getDateTime() ;

	public void setDateTime(Date dateTime) ;

	public String getMessage() ;

	public void setMessage(String message) ;

	public String getName() ;

	public void setName(String name) ;

	public String getDeleteKey() ;

	public void setDeleteKey(String deleteKey) ;
	
}

