package game;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import packets.Packet;
import packets.Packet00Login;
import packets.Packet06ID;

public class Server extends Thread {
	private List<ClientHandler> clients = new ArrayList<>();
	private DatagramSocket socket;
	private int ID = 0;
	
	public Server() {
		try {
			this.socket = new DatagramSocket(4324);
		} catch(SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
				Packet pack = (Packet)Packet.deserialize(packet.getData());
				pack.readServer(this);
			} catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendData(Packet pack, InetAddress ipAddress, int port) {
		try {
			byte[] data = Packet.serialize(pack);
			DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendDataAll(Packet pack) {
		for(ClientHandler ch : clients) {
			sendData(pack, ch.clientAddress(), ch.clientPort());
		}
	}
	
	
	public void newClient(Packet00Login pack) {
		sendDataAll(pack);
		ClientHandler ch = new ClientHandler(pack.getIpAddress(), pack.getPort(), clients, pack.getName(), ID);
		Packet id_pack = new Packet06ID(ID++, pack.getIpAddress(), pack.getPort());
		id_pack.writeServer(this);
		for(ClientHandler client : clients) {
			Packet pack2 = new Packet00Login(client.toString(), pack.getIpAddress(), pack.getPort());
			pack2.writeServer(this);
		}
		clients.add(ch);
	}
	
	public void sendDataAllOthers(Packet pack) {
		for(ClientHandler client : clients) {
			if(client.getID() != pack.getID()) {
				sendData(pack, client.clientAddress(), client.clientPort());
			}
		}
	}
	
	public void removeClient(String client_name) {
		for(ClientHandler ch : clients) {
			if(ch.toString().equals(client_name.trim())) {
				clients.remove(ch);
				return;
			}
		}
	}
	public List<ClientHandler> getClients() {
		return this.clients;
	}
	
	public ClientHandler getClientByName(String name) {
		for(ClientHandler ch : clients) {
			if(ch.toString().equals(name.trim())) {
				return ch;
			}
		}
		return null;
	}
	
	public List<ClientHandler> getClientListById(int id) {
		return clients.get(id).getOtherClients();
	}
	
}


