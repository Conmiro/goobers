package com.goobers.model;


import com.goobers.perms.PermissionCheck;

public class User {
	private String userName;
	private String passPhrase;
	private String pin;
	private PermissionCheck access;
	private boolean owner;
	
	public User(String userName, String passPhrase, String pin, PermissionCheck access, boolean isOwner) {
		this.userName = userName;
		this.passPhrase = passPhrase;
		this.access = access;
		this.owner = isOwner;
		this.pin = pin;
		
	}

	@Deprecated
	public User(String userName, String passPhrase, PermissionCheck access, boolean isOwner) {
		this.userName = userName;
		this.passPhrase = passPhrase;
		this.access = access;
		this.owner = isOwner;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassPhrase() {
		return passPhrase;
	}

	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	public PermissionCheck getAccess() {
		return access;
	}

	public void setAccess(PermissionCheck access) {
		this.access = access;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
}
