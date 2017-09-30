package server.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

import org.apache.log4j.Logger;

import utilities.Trace;

public class ServerThread extends Thread{
	private int ID = -1;
	private Socket socket = null;
	private LibServer server = null;
	private BufferedReader streamIn = null;
	private BufferedWriter streamOut = null;
	private Logger logger = Trace.getInstance().getLogger(this);
	private String clientAddress = null;;

	private boolean done = false;
	
	public ServerThread(LibServer server, Socket socket) {
		super();
		this.server = server;
		this.socket = socket;
		this.ID = socket.getPort();
		this.clientAddress = socket.getInetAddress().getHostAddress();
	}
}
