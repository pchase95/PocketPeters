import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Map extends JPanel implements ActionListener
{
	public Peters peter = new Peters(300, 300);
	public Peters peter2 = new Peters(300, 300);
	private Mouse mouse = new Mouse(peter);
	public static void main(String[] args)
	{
		new Map().go();
	}

	private void go()
	{
		buildMap();
	}

	public void buildMap()
	{
		JFrame frame = new JFrame("PC:MO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650,650);
		frame.add(this);
		Timer timer = new Timer(40,this);
		timer.start();
		frame.setVisible(true);
		this.setFocusable(true);
	}

	public Map()
	{
		addMouseListener(mouse);
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect(0,0,650,650);
		peter.paintPeter(g);
		peter2.paintPeter(g);
	}

	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
}