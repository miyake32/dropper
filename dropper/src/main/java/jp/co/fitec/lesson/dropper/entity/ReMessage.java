package jp.co.fitec.lesson.dropper.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="remessage")
public class ReMessage implements Message {
	
	// 返信先
	@Column(name="reply_to")
	@ManyToOne
	private long replyTo;
	
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
	
	public ReMessage() {}

	public ReMessage(long replyTo, long number, double latitude,
			double longitude, Date dateTime, String message, String name,
			String deleteKey, int isActive) {
		super();
		this.replyTo = replyTo;
		this.number = number;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dateTime = dateTime;
		this.message = message;
		this.name = name;
		this.deleteKey = deleteKey;
		this.isActive = isActive;
	}

	public long getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(long replyTo) {
		this.replyTo = replyTo;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	
	
}
