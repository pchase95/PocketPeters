import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Map extends JPanel implements KeyListener, ActionListener
{
	private JFrame f;
	private Player player;
	
	public Map()
	{
		f = new JFrame("Map");
		f.setSize(800,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		player = new Player(30, 30);
		add(player);
		addKeyListener(this);
		f.add(this);
		f.setFocusable(true);
		f.setVisible(true);
		
	}
	
	public void paintComponent(Graphics g)
	{
		player.paintPlayer(g);
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			System.out.println("Nice MEME!");
	}		
	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e){}

	public void actionPerformed(ActionEvent e)
	{
		repaint();		
	}
}
