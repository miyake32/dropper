package jp.co.fitec.lesson.dropper.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="message")
public class SimpleMessage implements Message {
	
	@Id
	private long number;
	private double latitude;
	private double longitude;
	
	@Column(name="date_time")
	@Temporal(TemporalType.DATE)
	private Date dateTime;
	private String message;
	
	@Column(name="user_name")
	private String name;
	
	@Column(name="delete_key")
	private String deleteKey;
	
	@Column(name="is_active")
	private int isActive;
	
	public SimpleMessage() {}


	public SimpleMessage(long number, double lattitude, double longitude,
			Date dateTime, String message, String name, String deleteKey,
			int isActive) {
		super();
		this.number = number;
		this.latitude = lattitude;
		this.longitude = longitude;
		this.dateTime = dateTime;
		this.message = message;
		this.name = name;
		this.deleteKey = deleteKey;
		this.isActive = isActive;
	}


	public int getIsActive() {
		return isActive;
	}


	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}


	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public double getLattitude() {
		return latitude;
	}

	public void setLattitude(double lattitude) {
		this.latitude = lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeleteKey() {
		return deleteKey;
	}

	public void setDeleteKey(String deleteKey) {
		this.deleteKey = deleteKey;
	}

}