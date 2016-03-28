import java.util.*;
import java.io.*;
import java.net.*;

public class Client implements PositionListener
{
	private Peter peter = new Peter(300, 300);
	public static void main(String[] args)
	{
		new Client().go();
	}
	
	public void go()
	{
		//map = new Map(peter, this);
		Server.map.addPeter(peter);
		Server.map.setPL(this);
		Server.map.buildMap();
		connect();
	}
	
	private void connect()
	{
		try
		{
			Socket sock = new Socket("localhost", 4324);
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
			PositionReceiver PR = new PositionReceiver(in, this);
			Thread t = new Thread(PR);
			t.start();
			while(true)
			{
				out.writeUnshared(peter); //why can I not do this jesus
				Thread.sleep(250);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void movePeter(int newX, int newY, Peter p)
	{
		Server.map.setPeter(peter);
		peter.movePeter(newX, newY);
		peter.setMove(true);
	}
	
	public void removeUser()
	{
		System.exit(0);
	}
}