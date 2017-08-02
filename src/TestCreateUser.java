import org.junit.Test;

public class TestCreateUser {

	@Test
	public void testCreateNewUser() {
		User accountOwner = new User("owner", "default", new Manage(), true);
		Account account = new DataAccount(null);
	}
	
}
