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
		
	}
	
	public void movePeter()
	{
		
	}
	
	public void removeUser()
	{
		
	}
}
