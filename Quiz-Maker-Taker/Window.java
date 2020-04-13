//Window.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Window extends JFrame implements ActionListener {
	//ArrayLists of different types of components
	Container surface;
	ArrayList<JButton> btnOptions = new ArrayList<JButton>();
	
	//main method
	public static void main(String[] args) {
		//create the window object
		Window win = new Window();
		
	}//end main
	
	//Constructor
	public Window() {
		//call a JFrame that gets the app title
		super("Quiz-Maker-Taker");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.surface = this.getContentPane();
		this.pack();
		this.setVisible(true);
		//create the components used in the main menu and call the main menu
		this.createBtnOptions();
		this.mainMenu();
	}//end constructor
	
	//createMainMenuComponents method
	public void createBtnOptions() {
		this.btnOptions.add(new JButton("Make a Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		this.btnOptions.add(new JButton("Take a Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		
	}//end createMainMenuComponents
	
	//mainMenu method
	public void mainMenu() {
		this.surface.setLayout(new GridLayout(2, 1));
		//top panel for the main menu
		Panel top = new Panel();
		top.setLayout(new FlowLayout());
		//add label from the menuComps arrayList
		top.add(new JLabel("Quiz-Maker-Taker"));
		
		//bottom panel for the main menu
		Panel bottom = new Panel();
		bottom.setLayout(new FlowLayout());
		//add button from the menuComps arraylist
		bottom.add(this.btnOptions.get(0));
		bottom.add(this.btnOptions.get(1));
		//add the panels to the surface
		this.surface.add(top);
		this.surface.add(bottom);
		//pack the surface
		this.pack();
	}//end mainMenu
		
	//actionPerformed
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnOptions.get(0)) {
			System.out.println("Clicked Make a Quiz");
		}
		if (e.getSource() == this.btnOptions.get(1)) {
			System.out.println("Clicked Take a Quiz");
		}
	}//end actionPerformed
}