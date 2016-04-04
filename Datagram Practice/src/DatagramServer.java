import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class DatagramServer extends Thread
{
	private DatagramSocket socket;
	private Game game;
	

	public DatagramServer(Game game)
	{
		this.game = game;
		try
		{
			this.socket = new DatagramSocket(4324);
		}
		catch(SocketException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void run()
	{
		while(true)
		{
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try
			{
				socket.receive(packet);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			String msg = new String(packet.getData()).trim();
			System.out.printf("Client[Address:%s  Port:%d] said:%n%s%n", packet.getAddress().getHostAddress(), packet.getPort(), msg);
			if(msg.equalsIgnoreCase("ping"))
			{
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}
		}	
	}
	
	public void sendData(byte[] data, InetAddress ipAddress, int port)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try
		{
			socket.send(packet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
