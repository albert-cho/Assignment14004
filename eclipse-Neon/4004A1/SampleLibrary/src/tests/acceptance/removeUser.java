package tests.acceptance;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;
import utilities.Trace;

public class removeUser {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");


	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//Clerk to delete user
		tServerOut = tInput.processInput("delete user", InputHandler.CLERK);
		assertEquals("Please Input User Info:'useremail'", tServerOut.getOutput());
		assertEquals(InputHandler.DELETEUSER, tServerOut.getState());
		logger.info(String.format("Operation:Clerk requesting to delete a User."));
		
		//Clerk then adds the email to delete the user
		////If the has active loans
		tServerOut = tInput.processInput("Zhibo@carleton.ca", InputHandler.DELETEUSER);
		assertEquals("Outstanding Fee Exists!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:User has outstanding fees."));
		
		////If the information is good
		tServerOut = tInput.processInput("Yu@carleton.ca", InputHandler.DELETEUSER);
		assertEquals("Success!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:User gets deleted successfully."));
		
	}

}
