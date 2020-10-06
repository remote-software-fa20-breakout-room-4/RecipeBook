import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI implements ActionListener{
		private JFrame frame;
		private JLabel label;
		private JPanel searchPanel;
		private JPanel recipesPanel;
		private JPanel outerPanel;
		private JButton button;
		private JTextField search;
		private FlowLayout layout;

	public GUI(){
		//setup size vars
		int frameWidth = 500;
		int frameHeight = 500;

		outerPanel = new JPanel(new BorderLayout()); //create panel
		searchPanel = new JPanel(); //create panel
		recipesPanel = new JPanel(); //create panel
		//setup frame
		frame = new JFrame("Recipe Book");
		frame.setSize(frameWidth, frameHeight); //width height of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set what happens when window is closed
		outerPanel.add(searchPanel, BorderLayout.NORTH);
		outerPanel.add(recipesPanel, BorderLayout.CENTER);
		frame.add(outerPanel); //add panel to frame
		//frame.add(recipesPanel); //add panel to frame

		//create objects to put in panel
		label = new JLabel("<html>Recipe(s):<ol><li>burger<ul><li>description:</li><li>ingredients:</li><li>instructions:</li></ul></li><li>pasta<ul><li>description:</li><li>ingredients:</li><li>instructions:</li></ul></li></html>");
		//label.setBounds(10, 20, 160, 25); //x, y, w, h of label
		button = new JButton("Search");
		//button.setBounds(searchButtonX, searchButtonY, searchButtonWidth, searchButtonHeight);
		button.addActionListener(this);
		search = new JTextField(20);
		//search.setBounds(searchX, searchY, searchWidth, searchHeight);

		FlowLayout searchLayout = new FlowLayout(FlowLayout.CENTER);
		FlowLayout recipesLayout = new FlowLayout(FlowLayout.LEFT);
		//add to panel
		searchPanel.setLayout(searchLayout);
		searchPanel.add(search);
		searchPanel.add(button);
		recipesPanel.setLayout(recipesLayout);
		recipesPanel.add(label);

		frame.setVisible(true); //set window to be visible and in focus

	}


	public static void main(String[] args) {
		new GUI();
		System.out.println("Hello World!");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Clicked!");
		label.setText("recipe");
	}

}
