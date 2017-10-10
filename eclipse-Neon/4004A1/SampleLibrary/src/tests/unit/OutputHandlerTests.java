package tests.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;
import utilities.Config;

public class OutputHandlerTests {
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
    
	Output tOut = null;
	OutputHandler tOhandle = new OutputHandler();
	String tValues = "";
	String uValues = "";
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		tOut = tOhandle.createUser("Test");
		assertEquals("Your input should in this format:'username,password'", tOut.getOutput());
		assertEquals(CREATEUSER, tOut.getState());

		tOut = tOhandle.createUser("Test@test.ca,Test");
		assertEquals("Success!", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.createUser("Sun@carleton.ca,Sun");
		assertEquals("The User Already Exists!", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.createTitle("99Test");
		assertEquals("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", tOut.getOutput());
		assertEquals(CREATETITLE, tOut.getState());
		
		tOut = tOhandle.createTitle("9999999999999,Test");
		assertEquals("Success!", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.createTitle("9781442668584,By The Grace of God");
		assertEquals("The Title Already Exists!", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.createItem("Test");
		assertEquals("Your input should in this format:'ISBN',ISBN should be a 13-digit number", tOut.getOutput());
		assertEquals(CREATEITEM, tOut.getState());
		
		tOut = tOhandle.createItem("9781442668584");
		assertEquals("Success!", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.createItem("1111111111111");
		assertEquals("The Title Does Not Exists!", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.deleteUser("1111111111111");
		assertEquals("Your input should in this format:'useremail'", tOut.getOutput());
		assertEquals(DELETEUSER, tOut.getState());
		
		tOut = tOhandle.deleteUser("Test@testing.ca");
		assertEquals("The User Does Not Exist!", tOut.getOutput());
		assertEquals(DELETEUSER, tOut.getState());
		
		tOut = tOhandle.deleteUser("Sun@carleton.ca");
		assertEquals("Success!", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.deleteUser("Zhibo@carleton.ca");
		assertEquals("Outstanding Fee Exists!", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.deleteTitle("Zhibo@carleton.ca");
		assertEquals("Your input should in this format:'ISBN',ISBN should be a 13-digit number", tOut.getOutput());
		assertEquals(DELETETITLE, tOut.getState());
		
		tOut = tOhandle.deleteTitle("9781442616899");
		assertEquals("Success!", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.deleteTitle("9781442668584");
		assertEquals("Active Loan Exists!", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.returnBook("9781442668584,1");
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", tOut.getOutput());
		assertEquals(RETURN, tOut.getState());
		
		tOut = tOhandle.returnBook("Test@testing.ca,9781442668584,1");
		assertEquals("The User Does Not Exist!", tOut.getOutput());
		assertEquals(RETURN, tOut.getState());
		
		tOut = tOhandle.returnBook("Zhibo@carleton.ca,9781442668584,a");
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", tOut.getOutput());
		assertEquals(RETURN, tOut.getState());
		
		tOut = tOhandle.returnBook("Zhibo@carleton.ca,9781442668584,1");
		assertEquals("Success!", tOut.getOutput());
		assertEquals(USER, tOut.getState());
		
		tOut = tOhandle.returnBook("Zhibo@carleton.ca,9999999999999,1");
		assertEquals("The Loan Does Not Exist!", tOut.getOutput());
		assertEquals(USER, tOut.getState());
		
		tOut = tOhandle.borrow("Zhibo,9781442668584,1");
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", tOut.getOutput());
		assertEquals(BORROW, tOut.getState());
		
		tOut = tOhandle.borrow("Test@testing.ca,9781442668584,1");
		assertEquals("The User Does Not Exist!", tOut.getOutput());
		assertEquals(BORROW, tOut.getState());
		
		tOut = tOhandle.borrow("Zhibo@carleton.ca,9781442668584,a");
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", tOut.getOutput());
		assertEquals(BORROW, tOut.getState());
		
		tOut = tOhandle.borrow("Yu@carleton.ca,9781442668584,1");
		assertEquals("Success!", tOut.getOutput());
		assertEquals(USER, tOut.getState());
		
		tOut = tOhandle.borrow("Zhibo@carleton.ca,9781442668584,1");
		assertEquals("The Item is Not Available!", tOut.getOutput());
		assertEquals(USER, tOut.getState());
		
		tOut = tOhandle.renew("Zhibo@carleton.ca,9781442668584");
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", tOut.getOutput());
		assertEquals(RENEW, tOut.getState());
		
		tOut = tOhandle.renew("Te@carleton.ca,9781442668584,1");
		assertEquals("The User Does Not Exist!", tOut.getOutput());
		assertEquals(RENEW, tOut.getState());
		
		tOut = tOhandle.renew("Zhibo@carleton.ca,9781442668584,a");
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", tOut.getOutput());
		assertEquals(RENEW, tOut.getState());
		
		tOut = tOhandle.renew("Yu@carleton.ca,9781442668584,1");
		assertEquals("Success!", tOut.getOutput());
		assertEquals(USER, tOut.getState());
		
		tOut = tOhandle.renew("Zhibo@carleton.ca,9781442668584,1");
		assertEquals("Outstanding Fee Exists!", tOut.getOutput());
		assertEquals(USER, tOut.getState());
		
		tOut = tOhandle.payFine("Test");
		assertEquals("Your input should in this format:'useremail'", tOut.getOutput());
		assertEquals(PAYFINE, tOut.getState());
		
		tOut = tOhandle.payFine("Test@ttess.ca");
		assertEquals("The User Does Not Exist!", tOut.getOutput());
		assertEquals(PAYFINE, tOut.getState());
		
		tOut = tOhandle.payFine("Zhibo@carleton.ca");
		assertEquals("Success!", tOut.getOutput());
		assertEquals(USER, tOut.getState());
		
		tOut = tOhandle.payFine("Yu@carleton.ca");
		assertEquals("Borrowing Items Exist!", tOut.getOutput());
		assertEquals(USER, tOut.getState());
		
		tOut = tOhandle.clerkLogin("Test");
		assertEquals("Wrong Password!Please Input The Password:", tOut.getOutput());
		assertEquals(CLERKLOGIN, tOut.getState());
		
		tOut = tOhandle.clerkLogin(Config.CLERK_PASSWORD);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.userLogin("");
		assertEquals("Your input should in this format:'username,password'", tOut.getOutput());
		assertEquals(USERLOGIN, tOut.getState());
		
		tOut = tOhandle.userLogin("Yu@carleton.ca,Yu");
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", tOut.getOutput());
		assertEquals(USER, tOut.getState());
		
		tOut = tOhandle.userLogin("Yu@carleton.ca,Test");
		assertEquals("Wrong Password!Please Input Username and Password:'username,password'", tOut.getOutput());
		assertEquals(USERLOGIN, tOut.getState());
		
		tOut = tOhandle.userLogin("Test@testing.ca,Test");
		assertEquals("The User Does Not Exist!Please The Username and Password:'username,password'", tOut.getOutput());
		assertEquals(USERLOGIN, tOut.getState());
		
		for (int i = 0; i < TitleTable.getInstance().getTitleTable().size(); i++){
			tValues = tValues + TitleTable.getInstance().getTitleTable().get(i);
			if(i < TitleTable.getInstance().getTitleTable().size()-1){
				tValues = tValues + ",";
			}
		}
		
		tOut = tOhandle.display("Titles");
		assertEquals(tValues, tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		for (int i = 0; i < UserTable.getInstance().getUserTable().size(); i++){
			uValues = uValues + UserTable.getInstance().getUserTable().get(i);
			if(i < UserTable.getInstance().getUserTable().size()-1){
				uValues = uValues + ",";
			}
		}
		
		tOut = tOhandle.display("Users");
		assertEquals(uValues, tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		String mValues = "";
		mValues = "Titles: " + tValues + "\nUsers: " + uValues;
		
		tOut = tOhandle.display("Monitor");
		assertEquals(mValues, tOut.getOutput());
		assertEquals(CLERK, tOut.getState());
		
		tOut = tOhandle.display("Test");
		assertEquals("Your input should in this format:'display'", tOut.getOutput());
		assertEquals(DISPLAY, tOut.getState());
	}

}
