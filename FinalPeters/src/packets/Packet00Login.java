package packets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import game.Client;
import game.ClientHandler;
import game.Server;

public class Packet00Login extends Packet {
	private static final long serialVersionUID = 8394421395713254658L;
	private String client_name;
	private ClientHandler ch;
	private List<ClientHandler> list;
	private InetAddress ipAddress;
	private int port;
	private int sender_ID;
	
	public Packet00Login(String client_name, String ipAddress, int port) {
		super(-1);
		this.client_name = client_name;
		try {
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.port = port;
	}
	
	public Packet00Login(String client_name, InetAddress ipAddress, int port) {
		super(00);
		this.client_name = client_name;
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	@Override
	public void writeClient(Client client) {
		client.sendData(this);
	}
	
	@Override
	public void readClient(Client client) {
		client.getGame().getLobby().newUser(client_name);
	}

	
	@Override
	public void writeServer(Server server) {
		server.sendData(this, ipAddress, port);
	}

	@Override
	public void readServer(Server server) {
		server.newClient(this);
	}
	
	public String getName() {
		return this.client_name;
	}
	
	public InetAddress getIpAddress() {
		return this.ipAddress;
	}
	
	public int getPort() {
		return this.port;
	}
}