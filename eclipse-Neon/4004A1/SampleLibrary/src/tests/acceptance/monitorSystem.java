package tests.acceptance;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;
import utilities.Trace;

public class monitorSystem {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");

	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//Clerk to monitor the system
		tServerOut = tInput.processInput("display", InputHandler.CLERK);
		assertEquals("Please Input Display Info:'Titles/Users/Monitor'", tServerOut.getOutput());
		assertEquals(InputHandler.DISPLAY, tServerOut.getState());
		logger.info(String.format("Operation:Clerk requesting to monitor the library."));
		
		//Clerk can look at the titles, users, or both
		String tString = "Titles: " + TitleTable.getInstance().getTitleList() +
				"\nUsers: " + UserTable.getInstance().getUserList();
		tServerOut = tInput.processInput("monitor", InputHandler.DISPLAY);
		assertEquals(tString, tServerOut.getOutput());
		assertEquals(InputHandler.CLERK, tServerOut.getState());
		logger.info(String.format("Operation:The Titles and Users get displayed to the clerk."));
	}

}
