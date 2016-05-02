package packets;

import game.*;

public class Packet02Ready extends Packet {
	private static final long serialVersionUID = -7918084715981220239L;
	private String client_name;
	private String message;
	
	public Packet02Ready(String client_name, int id) {
		super(id);
		this.client_name = client_name;
	}
	
	@Override
	public void writeClient(Client client2) {
		client2.sendData(this);
	}

	@Override
	public void writeServer(Server server) {

	}

	@Override
	public void readClient(Client client) {
		client.getGame().getLobby().appendMsg(message);
	}
	
	@Override
	public void readServer(Server server) {
		ClientHandler ch = server.getClientByName(client_name);
		if(!ch.getReady()) {
			message = client_name + " is ready!";
			ch.setReady();
		} else {
			message = client_name + " wants to start!";
		}
		server.sendDataAll(this);
		for(ClientHandler client : server.getClients()) {
			if(!client.getReady()) {
				return;
			}
		}
		Packet03Start start = new Packet03Start(this.ID);
		start.setList(server.getClientListById(this.ID));
		start.writeServer(server);
	}

}
