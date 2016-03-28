import java.util.*;
import java.io.*;
import java.net.*;

public class Client implements PositionListener
{
	private Map map;
	private ObjectOutputStream out;
	private Socket socket;
	ObjectInputStream in;
	PositionReceiver PR;
	
	public static void main(String[] args)
	{
		new Client();
	}
	
	public Client()
	{
		map = new Map(this);
		map.buildMap();
		Connect();
	}

	private void Connect()
	{
		try
		{
			socket = new Socket("localhost", 4324);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			PR = new PositionReceiver(in, this);
			Thread t = new Thread(PR);
			t.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void movePeter(Peter p)
	{
		try
		{
			map.addPeter(p);
			out.writeUnshared(p);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void removeUser()
	{
		System.exit(0);
	}
}