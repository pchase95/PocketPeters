import java.util.*;
import java.io.*;
import java.net.*;

public class Client implements PositionListener
{
	private String client_name;
	private String client_IP;
	private Map map;
	private Peter peter;
	private ArrayList<Peter> peters = new ArrayList<>();
	private boolean lob = true;
	
	private ObjectOutputStream out;
	private Socket socket;
	ObjectInputStream in;
	PositionReceiver PR;
	
	private Client(){}
	public Client(String client_name, String client_IP)
	{
		this.client_name = client_name;
		this.client_IP = client_IP;
		peter = new Peter(300, 300, client_name);
		Connect();
		Lobby();
		map = new Map(this);
	}
	
	private void Lobby()
	{
		Scanner keys = new Scanner(System.in);
		String meme = "";
		while(!(meme.equals("go")))
		{
			try
			{
				out.writeObject(peter);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			System.out.printf("Players in lobby: %s%n", peters.toString());
			System.out.println("Type 'go' to begin game");
			System.out.println("Type anythign else to refresh");
			meme = keys.nextLine();
		}
		lob = false;
	}

	private void Connect()
	{
		try
		{
			socket = new Socket(client_IP, 4324);
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
		if(lob == false)
		{
			for(Peter petey : peters)
			{
				if(petey.getID().equals(p.getID()))
				{
					petey.moveMe(p.getX(), p.getY());
				}
			}
			map.repaint();
		}
		else
		{
			boolean add = true;
			for(Peter petey : peters)
			{
				if(p.getID().equals(petey.getID()))
				{
					add = false;
				}
			}
			if(add == true)
			{
				peters.add(p);
			}
		}
	}
	
	public void movePeter(int x, int y)
	{
		peter.moveMe(x, y);
		try
		{
			out.writeUnshared(peter);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void removeUser()
	{
		System.exit(0);
	}
	
	public Peter getPeter() { return this.peter; }
	public ArrayList<Peter> getPeters() { return this.peters; }

}