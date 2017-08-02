
public class SecureAccount extends Account {

	public SecureAccount(User accountOwner) {
		super(accountOwner);
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
