package jp.co.fitec.lesson.dropper.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="remessage")
public class ReMessage implements Message, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -322767708204387982L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reply_to")
	private SimpleMessage parentMessage;
	
//	// 返信先
//	@Column(name="reply_to")
//	private long replyTo;
	
	@Id
	private long number;
	private double latitude;
	private double longitude;
	
	@Column(name="date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	private String message;
	
	@Column(name="user_name")
	private String name;
	
	@Column(name="delete_key")
	private String deleteKey;
	
	@Column(name="is_active")
	private int isActive;
	
	public ReMessage() {}



	@Override
	public String toString() {
		return "ReMessage [number=" + number + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", dateTime=" + dateTime
				+ ", message=" + message + ", name=" + name + ", deleteKey="
				+ deleteKey + ", isActive=" + isActive + "]";
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

	public SimpleMessage getParentMessage() {
		return parentMessage;
	}



	public void setParentMessage(SimpleMessage parentMessage) {
		this.parentMessage = parentMessage;
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
