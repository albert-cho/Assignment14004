package tests.acceptance;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;
import utilities.Trace;

public class addUser {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");


	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//Clerk to add user
		tServerOut = tInput.processInput("create user", InputHandler.CLERK);
		assertEquals("Please Input User Info:'username,password'", tServerOut.getOutput());
		assertEquals(InputHandler.CREATEUSER, tServerOut.getState());
		logger.info(String.format("Operation:Clerk requesting to create a User."));
		
		//Clerk then adds the information for the user
		////If the user already exists
		tServerOut = tInput.processInput("Sun@carleton.ca,Sun", InputHandler.CREATEUSER);
		assertEquals("The User Already Exists!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:User already exists."));
		
		////If the information is good
		tServerOut = tInput.processInput("Test@carleton.ca,Test", InputHandler.CREATEUSER);
		assertEquals("Success!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:User gets created successfully."));
		
	}

}
