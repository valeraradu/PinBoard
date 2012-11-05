package org.pinboard.web;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PinEditor {

	private User CreatedBy;
	private String message;
	private Integer visibility=2;
	
	public User getCreatedBy() {
		return CreatedBy;
	}

	@Inject
	public void setCreatedBy(@LoggedIn User currentUser) {
		CreatedBy = currentUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Pin createNewPin() {
		Pin pin = new Pin();
		pin.setCreatedBy(CreatedBy);
		pin.setMessage(message);
		pin.setVisibility(visibility);
		return pin;
	}

	public Integer getVisibility() {
		return visibility;
	}

	public void setVisibility(Integer visibility) {		
			this.visibility = visibility;
	}
}
