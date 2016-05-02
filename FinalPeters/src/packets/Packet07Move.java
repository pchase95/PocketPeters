package packets;

import game.Client;
import game.Server;

public class Packet07Move extends Packet {
	private static final long serialVersionUID = -7157008907648357082L;
	private int dir;

	public Packet07Move(int id) {
		super(id);
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
		client.getGame().getMap().setCoordsById(ID, dir);
	}

	@Override
	public void readServer(Server server) {
		server.sendDataAllOthers(this);
	}
	
	public void setDir(int a) {
		dir = a;
	}

}
