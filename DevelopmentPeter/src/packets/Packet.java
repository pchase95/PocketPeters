package packets;
import game.*;
import java.io.*;
import java.net.DatagramPacket;

public abstract class Packet implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String ID;
	public Packet(String ID) {
		this.ID = ID;
	}

	public String getID() {
		return this.ID;
	}
	
	public static Packet getPacket(DatagramPacket packet) {
		System.out.println(packet.getClass());
		String msg = new String(packet.getData());
		String id = msg.substring(0, 2);
		switch(id) {
		default:
			return new Packet99Invalid(packet);
		case "00":
			return new Packet00Login(packet);
		case "01":
			return new Packet01Message(packet);
		case "02":
			return new Packet02Ready(packet);
		case "03":
			return new Packet03Start();
		case "11":
			return new Packet11Disconnect(packet);
		}
	}
	

	public static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	
	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}
	
	//Create a packet from the Client
	public abstract void writeClient(Client client);
	//Create a packet from the Server
	public abstract void writeServer(Server server);
	//Read a packet in the Client
	public abstract void readClient(Client client);
	//Read a packet in the Server
	public abstract void readServer(Server server);

}
