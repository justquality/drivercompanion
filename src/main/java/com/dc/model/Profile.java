package com.dc.model;

public class Profile {
	
	private User user;
	private Driver driver;
	private Companion companion;
	private String newPassword;
	private String newPasswordConfirm;
	
	public Profile() {}

	public Profile(User user, Driver driver) {
		this.user = user;
		this.driver = driver;
	}
	
	public Profile(User user, Companion companion) {
		this.user = user;
		this.companion = companion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Companion getCompanion() {
		return companion;
	}

	public void setCompanion(Companion companion) {
		this.companion = companion;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
	
}
