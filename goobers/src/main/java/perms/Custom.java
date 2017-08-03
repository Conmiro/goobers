package main.java.perms;

public class Custom implements PermissionCheck {
	
	private boolean viewBankBalance;
	private boolean transferBalance;
	private boolean billPay;
	private boolean viewPolicy;
	private boolean changePolicy;

	@Override
	public boolean canViewBankBalance() {
		return viewBankBalance;
	}
	
	public void setViewBankBalance(boolean canViewBankBalance) {
		this.viewBankBalance = canViewBankBalance;
	}

	@Override
	public boolean canTransferBalance() {
		return transferBalance;
	}
	
	public void setTransferBalance(boolean canTransferBalance) {
		this.transferBalance = canTransferBalance;
	}

	@Override
	public boolean canBillPay() {
		return billPay;
	}
	
	public void setBillPay(boolean canBillPay) {
		this.billPay = canBillPay;
	}

	@Override
	public boolean canViewPolicy() {
		return viewPolicy;
	}
	
	public void setViewPolicy(boolean canSetPolicy) {
		this.viewPolicy = canSetPolicy;
	}

	@Override
	public boolean canChangePolicy() {
		return changePolicy;
	}
	
	public void setChangePolicy(boolean canChangePolicy) {
		this.changePolicy = canChangePolicy;
	}

}
