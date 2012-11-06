package org.pinboard.web;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Entity
public class Pin {
	@Id
	@GeneratedValue
	private Long id;
	private User CreatedBy;
	@Column(length=2000)
	private String message;
	private int visibility;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public User getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(User currentUser) {
		CreatedBy = currentUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
