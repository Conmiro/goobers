public class DataAccount extends Account {

	public DataAccount(User accountOwner) {
		super(accountOwner);	
	}

	@Override
	public String transferFunds() {
		return "I transferred funds";
	}

	@Override
	public String payBills() {
		return "I paid bills";
	}
	

	
}
