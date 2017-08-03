package com.goobers.db;

import static org.junit.Assert.*;

import com.goobers.resource.data.DataAccount;
import com.goobers.perms.Manage;
import com.goobers.resource.Account;
import com.goobers.model.User;
import org.junit.Test;

public class TestCreateUser {

	@Test
	public void testCreateNewUser() {
		User accountOwner = new User("owner", "default", new Manage(), true);
		Account account = new DataAccount(accountOwner);
		
		assertEquals("owner", account.getAccountOwner().getUserName());
		assertTrue(accountOwner.getAccess().canBillPay());
		
		
	}
	
}
