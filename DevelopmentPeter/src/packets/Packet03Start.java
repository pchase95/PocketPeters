package packets;

import game.*;
import java.net.DatagramPacket;

import game.Client;
import game.Server;

public class Packet03Start extends Packet {
	private static final long serialVersionUID = -875892452723281529L;
	private DatagramPacket packet;
	
//	public Packet03Start(DatagramPacket packet) {
//		super("03");
//		this.packet = packet;
//	}
	
	public Packet03Start() {
		super("03");
	}
	@Override
	public void writeClient(Client client) {

	}

	@Override
	public void writeServer(Server server) {

	}

	@Override
	public void readClient(Client client) {
		client.getGame().getLobby().startGame();
	}

	@Override
	public void readServer(Server server) {

	}

}
