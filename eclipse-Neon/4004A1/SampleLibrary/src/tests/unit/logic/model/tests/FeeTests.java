package tests.unit.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.model.Fee;

public class FeeTests {

	Fee tFee = new Fee(0, 0);
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(0, tFee.getUserid());
		assertEquals(0, tFee.getFee());
		assertEquals("[0,0]", tFee.toString());
		
		tFee.setFee(1);
		tFee.setUserid(4);
		
		assertEquals(1, tFee.getFee());
		assertEquals(4, tFee.getUserid());
	}

}
