package org.pinboard.web;

public class Document {
	
	private User CreatedBy;

	public User getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(User currentUser) {
		CreatedBy = currentUser;
	}
}
