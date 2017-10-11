package tests.acceptance;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;
import utilities.Trace;

public class removeTitle {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");


	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//Clerk requests to delete title
		tServerOut = tInput.processInput("delete title", InputHandler.CLERK);
		assertEquals("Please Input Title Info:'ISBN'", tServerOut.getOutput());
		assertEquals(InputHandler.DELETETITLE, tServerOut.getState());
		logger.info(String.format("Operation:Clerk requesting to delete a Title."));
				
		//Clerk Adds ISBN number
		////If missing information
		tServerOut = tInput.processInput("1111111111111", InputHandler.DELETEITEM);
		assertEquals("Your input should in this format:'ISBN,copynumber',ISBN should be a 13-digit number", tServerOut.getOutput());
		assertEquals(InputHandler.DELETEITEM, tServerOut.getState());
		logger.info(String.format("Operation:Item can not be deleted due to missing information."));
		
		////If the information is good
		tServerOut = tInput.processInput("9781442616899,1", InputHandler.DELETEITEM);
		assertEquals("Success!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:Item gets deleted."));
		
		////If the item already exists
		tServerOut = tInput.processInput("9781442668584,1", InputHandler.DELETEITEM);
		assertEquals("Active Loan Exists!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:Item that is wanting to be deleted has an active loan."));
		
	}
}
