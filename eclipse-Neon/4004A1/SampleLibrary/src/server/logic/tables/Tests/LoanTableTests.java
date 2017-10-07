package server.logic.tables.Tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import server.logic.tables.FeeTable;
import server.logic.tables.LoanTable;

public class LoanTableTests {

	LoanTable tLtable = LoanTable.getInstance();
	FeeTable nFtable = FeeTable.getInstance();
	Date tDate = new Date();
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals("success", tLtable.createloan(1, "9781611687910", "1", tDate));
		assertEquals("User Invalid", tLtable.createloan(6, "9781611687910", "1", tDate));
		assertEquals("ISBN Invalid", tLtable.createloan(1, "1", "1", tDate));
		assertEquals("Copynumber Invalid", tLtable.createloan(1, "9781611687910", "0", tDate));
		assertEquals("The Item is Not Available", tLtable.createloan(1, "9781611687910", "1", tDate));
		assertEquals("Outstanding Fee Exists", tLtable.createloan(0, "9781442616899", "1", tDate));
		tLtable.returnItem(0, "9781442668584", "1", tDate);
		tLtable.createloan(1, "9781442667181", "1", tDate);
		assertEquals("success", tLtable.createloan(1, "9781442616899", "1", tDate));
		assertEquals("The Maximun Number of Items is Reached", tLtable.createloan(1, "9781442668584", "1", tDate));
		
		
		assertEquals(false, tLtable.lookup(1, "9781611687910", "1"));
		assertEquals(true, tLtable.lookup(1, "1", "1"));
		
		assertEquals(true, tLtable.checkLimit(2));
		assertEquals(false, tLtable.checkLimit(1));
		
		assertEquals("The Maximun Number of Items is Reached", tLtable.renewal(1, "9781611687910", "1", tDate));
		tLtable.returnItem(1, "9781442616899", "1", tDate);
		assertEquals("success", tLtable.renewal(1, "9781611687910", "1", tDate));
		assertEquals("Renewed Item More Than Once for the Same Loan", tLtable.renewal(1, "9781611687910", "1", tDate));
		assertEquals("The loan does not exist", tLtable.renewal(1, "1", "1", tDate));
		
		nFtable.applyfee(1, 9999999);
		assertEquals("Outstanding Fee Exists", tLtable.renewal(1, "9781442667181", "1", tDate));
		
		assertEquals(false, tLtable.checkLoan("9781611687910", "1"));
		assertEquals(false, tLtable.checkLoan("9781611687910"));
		
		assertEquals("success", tLtable.returnItem(1, "9781611687910", "1", tDate));
		assertEquals("The Loan Does Not Exist", tLtable.returnItem(1, "9781611687910", "1", tDate));
		
		assertEquals(true, tLtable.looklimit(2));
		assertEquals(false, tLtable.looklimit(1));
		
		assertEquals(true, tLtable.checkLoan("9781611687910", "1"));
		assertEquals(false, tLtable.checkLoan("9781442667181", "1"));
		assertEquals(true, tLtable.checkLoan("9781611687910"));
		assertEquals(false, tLtable.checkLoan("9781442667181"));
	}

}
