package packets;

import game.Client;
import game.Server;

public class Packet01Message extends Packet {
	private static final long serialVersionUID = -3508052512076027658L;
	private String msg;
	
	public Packet01Message(String msg, int id) {
		super(id);
		this.msg = msg;
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
		String[] part = msg.split(",");
		client.getGame().getLobby().appendMsg(part[0] + " said: " + part[1]);
	}

	@Override
	public void readServer(Server server) {
		server.sendDataAll(this);
	}
}
