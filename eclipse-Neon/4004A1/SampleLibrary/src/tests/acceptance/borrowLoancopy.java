package tests.acceptance;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;

public class borrowLoancopy {

	@Test
	public void test() {
		//Clerk to add user
		tServerOut = tInput.processInput("create user", InputHandler.CLERK);
		assertEquals("Please Input User Info:'username,password'", tServerOut.getOutput());
		assertEquals(InputHandler.CREATEUSER, tServerOut.getState());
	}

}
