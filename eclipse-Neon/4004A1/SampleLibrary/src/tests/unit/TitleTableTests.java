package tests.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.tables.TitleTable;

public class TitleTableTests {
	
	TitleTable tTable = TitleTable.getInstance();
	String tValues = "";
	{for (int i = 0; i < TitleTable.getInstance().getTitleTable().size(); i++){
		tValues = tValues + TitleTable.getInstance().getTitleTable().get(i);
		if(i < TitleTable.getInstance().getTitleTable().size()-1){
			tValues = tValues + ",";
		}
	}}
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(true, tTable.createtitle("ISBN", "Test"));
		assertEquals(false, tTable.createtitle("ISBN", "Test"));
		
		assertEquals(true, tTable.lookup("ISBN"));
		assertEquals(false, tTable.lookup("Test"));
		
		assertEquals("success", tTable.delete("ISBN"));
		assertEquals("Active Loan Exists", tTable.delete("9781442668584"));
		assertEquals("The Title Does Not Exist", tTable.delete("ISBN"));
		
		tTable.createtitle("9781442668584", "1");
		
		assertEquals(tValues, TitleTable.getInstance().getTitleList());
	}

}
