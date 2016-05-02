package game;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.List;

public class ClientHandler implements Serializable{
	private static final long serialVersionUID = 5416783362875791872L;
		private InetAddress address;
		private int port;
		private String client_name;
		private List<ClientHandler> other_clients;
		private int client_ID;
		private boolean ready = false;
		public ClientHandler(InetAddress address, int port, List<ClientHandler> other_clients, String client_name, int client_ID) {
			this.address = address;
			this.port = port;
			this.other_clients = other_clients;
			this.client_name = client_name;
			this.client_ID = client_ID;
		}
		
		public InetAddress clientAddress() {
			return this.address;
		}
		
		public int clientPort() {
			return this.port;
		}

		
		public List<ClientHandler> getOtherClients() {
			return this.other_clients;
		}
		
		public int getID() {
			return this.client_ID;
		}
		
		public String toString() {
			return this.client_name;
		}
		
		public void setReady() {
			this.ready = true;
		}
		
		public boolean getReady() {
			return this.ready;
		}
	}