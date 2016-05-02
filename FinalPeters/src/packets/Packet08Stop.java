package packets;

import game.Client;
import game.Server;

public class Packet08Stop extends Packet {

	public Packet08Stop(int ID) {
		super(ID);
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
		client.getGame().getMap().stopById(ID);
	}

	@Override
	public void readServer(Server server) {
		server.sendDataAllOthers(this);
	}

}
