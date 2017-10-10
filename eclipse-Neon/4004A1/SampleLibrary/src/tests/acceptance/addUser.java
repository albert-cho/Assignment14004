package tests.acceptance;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;

public class addUser {

	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//Clerk to add user
		tServerOut = tInput.processInput("create user", InputHandler.CLERK);
		assertEquals("Please Input User Info:'username,password'", tServerOut.getOutput());
		assertEquals(InputHandler.CREATEUSER, tServerOut.getState());
		
		//Clerk then adds the information for the user
		////If the user already exists
		tServerOut = tInput.processInput("Sun@carleton.ca,Sun", InputHandler.CREATEUSER);
		assertEquals("The User Already Exists!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		
		////If the information is good
		tServerOut = tInput.processInput("Test@carleton.ca,Test", InputHandler.CREATEUSER);
		assertEquals("Success!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
	}

}
