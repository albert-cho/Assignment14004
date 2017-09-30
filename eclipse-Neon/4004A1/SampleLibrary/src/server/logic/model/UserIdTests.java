package server.logic.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserIdTests {

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(0, User.getUserid());
		assertEquals("John", User.getUsername());
		assertEquals("Password", User.getPassword());
	}

}
