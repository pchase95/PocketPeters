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
	
	public void movePeter(int newX, int newY, Peter p)
	{
		for(int i = 0; i < clients.size()-1; i++)
		{
			if(clients.get(i) != this)
				clients.get(i).sendCoords(newX, newY, p);
		}
	}
	
	private void sendCoords(int newX, int newY, Peter p)
	{
		try
		{
			p.movePeter(newX, newY);
			out.writeUnshared(p);
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
