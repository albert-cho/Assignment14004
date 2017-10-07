package server.logic.tables.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.tables.UserTable;

public class UserTableTests {

	UserTable tUtable = UserTable.getInstance();
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(-1, tUtable.lookup("Test"));
		assertEquals(false, tUtable.lookup(5));
		assertEquals(true, tUtable.createuser("Test", "Password"));
		
		assertEquals(5, tUtable.lookup("Test"));
		assertEquals(true, tUtable.lookup(5));
		assertEquals(false, tUtable.createuser("Test", "Password"));
		
		assertEquals(0, tUtable.checkUser("Test", "Password"));
		assertEquals(1, tUtable.checkUser("Test", ""));
		assertEquals(2, tUtable.checkUser("Tests", "Hello"));
	}

}
