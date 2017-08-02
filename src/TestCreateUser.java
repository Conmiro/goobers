import static org.junit.Assert.*;

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
