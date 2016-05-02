package packets;

import game.Client;
import game.Server;

public class Packet11Disconnect extends Packet {
	private static final long serialVersionUID = 7397367557267184374L;
	private String client_name;

	public Packet11Disconnect(String client_name, Client client) {
		super(client.getClientID());
		this.client_name = client_name;
	}

	@Override
	public void writeClient(Client client) {
		client.sendData(this);
	}

	@Override
	public void writeServer(Server server) {
		
	}

	@Override
	public void readClient(Client client) {
		client.getGame().getLobby().appendMsg(client_name + " has disconnected...");
		client.getGame().getLobby().removeModel(client_name);
	}

	@Override
	public void readServer(Server server) {
		server.removeClient(client_name);
		server.sendDataAll(this);
	}

}
