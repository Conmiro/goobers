package main.java.perms;

public class View implements PermissionCheck {

	@Override
	public boolean canViewBankBalance() {
		return true;
	}

	@Override
	public boolean canTransferBalance() {
		return false;
	}

	@Override
	public boolean canBillPay() {
		return false;
	}

	@Override
	public boolean canViewPolicy() {
		return true;
	}

	@Override
	public boolean canChangePolicy() {
		return false;
	}

}
