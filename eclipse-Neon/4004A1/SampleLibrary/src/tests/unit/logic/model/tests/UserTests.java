package tests.unit.logic.model.tests;

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
	}

}
