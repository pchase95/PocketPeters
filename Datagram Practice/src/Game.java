import java.util.*;
import javax.swing.*;

public class Game
{
	private Scanner keys;
	private DatagramClient client;
	private DatagramServer server;

	public static void main(String[] args)
	{
		new Game();
	}
	
	public Game()
	{
		keys = new Scanner(System.in);
		client = new DatagramClient(this, "localhost");
		client.start();
		if(JOptionPane.showConfirmDialog(null, "Do you want to run the server?") == 0)
		{
			server = new DatagramServer(this);
			server.start();
		}
		go();
	}
	

	private void go()
	{
		String input;
		while(true)
		{
			input = getInput();
			client.sendData(input.getBytes());
			if(input.equals("q"))
			{
				System.out.println("bye bye");
				break;
			}
		}
		System.exit(0);
	}
	
	
	private String getInput()
	{
		System.out.println("Enter a string: ");
		return keys.nextLine();
	}
}
