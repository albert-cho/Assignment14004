package server.logic.model.Tests;

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
	}

}
