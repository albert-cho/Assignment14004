package tests.acceptance;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class monitorSystem {

	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//Clerk to monitor the system
		tServerOut = tInput.processInput("display", InputHandler.CLERK);
		assertEquals("Please Input Display Info:'Titles/Users/Monitor'", tServerOut.getOutput());
		assertEquals(InputHandler.DISPLAY, tServerOut.getState());
		
		//Clerk can look at the titles, users, or both
		String tString = "Titles: " + TitleTable.getInstance().getTitleList() +
				"\nUsers: " + UserTable.getInstance().getUserList();
		tServerOut = tInput.processInput("monitor", InputHandler.DISPLAY);
		assertEquals(tString, tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
	}

}
