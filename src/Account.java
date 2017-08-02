import java.util.ArrayList;

public abstract class Account {

	private User accountOwner;
	private ArrayList<User> accountUsers;
	private Double amount;
	private Policy policyType;

	public Account(User accountOwner) {
		this.accountOwner = accountOwner;
		this.accountUsers = new ArrayList<>();
	}

	public void createAccountOwner() {

	}

	public void createNewAccountUser(String userName, String passPhrase, boolean fullAccess) {
		if(fullAccess) {
			accountUsers.add(new User(userName, passPhrase, new Manage(), false));
		} else {
			accountUsers.add(new User(userName, passPhrase, new View(), false));
		}
		
	}
	
	public User getAccountOwner() {
		return this.accountOwner;
	}

	public ArrayList<User> getAccountUsers() {
		return accountUsers;
	}

	public void setAccountUsers(ArrayList<User> accountUsers) {
		this.accountUsers = accountUsers;
	}

	public void setAccountOwner(User accountOwner) {
		this.accountOwner = accountOwner;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Policy getPolicyType() {
		return policyType;
	}

	public void setPolicyType(Policy policyType) {
		this.policyType = policyType;
	}

	public abstract String transferFunds(Double amount);

	public abstract String payBills();

}
