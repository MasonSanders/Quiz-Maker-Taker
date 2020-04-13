//Window.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Window extends JFrame implements ActionListener {
	Screens previousScreen = Screens.MAIN_MENU;
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
		this.btnOptions.add(new JButton("Back"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		
		//button components for the main menu screen
		this.btnOptions.add(new JButton("Make a Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		this.btnOptions.add(new JButton("Take a Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		
		//button components for the maker type selection screen
		this.btnOptions.add(new JButton("Score Based Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		this.btnOptions.add(new JButton("Finite Results Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		
	}//end createMainMenuComponents
	
	//mainMenu method
	public void mainMenu() {
		//set the current screen
		this.previousScreen = Screens.MAIN_MENU;
		
		
		this.surface.removeAll();
		this.surface.setLayout(new GridLayout(2, 1));
		//top panel for the main menu
		Panel top = new Panel();
		top.setLayout(new FlowLayout());
		//add label
		top.add(new JLabel("Quiz-Maker-Taker"));
		
		//bottom panel for the main menu
		Panel bottom = new Panel();
		bottom.setLayout(new FlowLayout());
		//add button from the btnOptions arraylist
		bottom.add(this.btnOptions.get(1));
		bottom.add(this.btnOptions.get(2));
		//add the panels to the surface
		this.surface.add(top);
		this.surface.add(bottom);
		//pack the surface
		this.pack();
	}//end mainMenu
	
	public void makerTypeSelection() {
		//set the previous screen for the back button.
		this.previousScreen = Screens.MAIN_MENU;
		
		
		this.surface.removeAll();
		this.surface.setLayout(new GridLayout(3, 1));
		Panel top = new Panel();
		top.setLayout(new FlowLayout());
		top.add(new JLabel("Select the type of quiz you want to make."));
		
		Panel middle = new Panel();
		middle.setLayout(new FlowLayout());
		//add buttons from btnOptions
		middle.add(this.btnOptions.get(3));
		middle.add(this.btnOptions.get(4));
		
		Panel bottom = new Panel();
		bottom.setLayout(new FlowLayout());
		bottom.add(this.btnOptions.get(0));
		
		//add panels to surface
		this.surface.add(top);
		this.surface.add(middle);
		this.surface.add(bottom);
		
		//pack the surface
		this.pack();
	}
		
	//actionPerformed
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnOptions.get(0)) {
			//determine where the back button takes the user.
			if (this.previousScreen == Screens.MAIN_MENU) {
				this.mainMenu();
			} else if (this.previousScreen == Screens.MAKER_TYPE_SELECTION) {
				this.makerTypeSelection();
			} else {
				System.out.println("no previous screen.");
			}
		}
		if (e.getSource() == this.btnOptions.get(1)) {
			//go to the makerTypeSelection screen
			this.makerTypeSelection();
		}
		if (e.getSource() == this.btnOptions.get(2)) {
			System.out.println("Clicked Take a Quiz");
		}
	}//end actionPerformed
}