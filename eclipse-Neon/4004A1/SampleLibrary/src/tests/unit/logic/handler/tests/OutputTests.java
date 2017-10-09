package tests.unit.logic.handler.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.model.Output;

public class OutputTests {

	Output tOut = new Output("Test", 0);
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals("Test", tOut.getOutput());
		assertEquals(0, tOut.getState());
		assertEquals("[Test,0]", tOut.toString());
		
		tOut.setOutput("T1");
		tOut.setState(1);
		
		assertEquals("T1", tOut.getOutput());
		assertEquals(1, tOut.getState());
	}

}
