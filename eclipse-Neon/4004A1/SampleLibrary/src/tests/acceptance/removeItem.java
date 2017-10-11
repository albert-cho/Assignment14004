package tests.acceptance;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;
import utilities.Trace;

public class removeItem {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");


	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//Clerk Requests to delete item
		tServerOut = tInput.processInput("delete item", InputHandler.CLERK);
		assertEquals("Please Input Item Info:'ISBN,copynumber'", tServerOut.getOutput());
		assertEquals(InputHandler.DELETEITEM, tServerOut.getState());
		logger.info(String.format("Operation:Clerk requesting to delete an Item."));
		
		//ISBN and copy number information is added
		////The ISBN number does not exist
		tServerOut = tInput.processInput("1111111111111", InputHandler.DELETEITEM);
		assertEquals("Your input should in this format:'ISBN,copynumber',ISBN should be a 13-digit number", tServerOut.getOutput());
		assertEquals(InputHandler.DELETEITEM, tServerOut.getState());
		logger.info(String.format("Operation:Item's ISBN number does not exist."));
		
		////The Item gets deleted successfully
		tServerOut = tInput.processInput("9781442616899,1", InputHandler.DELETEITEM);
		assertEquals("Success!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:Item gets deleted from the system."));
		
		////The Item has a loan on it so the clerk can not delete it
		tServerOut = tInput.processInput("9781442668584,1", InputHandler.DELETEITEM);
		System.out.println(tServerOut.getOutput());
		assertEquals("Active Loan Exists!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:Item does not get deleted because there is an active loan."));
	}
}
