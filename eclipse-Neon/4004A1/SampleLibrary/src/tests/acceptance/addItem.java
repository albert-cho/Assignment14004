package tests.acceptance;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;
import utilities.Trace;

public class addItem {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");


	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//Clerk Requests to add title
		tServerOut = tInput.processInput("create item", InputHandler.CLERK);
		assertEquals("Please Input Item Info:'ISBN'", tServerOut.getOutput());
		assertEquals(InputHandler.CREATEITEM, tServerOut.getState());
		logger.info(String.format("Operation:Clerk requesting to create an Item."));
		
		//Title information is added
		////The ISBN number does not exist
		tServerOut = tInput.processInput("1111111111111", InputHandler.CREATEITEM);
		assertEquals("The Title Does Not Exists!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		//have to get this to instead ask to add a title
		////see addTitle for adding title
		logger.info(String.format("Operation:Item's ISBN number does not exist."));
		logger.info(String.format("Operation:Clerk gets asked if want to add the Title. See addTitle Test."));
		
		////The Item gets added successfully
		tServerOut = tInput.processInput("9781442668584", InputHandler.CREATEITEM);
		assertEquals("Success!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:Item gets created into the system."));
	}

}
