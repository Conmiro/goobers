package main.java.perms;

public class Manage implements PermissionCheck{

	@Override
	public boolean canViewBankBalance() {
		return true;
	}

	@Override
	public boolean canTransferBalance() {
		return true;
	}

	@Override
	public boolean canBillPay() {
		return true;
	}

	@Override
	public boolean canViewPolicy() {
		return true;
	}

	@Override
	public boolean canChangePolicy() {
		return true;
	}

	@Override
	public boolean canAddUser() {
		return true;
	}

	@Override
	public boolean canRemoveUser() {
		return true;
	}

}
