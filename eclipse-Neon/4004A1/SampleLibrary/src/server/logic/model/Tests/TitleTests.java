package server.logic.model.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.model.Title;

public class TitleTests {

	Title cTitle = new Title("Test", "Name");
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals("Test", cTitle.getISBN());
		assertEquals("Name", cTitle.getBooktitle());
		assertEquals("[Test,Name]", cTitle.toString());
	}

}
