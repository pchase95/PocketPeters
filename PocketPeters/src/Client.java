import java.util.*;
import java.net.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class Client implements MessageListener
{
	public static void main(String[] args)
	{
		new Client();
	}

	public Client()
	{
		Map map = new Map();
		map.buildMap();
		try
		{
			Socket socket = new Socket("localhost", 4324);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			MessageReceiver mr = new MessageReceiver(in, this);
			Thread t = new Thread(mr);
			t.start();
			while(true)
			{
				int x = map.peter.getX();
				int y = map.peter.getY();
				String coords = x + "," + y;
				out.writeObject(coords);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void deliverMessage(String s)
	{
		System.out.println(s);
	}

	public void removeMe()
	{
		System.exit(0);
	}


}