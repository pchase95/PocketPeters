import java.util.*;
import java.io.*;
import java.net.*;

public class PositionReceiver implements Runnable
{
	private ObjectInputStream in;
	private PositionListener PL;
	private Peter peter;
	
	public PositionReceiver(ObjectInputStream in, PositionListener PL)
	{
		this.in = in;
		this.PL = PL;
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				peter = (Peter)in.readUnshared();
				PL.movePeter(peter);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				PL.removeUser();
				break;
			}
		}
	}
}