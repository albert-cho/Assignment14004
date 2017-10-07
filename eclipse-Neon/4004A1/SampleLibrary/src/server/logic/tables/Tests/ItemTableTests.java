package server.logic.tables.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.tables.ItemTable;

public class ItemTableTests {

	ItemTable tItable = ItemTable.getInstance();
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(true, tItable.createitem("9781611687910"));
		assertEquals(false, tItable.createitem("Test"));
		
		assertEquals(true, tItable.lookup("9781442668584", "1"));
		assertEquals(false, tItable.lookup("Test", "0"));
	}

}
