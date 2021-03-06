package org.pinboard.web;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {

	@Id
	private String username;
	private String password;
	
	public User() {
	}
	
	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*@Override
	public boolean equals(Object o) {

		if (this.username.equals(((User) o).getUsername())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return username.hashCode();
	}*/
}
