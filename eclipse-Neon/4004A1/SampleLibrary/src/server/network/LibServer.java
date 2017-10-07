package server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.handler.InputHandler;
import server.logic.handler.model.Client;
import server.logic.tables.FeeTable;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;
import utilities.Config;
import utilities.Trace;

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
			start();
		} catch (IOException ioe) {
			logger.fatal(ioe);
		}
	}
	
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
			logger.info(String.format("Server started: %s %d", server,thread.getId()));
			//Initialize the tables
			UserTable.getInstance();
			TitleTable.getInstance();
			ItemTable.getInstance();
			LoanTable.getInstance();
			FeeTable.getInstance();
			
			System.out.println("Server started successfully!");
		}
	}
	
	public void run() {
		while (thread != null) {
			try {
				logger.info("Waiting for a client ...");
				addThread(server.accept());
			} catch (IOException e) {				
				logger.fatal(e);
			}
		}
	}
	private void addThread(Socket socket) {
		String message = String.format("%s : Client Address : [%15s] Client Socket: [%-6d]\n", "Client accepted", socket.getRemoteSocketAddress(), socket.getPort());
		logger.info(String.format(message));
		if (clientCount < Config.MAX_CLIENTS) {
			try {
				ServerThread serverThread = new ServerThread(this, socket);
				serverThread.open();
				serverThread.start();
				clients.put(serverThread.getID(), serverThread);
				this.clientCount++;
			} catch (IOException e) {
				logger.fatal(e);
			}
		} else {
			logger.info(String.format("Client Tried to connect: %s", socket));
			logger.info(String.format("Client refused: maximum number of clients reached: d", 5));
		}
	}
}
