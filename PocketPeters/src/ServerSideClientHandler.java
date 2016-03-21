import java.util.*;
import java.net.*;
import java.io.*;

public class ServerSideClientHandler implements Runnable, MessageListener
{
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private ArrayList<ServerSideClientHandler> list;
	private MessageReceiver mr;

	public ServerSideClientHandler(Socket socket, ArrayList<ServerSideClientHandler> l)
	{
		try
		{
			in = new ObjectInputStream(socket.getInputStream());
			mr = new MessageReceiver(in, this);
			Thread t = new Thread(mr);
			out = new ObjectOutputStream(socket.getOutputStream());
			t.start();
			list = l;
		}
		catch(Exception e)
		{
			System.out.println("Unable to connect.");
			//e.printStackTrace();
		}
	}

	public void deliverMessage(String s)
	{
		System.out.println("A client said:" + s);
		String[] part = s.split(",");
		int x = Integer.parseInt(part[0]);
		int y = Integer.parseInt(part[1]);
		for(int i = 0; i < list.size(); i++)
		{
			list.get(i).sendMail(s);
		}
	}

	public void sendMail(String s)
	{
		try
		{
			out.writeObject(s);
		}
		catch(Exception e)
		{
			System.out.println("Client can not receive mail.");
			//e.printStackTrace();
		}
	}

	public void removeMe()
	{
		list.remove(this);
		System.out.println("Client left.  There are " + list.size() + " clients connected.");
	}

	public void run()
	{
		System.out.println("New client is here!!!");
	}
}