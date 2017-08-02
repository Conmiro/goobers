/**
 * This is just an example to help us figure out what we need in the model
 * @author bree
 *
 */
public class Controller {
	
	public static void main(String[] args) {
		User fakeUser = new User("fakename", "passphrase", new Manage(), true);
		Account fakeAccount = new SecureAccount(fakeUser);
		new Controller(fakeAccount);
	}
	
	private Account currentAccount;
	private User currentUser;
//	TODO ADD REAL INTERFACE!!!!!
	private String command;
	
	
	public Controller(Account currentAccount) {
//		TODO log in to account
		this.currentAccount = currentAccount;
		
		if(command.equals("Add user to account")) {
			String userName = getUserNameWithAlexa();
			String passPhrase = getUserPassPhrase();
			boolean fullAccess = getIfFullAccess();

			currentAccount.createNewAccountUser(userName, passPhrase, fullAccess);
		}
		
		if(command.equalsIgnoreCase("Change user access level")) {
			boolean fullAccess = getIfFullAccess();
			
		}
		
		if(command.equals("transfer money")) {
			if(currentUser.getAccess().canTransferBalance()) {
//				make the transfer
				Double amount = getAmountToTransfer();
				currentAccount.transferFunds(amount);
			} else {
//				rejected!
			}
		}
		
		
	}
	
}
