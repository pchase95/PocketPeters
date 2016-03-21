import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;

// Pocket Peters Login Screen
public class PPLogin implements ActionListener
{

// Initialize all of my values.
	JPanel panel = new JPanel();
	JFrame frame = new JFrame("Dank Meme");

	private JButton loginButton;
	private JButton saveButton;
	private JButton exitButton;

	private JTextField field;
	private JTextField username;
	private JTextField password;

	private String userNM;
	private String userPW;

	private JScrollPane scrollPane;

	private JFileChooser jc;

	private File input;

	private Color background = new Color(236, 240, 241);

	public Font label = new Font("Serif", Font.BOLD, 14);
	public Font h1 = new Font("Serif", Font.BOLD, 24);

	public static void main(String[] args)
	{
		new PPLogin();

	// Set sytem look and feel based on OS
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}

		catch (Exception e)
		{
		}
	}


	public PPLogin()
	{

	// Set up my panel for work.
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		panel.setLayout(null);
		panel.setBackground(background);

	// Save, Open and Exit Program buttons
		loginButton = new JButton("Login");
		loginButton.setSize(160, 30);
		loginButton.setLocation(125, 180);
		loginButton.addActionListener(this);
		panel.add(loginButton);

		exitButton = new JButton("Exit");
		exitButton.setSize(100, 30);
		exitButton.setLocation(325, 180);
		exitButton.addActionListener(this);
		panel.add(exitButton);

	//Title label
		JLabel title = new JLabel("Nice Meme");
		title.setFont(h1);
		title.setBounds(130, 25, 300, 50);

	// Username field
		JLabel unLabel = new JLabel("Username");
		unLabel.setFont(label);
		unLabel.setBounds(35, 94, 200, 50);
		username = new JTextField(3);
		username.setSize(300,20);
		username.setLocation(125,110);
		panel.add(username);

	// Password field
		JLabel pwLabel = new JLabel("Password");
		pwLabel.setFont(label);
		pwLabel.setBounds(35, 125, 200, 50);
		password = new JTextField(3);
		password.setSize(300,20);
		password.setLocation(125,140);
		panel.add(password);

	// Add all my labels to the panels.
		panel.add(unLabel);
		panel.add(pwLabel);
		panel.add(title);

	// Set frame booleans.
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == exitButton)
		{
			System.exit(0);
		}
	}

	public void setStrings()
	{
		userNM = username.getText();
		userPW = password.getText();
	}
}
