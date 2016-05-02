package packets;

import java.net.DatagramPacket;

import game.Client;
import game.Server;

public class Packet11Disconnect extends Packet {
	private static final long serialVersionUID = 7397367557267184374L;
	private String client_name;
	private DatagramPacket packet;
	
	public Packet11Disconnect(DatagramPacket packet) {
		super("11");
		this.packet = packet;
	}
	
	public Packet11Disconnect(String client_name) {
		super("11");
		this.client_name = client_name;
	}

	@Override
	public void writeClient(Client client) {
		client.sendData(new String(this.ID + client_name).getBytes());
	}

	@Override
	public void writeServer(Server server) {
		
	}

	@Override
	public void readClient(Client client) {
		String msg = new String(packet.getData()).substring(2);
		client.getGame().getLobby().appendMsg(new String(msg + " has disconnected..."));
		client.getGame().getLobby().removeModel(msg);
	}

	@Override
	public void readServer(Server server) {
		byte[] client_name = packet.getData();
		server.sendDataAll(client_name);
		server.removeClient(new String(client_name).substring(2));
	}

}
