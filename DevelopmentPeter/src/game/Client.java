package game;

import packets.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client extends Thread {
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	private String client_name;
	private boolean ready = false;
	
	public Game getGame() {
		return this.game;
	}
	
	public Client(Game game, String ipAddress) {
		this.game = game;
		this.client_name = game.getLobby().getClientName();
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		}catch(SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
		Packet pack = new Packet00Login(client_name);
		pack.writeClient(this);
	}
	
	public void run() {
		while(true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			}catch(IOException e) {
				e.printStackTrace();
			}
			Packet pack = Packet.getPacket(packet);
//			System.out.println("PACKET: " + pack);
			pack.readClient(this);
		}
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 4324);
		try {
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
	
}
