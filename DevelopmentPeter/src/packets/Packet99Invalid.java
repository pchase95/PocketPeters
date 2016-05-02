package packets;

import java.net.DatagramPacket;

import game.Client;
import game.Server;

public class Packet99Invalid extends Packet {
	private static final long serialVersionUID = -1285954837161319950L;
	private DatagramPacket packet;

	public Packet99Invalid(DatagramPacket packet) {
		super("99");
		this.packet = packet;
	}
	@Override
	public void writeClient(Client client) {

	}

	@Override
	public void writeServer(Server server) {

	}

	@Override
	public void readClient(Client client) {

	}

	@Override
	public void readServer(Server server) {

	}

}
