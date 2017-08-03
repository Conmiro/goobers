package main.java.resource;

import main.java.model.Policy;
import main.java.model.User;

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
