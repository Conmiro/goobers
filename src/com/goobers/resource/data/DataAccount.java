package com.goobers.resource.data;

import com.goobers.resource.Account;
import com.goobers.model.Policy;
import com.goobers.model.User;

import java.util.ArrayList;

public class DataAccount implements Account {


	private User accountOwner;
	private ArrayList<User> accountUsers;
	private Double amount;
	private Policy policyType;

	public DataAccount(User accountOwner) {
		super();
	}


	@Override
	public void createAccountOwner() {

	}

	@Override
	public void createNewAccountUser(String userName, String passPhrase, boolean fullAccess) {

	}

	@Override
	public User getAccountOwner() {
		return null;
	}

	@Override
	public ArrayList<User> getAccountUsers() {
		return null;
	}

	@Override
	public void setAccountUsers(ArrayList<User> accountUsers) {

	}

	@Override
	public void setAccountOwner(User accountOwner) {

	}

	@Override
	public Double getAmount() {
		return null;
	}

	@Override
	public void setAmount(Double amount) {

	}

	@Override
	public Policy getPolicyType() {
		return null;
	}

	@Override
	public void setPolicyType(Policy policyType) {

	}

	@Override
	public String transferFunds(Double amount) {
		return null;
	}

	@Override
	public String payBills() {
		return null;
	}
}
