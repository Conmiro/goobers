package main.java;

import main.java.model.User;
import main.java.perms.Manage;
import main.java.resource.Account;
import main.java.resource.data.DataAccount;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCreateUser {

	@Test
	public void testCreateNewUser() {
		User accountOwner = new User("owner", "default", new Manage(), true);
		Account account = new DataAccount(accountOwner);
		
		assertEquals("owner", account.getAccountOwner().getUserName());
		assertTrue(accountOwner.getAccess().canBillPay());
		
		
	}
	
}
