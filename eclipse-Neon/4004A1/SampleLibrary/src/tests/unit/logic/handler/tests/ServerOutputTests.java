package tests.unit.logic.handler.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.model.ServerOutput;

public class ServerOutputTests {

	ServerOutput tOut = new ServerOutput("Test", 0);
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals("Test", tOut.getOutput());
		assertEquals(0, tOut.getState());
	}

}
