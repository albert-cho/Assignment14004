package tests.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.model.Item;

public class ItemTests {

	Item tItem = new Item(0, "Test", "0");
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(0, tItem.getItemid());
		assertEquals("Test", tItem.getISBN());
		assertEquals("0", tItem.getCopynumber());
		assertEquals("[0,Test,0]", tItem.toString());
		
		tItem.setCopynumber("1");
		tItem.setISBN("T1");
		tItem.setItemid(1);
		
		assertEquals("1", tItem.getCopynumber());
		assertEquals("T1", tItem.getISBN());
		assertEquals(1, tItem.getItemid());
	}

}
