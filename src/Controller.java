
public class Controller {
	
	private Account currentAccount;
	private User currentUser;
//	TODO FIX THIS!!!!!
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
		
		
	}
	
}
