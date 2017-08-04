package main.java.perms;

public interface PermissionCheck {
//	Bank
	public abstract boolean canViewBankBalance();
	public abstract boolean canTransferBalance();
	public abstract boolean canBillPay();
	
//	Insurance
	public abstract boolean canViewPolicy();
	public abstract boolean canChangePolicy();

//	Extras
	public abstract boolean canAddUser();
	public abstract boolean canRemoveUser();
}
