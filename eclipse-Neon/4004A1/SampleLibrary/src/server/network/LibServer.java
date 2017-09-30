package server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.handler.model.Client;

public class LibServer implements Runnable{
	int clientCount = 0;
	private Thread thread = null;
	private ServerSocket server = null;
	private HashMap<Integer, ServerThread> clients;
	private Logger logger = Trace.getInstance().getLogger(this);
	InputHandler handler=new InputHandler();
	private List<Client> clientList=new ArrayList<Client>();
	public LibServer(int port) {
		try {
			logger.info("Binding to port " + port);
			clients = new HashMap<Integer, ServerThread>();
			server = new ServerSocket(port);
			server.setReuseAddress(true);
			//start();
		} catch (IOException ioe) {
			logger.fatal(ioe);
		}
	}
}
