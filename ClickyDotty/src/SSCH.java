import java.util.*;
import java.io.*;
import java.net.*;

public class SSCH implements Runnable, PositionListener
{
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private ArrayList<SSCH> clients;
	private PositionReceiver PR;
	
	public SSCH(Socket sock, ArrayList<SSCH> clients)
	{
		try
		{
			in = new ObjectInputStream(sock.getInputStream());
			PR = new PositionReceiver(in, this);
			out = new ObjectOutputStream(sock.getOutputStream());
			Thread t = new Thread(PR);
			t.start();
			this.clients = clients;
		}
		catch(Exception e)
		{
			System.out.println("Unable to connect.");
		}	
	}
	
	public void run()
	{
		System.out.println("A new Peter is here");
	}
	
	public void movePeter(int newX, int newY)
	{
		for(SSCH client : clients)
		{
			client.sendCoords(newX, newY);
		}
	}
	
	private void sendCoords(int newX, int newY)
	{
		try
		{
			Peter peter = (Peter)in.readObject();
			peter.setNewX(newX);
			peter.setNewY(newY);
			out.writeUnshared(peter);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void removeUser()
	{
		System.out.println("BYE BYE");
		clients.remove(this);
	}
}
