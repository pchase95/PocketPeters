import java.util.*;
import java.io.*;
import java.net.*;

public class PositionReceiver implements Runnable
{
	private ObjectInputStream in;
	private PositionListener PL;

	public PositionReceiver(ObjectInputStream ois, PositionListener PL)
	{
		in = ois;
		this.PL = PL;
	}
	
	public void run()
	{
		
	}
}
