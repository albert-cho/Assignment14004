package server.logic.model.Tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import server.logic.model.Loan;

public class LoanTests {
	
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date tDate = new Date();
	Loan tLoan = new Loan(0, "Test", "0", tDate, "1");
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(0, tLoan.getUserid());
		assertEquals("Test", tLoan.getIsbn());
		assertEquals("0", tLoan.getCopynumber());
		assertEquals(tDate, tLoan.getDate());
		assertEquals("1", tLoan.getRenewstate());
		assertEquals("[0,Test,0,"+format1.format(tDate)+",1]", tLoan.toString());
	}

}
