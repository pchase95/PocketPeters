package game;

import javax.swing.JOptionPane;

public class Game {
	private Client client;
	private Server server;
	private String client_name;
	private String client_IP;
	private Lobby lobby;
	
	public Game(String new_client_name, String new_client_IP) {
		this.client_name = new_client_name;
		this.client_IP = new_client_IP;
		if(JOptionPane.showConfirmDialog(null, "Do you want to run the server?") == 0) {
			server = new Server();
			server.start();
		}
		lobby = new Lobby(this, client_name, client_IP);
		client = new Client(this, "localhost");
		client.start();
	}
	
	public Lobby getLobby() {
		return this.lobby;
	}
	
	public Client getClient() {
		return this.client;
	}
	

}
