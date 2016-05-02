package game;

import packets.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client extends Thread {
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private int port;
	private Game game;
	private String client_name;
	private boolean ready = false;
	private int client_ID;
	
	public Game getGame() {
		return this.game;
	}
	
	public Client(Game game, String ipAddress) {
		this.game = game;
		this.client_name = game.getLobby().getClientName();
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
			this.port = socket.getLocalPort();
		}catch(SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
		Packet pack = new Packet00Login(client_name, ipAddress, port);
		pack.writeClient(this);
	}
	
	public void run() {
		while(true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
				Packet pack = (Packet)Packet.deserialize(packet.getData());
				pack.readClient(this);
			}catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendData(Packet pack) {
		try {
			byte[] data = Packet.serialize(pack);
			DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 4324);
			socket.send(packet);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getClientName() {
		return this.client_name;
	}
	
	public void setReady() {
		this.ready = true;
	}
	
	public boolean getReady() {
		return this.ready;
	}
	
	public int getClientID() {
		return client_ID;
	}

	public void setClientID(int client_ID) {
		this.client_ID = client_ID;
	}

	
}
