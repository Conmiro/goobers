
public class User {
	private String userName;
	private String passPhrase;
	private PermissionCheck access;
	private boolean owner;
	
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
}
