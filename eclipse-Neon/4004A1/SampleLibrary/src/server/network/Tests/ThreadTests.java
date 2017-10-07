package server.network.Tests;

import static org.junit.Assert.*;

import java.io.*;
import java.net.Socket;

import org.junit.Test;

import server.network.LibServer;
import server.network.ServerThread;
import utilities.Config;

public class ThreadTests {
	Socket server = null;
	LibServer libServer = null;{
	
	try
	{
		libServer = new LibServer(Config.DEFAULT_PORT);
		server = new Socket("Test", Config.DEFAULT_PORT);
	} catch (IOException e){
		e.printStackTrace();
	}}
	
	ServerThread tServ = new ServerThread(libServer, server);
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		
	}

}
