package com.goobers.resource;

import com.goobers.model.Policy;
import com.goobers.model.User;
import com.goobers.perms.Manage;
import com.goobers.perms.View;

import java.util.ArrayList;

public interface Account {


	public void createAccountOwner() ;


	public void createNewAccountUser(String userName, String passPhrase, boolean fullAccess);
		

	
	public User getAccountOwner();

	public ArrayList<User> getAccountUsers();

	public void setAccountUsers(ArrayList<User> accountUsers);

	public void setAccountOwner(User accountOwner);
	
	public Double getAmount();

	public void setAmount(Double amount);

	public Policy getPolicyType();

	public void setPolicyType(Policy policyType);

	public String transferFunds(Double amount);

	public String payBills();

}
