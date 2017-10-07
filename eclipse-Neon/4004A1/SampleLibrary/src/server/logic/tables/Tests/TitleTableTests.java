package server.logic.tables.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.tables.TitleTable;

public class TitleTableTests {
	
	TitleTable tTable = TitleTable.getInstance();
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(true, tTable.createtitle("ISBN", "Test"));
		assertEquals(false, tTable.createtitle("ISBN", "Test"));
		
		assertEquals(true, tTable.lookup("ISBN"));
		assertEquals(false, tTable.lookup("Test"));
		
		assertEquals("success", tTable.delete("ISBN"));
		assertEquals("Active Loan Exists", tTable.delete("9781442668584"));
		assertEquals("The Title Does Not Exist", tTable.delete("ISBN"));
	}

}
