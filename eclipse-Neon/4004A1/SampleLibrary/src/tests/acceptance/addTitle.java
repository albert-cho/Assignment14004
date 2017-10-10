package tests.acceptance;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;

public class addTitle {

	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//Clerk requests to add title
		tServerOut = tInput.processInput("create title", InputHandler.CLERK);
		assertEquals("Please Input Title Info:'ISBN,title'", tServerOut.getOutput());
		assertEquals(InputHandler.CREATETITLE, tServerOut.getState());
		
		//Clerk Adds ISBN number
		////If missing information
		tServerOut = tInput.processInput("1111111111111", InputHandler.CREATETITLE);
		assertEquals("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", tServerOut.getOutput());
		assertEquals(InputHandler.CREATETITLE, tServerOut.getState());
		
		////If the information is good
		tServerOut = tInput.processInput("1111111111111, Test", InputHandler.CREATETITLE);
		assertEquals("Success!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		
		////If the title already exists
		tServerOut = tInput.processInput("1111111111111, Test", InputHandler.CREATETITLE);
		assertEquals("The Title Already Exists!", tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
	}

}
