package tests.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.model.User;

public class UserTests {

	User tUser = new User(0, "John", "Password");
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(0, tUser.getUserid());
		assertEquals("John", tUser.getUsername());
		assertEquals("Password", tUser.getPassword());
		assertEquals("[0,John,Password]", tUser.toString());
		
		tUser.setPassword("Pword");
		tUser.setUserid(5);
		tUser.setUsername("Test");
		
		assertEquals("Test", tUser.getUsername());
		assertEquals("Pword", tUser.getPassword());
		assertEquals(5, tUser.getUserid());
	}

}
