package tests.acceptance;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;
import utilities.Trace;

public class addTitle {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");


	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//Clerk requests to add title
		tServerOut = tInput.processInput("create title", InputHandler.CLERK);
		assertEquals("Please Input Title Info:'ISBN,title'", tServerOut.getOutput());
		assertEquals(InputHandler.CREATETITLE, tServerOut.getState());
		logger.info(String.format("Operation:Clerk requesting to create a Title."));
				
		//Clerk Adds ISBN number
		////If missing information
		tServerOut = tInput.processInput("1111111111111", InputHandler.CREATETITLE);
		assertEquals("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", tServerOut.getOutput());
		assertEquals(InputHandler.CREATETITLE, tServerOut.getState());
		logger.info(String.format("Operation:Title can not be made due to missing information."));
		
		////If the information is good
		tServerOut = tInput.processInput("1111111111111, Test", InputHandler.CREATETITLE);
		assertEquals("Success!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:Title gets generated."));
		
		////If the title already exists
		tServerOut = tInput.processInput("1111111111111, Test", InputHandler.CREATETITLE);
		assertEquals("The Title Already Exists!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:Title that is wanting to be created already exists."));
		
	}

}
