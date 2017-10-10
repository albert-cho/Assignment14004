package tests.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.tables.FeeTable;

public class FeeTableTests {

	FeeTable nFtable = FeeTable.getInstance();
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(0, nFtable.lookupfee(1));
		assertEquals(true, nFtable.lookup(1));
		
		nFtable.applyfee(1, 9999999);
		
		assertEquals(161, nFtable.lookupfee(1));
		assertEquals(false, nFtable.lookup(1));
		
		assertEquals("success", nFtable.payfine(1));
		assertEquals("Borrowing Items Exist", nFtable.payfine(0));
		
		assertEquals(0, nFtable.lookupfee(1));
		assertEquals(true, nFtable.lookup(1));
	}

}
