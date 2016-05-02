package packets;

import java.net.DatagramPacket;

import game.Client;
import game.Server;

public class Packet01Message extends Packet {
	private static final long serialVersionUID = -3508052512076027658L;
	private byte[] data;
	private DatagramPacket packet;
	public Packet01Message(DatagramPacket packet) {
		super("01");
		this.packet = packet;
	}
	
	public Packet01Message(String msg) {
		super("01");
		data = new String(this.ID + msg).getBytes();
	}

	@Override
	public void writeClient(Client client) {
		client.sendData(data);
	}

	@Override
	public void writeServer(Server server) {
		
	}

	@Override
	public void readClient(Client client) {
		String msg = new String(packet.getData()).substring(2);
		String[] part = msg.split(",");
		client.getGame().getLobby().appendMsg(part[0] + " said: " + part[1]);
	}

	@Override
	public void readServer(Server server) {
		server.sendDataAll(packet.getData());
	}

}
