package server.logic.tables.Tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import server.logic.tables.FeeTable;
import server.logic.tables.LoanTable;
import server.logic.tables.UserTable;

public class UserTableTests {

	UserTable tUtable = UserTable.getInstance();
	LoanTable tLtable = LoanTable.getInstance();
	FeeTable tFtable = FeeTable.getInstance();
	Date tDate = new Date();
	
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
		
		assertEquals("success", tUtable.delete(5));
		assertEquals("Outstanding Fee Exists", tUtable.delete(0));
		tLtable.returnItem(0, "9781442668584", "1", tDate);
		tFtable.payfine(0);
		tLtable.createloan(0, "9781442668584", "1", tDate);
		assertEquals("Active Loan Exists", tUtable.delete(0));
		assertEquals("The User Does Not Exist", tUtable.delete(6));
	}

}
