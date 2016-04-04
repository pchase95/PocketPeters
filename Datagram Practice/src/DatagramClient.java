import java.io.IOException;
import java.net.*;

public class DatagramClient extends Thread
{
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	

	public DatagramClient(Game game, String ipAddress)
	{
		this.game = game;
		try
		{
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		}
		catch(SocketException | UnknownHostException e)
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
			System.out.printf("The Server[Address:%s  Port:%d] said:%n%s%n", packet.getAddress().getHostAddress(), packet.getPort(), msg);
		}
	}
	
	public void sendData(byte[] data)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 4324);
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
