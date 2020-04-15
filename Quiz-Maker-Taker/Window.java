//Window.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Window extends JFrame implements ActionListener {
	Screens previousScreen = Screens.MAIN_MENU;
	//ArrayLists of different types of components
	Container surface;
	ArrayList<JButton> btnOptions = new ArrayList<JButton>();
	//the list of textFields is reset with each screen, it might make sense to do that with the buttons and other components as well later on.
	ArrayList<JTextField> textFields = new ArrayList <JTextField>();
	//the arrayList of quizzes
	ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
	
	
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
		//this.createBtnOptions();
		this.mainMenu();
	}//end constructor
	
	
	//createBtnOptionsComponents method
	/*
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
	*/
	
	//mainMenu method
	public void mainMenu() {
		//set the current screen
		this.previousScreen = Screens.MAIN_MENU;
		//do this on each screen to minimize space taken up by the btnOptions arrayList
		this.btnOptions.removeAll(this.btnOptions);
		
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
		
		//make a quiz button
		this.btnOptions.add(new JButton("Make a Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		bottom.add(this.btnOptions.get(this.btnOptions.size() - 1));
		
		//take a quiz button
		this.btnOptions.add(new JButton("Take a Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		bottom.add(this.btnOptions.get(this.btnOptions.size() - 1));
		
		
		//add the panels to the surface
		this.surface.add(top);
		this.surface.add(bottom);
		//pack the surface
		this.pack();
	}//end mainMenu
	
	public void makerTypeSelection() {
		//set the previous screen for the back button.
		this.previousScreen = Screens.MAIN_MENU;
		this.btnOptions.removeAll(this.btnOptions);
		
		this.surface.removeAll();
		this.surface.setLayout(new GridLayout(3, 1));
		Panel top = new Panel();
		top.setLayout(new FlowLayout());
		top.add(new JLabel("Select the type of quiz you want to make."));
		
		Panel middle = new Panel();
		middle.setLayout(new FlowLayout());
		
		//Score based quiz button
		this.btnOptions.add(new JButton("Score Based Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		middle.add(this.btnOptions.get(this.btnOptions.size() - 1));
		
		//Finite results quiz button
		this.btnOptions.add(new JButton("Finite Results Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		middle.add(this.btnOptions.get(this.btnOptions.size() - 1));
		
		Panel bottom = new Panel();
		bottom.setLayout(new FlowLayout());
		
		//back button
		this.btnOptions.add(new JButton("Back"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		bottom.add(this.btnOptions.get(this.btnOptions.size() - 1));
		
		//add panels to surface
		this.surface.add(top);
		this.surface.add(middle);
		this.surface.add(bottom);
		
		//pack the surface
		this.pack();
	}
	
	public void nameScore() {
		//set previous screen for the back button
		this.previousScreen = Screens.MAKER_TYPE_SELECTION;
		this.btnOptions.removeAll(this.btnOptions);
		this.textFields.removeAll(this.textFields);
		
		this.surface.removeAll();
		this.surface.setLayout(new GridLayout(2, 1));
		Panel top = new Panel();
		top.setLayout(new FlowLayout());
		top.add(new JLabel("Name your quiz:"));
		//textField for entering the name of the quiz
		this.textFields.add(new JTextField(20));
		top.add(this.textFields.get(this.textFields.size() - 1));
		
		Panel bottom = new Panel();
		bottom.setLayout(new FlowLayout());
		
		//back button
		this.btnOptions.add(new JButton("Back"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		bottom.add(this.btnOptions.get(this.btnOptions.size() - 1));
		
		//Submit button
		this.btnOptions.add(new JButton("Submit"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		bottom.add(this.btnOptions.get(this.btnOptions.size() - 1));
		
		this.surface.add(top);
		this.surface.add(bottom);
		this.pack();
		
	}
	
	public void makerScore(String name) {
		//Setup
		this.previousScreen = Screens.NAME_SCORE;
		this.btnOptions.removeAll(this.btnOptions);
		this.textFields.removeAll(this.textFields);
		this.surface.removeAll();
		this.setSize(800, 600);
		
		this.surface.setLayout(new FlowLayout());
		
		//create a secondary layer that the scrollpane will be able to hold
		Panel content = new Panel();
		content.setLayout(new GridLayout(2, 1));
		
		//top panel contains the quiz name and the back button
		Panel top = new Panel();
		top.setLayout(new FlowLayout());
		
		//back button
		this.btnOptions.add(new JButton("Back"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		top.add(this.btnOptions.get(this.btnOptions.size() - 1));
		//title label
		top.add(new JLabel(name));
		
		//maker
		ScoreBasedQuiz newQuiz = new ScoreBasedQuiz();
		newQuiz.setName(name);
		
		content.add(top);
		content.add(newQuiz.getMakerPanel());
		JScrollPane pane = new JScrollPane(content);
		pane.setBorder(null);
		this.surface.add(pane);
		
		repaint();
	}
	
	//for now, checking button presses based on their text to use less memory with the arrayList	
	//actionPerformed
	public void actionPerformed(ActionEvent e) {
		//convert the event source to a button instead of an object
		JButton eventButton = null;
		if (e.getSource() instanceof JButton) {
			eventButton = (JButton)e.getSource();
		}
		
		if (eventButton.getText().equals("Back")) {
			//determine where the back button takes the user.
			if (this.previousScreen == Screens.MAIN_MENU) {
				this.mainMenu();
			} else if (this.previousScreen == Screens.MAKER_TYPE_SELECTION) {
				this.makerTypeSelection();
			} else if (this.previousScreen == Screens.NAME_SCORE) {
				this.nameScore();
			} else {
				System.out.println("no previous screen.");
			}
		}
		//select submit
		if (eventButton.getText().equals("Submit")) {
			//determine what the sumbit button does based on previous screen
			if (this.previousScreen == Screens.MAKER_TYPE_SELECTION) {
				if (!this.textFields.get(0).getText().equals("")) {
					this.makerScore(this.textFields.get(0).getText());
				}
			}
		}
		
		//select make a quiz
		if (eventButton.getText().equals("Make a Quiz")) {
			//go to the makerTypeSelection screen
			this.makerTypeSelection();
		}
		//select take a quiz
		if (eventButton.getText().equals("Take a Quiz")) {
			System.out.println("Clicked Take a Quiz");
		}
		//select score based quiz
		if (eventButton.getText().equals("Score Based Quiz")) {
			this.nameScore();
		}
	}//end actionPerformed
}