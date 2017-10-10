package tests.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.ServerOutput;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;
import utilities.Config;

public class InputHandlerTests {
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
    public static final int CLERK = 2;
    public static final int USER = 3;
    public static final int CREATEUSER=4;
    public static final int CREATETITLE=5;
    public static final int CREATEITEM=6;
    public static final int DELETEUSER=7;
    public static final int DELETETITLE=8;
    public static final int DELETEITEM=9;
    public static final int BORROW=10;
    public static final int RENEW=11;
    public static final int RETURN=12;
    public static final int PAYFINE=13;
    public static final int CLERKLOGIN=14;
    public static final int USERLOGIN=15;
    public static final int DISPLAY=16;

	ServerOutput tServerOut = null;
	InputHandler tInput = new InputHandler();
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		tServerOut = tInput.processInput("", WAITING);
		assertEquals("Who Are you?Clerk or User?", tServerOut.getOutput());
		assertEquals(FINISHWAITING, tServerOut.getState());
		
		tServerOut = tInput.processInput("clerk", FINISHWAITING);
		assertEquals("Please Input The Password:", tServerOut.getOutput());
		assertEquals(CLERKLOGIN, tServerOut.getState());
		
		tServerOut = tInput.processInput("user", FINISHWAITING);
		assertEquals("Please Input Username and Password:'username,password'", tServerOut.getOutput());
		assertEquals(USERLOGIN, tServerOut.getState());
		
		tServerOut = tInput.processInput("", FINISHWAITING);
		assertEquals("Who Are you?Clerk or User?", tServerOut.getOutput());
		assertEquals(FINISHWAITING, tServerOut.getState());
		
		tServerOut = tInput.processInput(Config.CLERK_PASSWORD, CLERKLOGIN);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());
		
		tServerOut = tInput.processInput("Yu@carleton.ca,Yu", USERLOGIN);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", tServerOut.getOutput());
		assertEquals(USER, tServerOut.getState());
		
		tServerOut = tInput.processInput("create user", CLERK);
		assertEquals("Please Input User Info:'username,password'", tServerOut.getOutput());
		assertEquals(CREATEUSER, tServerOut.getState());
		
		tServerOut = tInput.processInput("create title", CLERK);
		assertEquals("Please Input Title Info:'ISBN,title'", tServerOut.getOutput());
		assertEquals(CREATETITLE, tServerOut.getState());
		
		tServerOut = tInput.processInput("create item", CLERK);
		assertEquals("Please Input Item Info:'ISBN'", tServerOut.getOutput());
		assertEquals(CREATEITEM, tServerOut.getState());
		
		tServerOut = tInput.processInput("delete user", CLERK);
		assertEquals("Please Input User Info:'useremail'", tServerOut.getOutput());
		assertEquals(DELETEUSER, tServerOut.getState());
		
		tServerOut = tInput.processInput("delete title", CLERK);
		assertEquals("Please Input Title Info:'ISBN'", tServerOut.getOutput());
		assertEquals(DELETETITLE, tServerOut.getState());
		
		tServerOut = tInput.processInput("delete item", CLERK);
		assertEquals("Please Input Item Info:'ISBN,copynumber'", tServerOut.getOutput());
		assertEquals(DELETEITEM, tServerOut.getState());
		
		tServerOut = tInput.processInput("log out", CLERK);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());
		
		tServerOut = tInput.processInput("main menu", CLERK);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());
		
		tServerOut = tInput.processInput("", CLERK);
		assertEquals("Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item.", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());
		
		tServerOut = tInput.processInput("borrow", USER);
		assertEquals("Please Input User Info:'useremail,ISBN,copynumber'", tServerOut.getOutput());
		assertEquals(BORROW, tServerOut.getState());
		
		tServerOut = tInput.processInput("renew", USER);
		assertEquals("Please Input Title Info:'useremail,ISBN,copynumber'", tServerOut.getOutput());
		assertEquals(RENEW, tServerOut.getState());

		tServerOut = tInput.processInput("return", USER);
		assertEquals("Please Input Item Info:'useremail,ISBN,copynumber'", tServerOut.getOutput());
		assertEquals(RETURN, tServerOut.getState());

		tServerOut = tInput.processInput("pay fine", USER);
		assertEquals("Please Input User Info:'useremail'", tServerOut.getOutput());
		assertEquals(PAYFINE, tServerOut.getState());

		tServerOut = tInput.processInput("log out", USER);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", USER);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", tServerOut.getOutput());
		assertEquals(USER, tServerOut.getState());
		
		tServerOut = tInput.processInput("", USER);
		assertEquals("Please select from the menu.Menu:Borrow,Renew,Return,Pay Fine.", tServerOut.getOutput());
		assertEquals(USER, tServerOut.getState());
		
		tServerOut = tInput.processInput("log out", CREATEUSER);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", CREATEUSER);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());

		tServerOut = tInput.processInput("Sun@carleton.ca,Sun", CREATEUSER);
		assertEquals("The User Already Exists!", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());

		tServerOut = tInput.processInput("log out", CREATETITLE);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", CREATETITLE);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());

		tServerOut = tInput.processInput("9781442668584,By The Grace of God", CREATETITLE);
		assertEquals("The Title Already Exists!", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());

		tServerOut = tInput.processInput("log out", CREATEITEM);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", CREATEITEM);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());

		tServerOut = tInput.processInput("1111111111111", CREATEITEM);
		assertEquals("The Title Does Not Exists!", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());

		tServerOut = tInput.processInput("log out", DELETEUSER);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", DELETEUSER);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());

		tServerOut = tInput.processInput("Testing@test.ca", DELETEUSER);
		assertEquals("The User Does Not Exist!", tServerOut.getOutput());
		assertEquals(DELETEUSER, tServerOut.getState());
		
		tServerOut = tInput.processInput("log out", DELETETITLE);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", DELETETITLE);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());

		tServerOut = tInput.processInput("1135A", DELETETITLE);
		assertEquals("Your input should in this format:'ISBN',ISBN should be a 13-digit number", tServerOut.getOutput());
		assertEquals(DELETETITLE, tServerOut.getState());
		
		tServerOut = tInput.processInput("log out", DELETEITEM);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", DELETEITEM);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());

		tServerOut = tInput.processInput("1111111111111", DELETEITEM);
		assertEquals("Your input should in this format:'ISBN,copynumber',ISBN should be a 13-digit number", tServerOut.getOutput());
		assertEquals(DELETEITEM, tServerOut.getState());

		tServerOut = tInput.processInput("log out", BORROW);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", BORROW);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", tServerOut.getOutput());
		assertEquals(USER, tServerOut.getState());

		tServerOut = tInput.processInput("Yu,1111111111111,1", BORROW);
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", tServerOut.getOutput());
		assertEquals(BORROW, tServerOut.getState());

		tServerOut = tInput.processInput("log out", RENEW);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", RENEW);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", tServerOut.getOutput());
		assertEquals(USER, tServerOut.getState());

		tServerOut = tInput.processInput("Yu,1111111111111", RENEW);
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", tServerOut.getOutput());
		assertEquals(RENEW, tServerOut.getState());

		tServerOut = tInput.processInput("log out", RETURN);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", RETURN);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", tServerOut.getOutput());
		assertEquals(USER, tServerOut.getState());

		tServerOut = tInput.processInput("Sun,1111111111111", RETURN);
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", tServerOut.getOutput());
		assertEquals(RETURN, tServerOut.getState());

		tServerOut = tInput.processInput("log out", PAYFINE);
		assertEquals("Successfully Log Out!", tServerOut.getOutput());
		assertEquals(WAITING, tServerOut.getState());

		tServerOut = tInput.processInput("main menu", PAYFINE);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", tServerOut.getOutput());
		assertEquals(USER, tServerOut.getState());

		tServerOut = tInput.processInput("Test", PAYFINE);
		assertEquals("Your input should in this format:'useremail'", tServerOut.getOutput());
		assertEquals(PAYFINE, tServerOut.getState());
		
		tServerOut = tInput.processInput("titles", DISPLAY);
		assertEquals(TitleTable.getInstance().getTitleList(), tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());
		
		tServerOut = tInput.processInput("users", DISPLAY);
		assertEquals(UserTable.getInstance().getUserList(), tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());
		
		tServerOut = tInput.processInput("Test", DISPLAY);
		assertEquals("Your input should in this format:'display'", tServerOut.getOutput());
		assertEquals(DISPLAY, tServerOut.getState());
		
		String tString = "Titles: " + TitleTable.getInstance().getTitleList() +
				"\nUsers: " + UserTable.getInstance().getUserList();
		tServerOut = tInput.processInput("monitor", DISPLAY);
		assertEquals(tString, tServerOut.getOutput());
		assertEquals(CLERK, tServerOut.getState());
	}

}
