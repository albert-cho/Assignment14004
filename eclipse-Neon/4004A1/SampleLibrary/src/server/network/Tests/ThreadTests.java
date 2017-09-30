package server.network.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.network.ServerThread;

public class ThreadTests {

	ServerThread tThread = new ServerThread();
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(9999, tThread.getId());
	}

}
