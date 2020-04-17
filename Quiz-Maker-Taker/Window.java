//Window.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Window extends JFrame implements ActionListener, AdjustmentListener {
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
		
		this.mainMenu();
	}//end constructor
	
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
		this.surface.repaint();
		this.surface.validate();
		
		this.surface.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		//back button
		this.btnOptions.add(new JButton("Back"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 0;
		this.surface.add(this.btnOptions.get(this.btnOptions.size() - 1), constr);
		
		//title label
		JLabel title = new JLabel(name);
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 1;
		constr.gridy = 0;
		this.surface.add(title, constr);
		
		//create the quiz object
		if (this.quizzes.size() > 0) {
			if (!this.quizzes.get(this.quizzes.size() - 1).getName().equals(name)) {
				ScoreBasedQuiz newQuiz = new ScoreBasedQuiz();
				this.quizzes.add(newQuiz);
				this.quizzes.get(this.quizzes.size() - 1).setName(name);
			}
		} else {
			ScoreBasedQuiz newQuiz = new ScoreBasedQuiz();
			this.quizzes.add(newQuiz);
			this.quizzes.get(this.quizzes.size() - 1).setName(name);
		}


		//add the scroll pane
		

		constr.fill = GridBagConstraints.BOTH;
		constr.weightx = 1.0;
		constr.weighty = 20.0;
		constr.gridx = 0;
		constr.gridy = 1;
		constr.gridwidth = 60;
		constr.gridheight = 60;
		this.surface.add(this.quizzes.get(this.quizzes.size() - 1).getMakerPanel(), constr);
		this.setPreferredSize(new Dimension(800,600));
		this.pack();
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
		//this.surface.revalidate();
	}//end actionPerformed
	
	//so i can revalidate when the JScrollPane is scrolled
	public void adjustmentValueChanged(AdjustmentEvent e) {
		System.out.println("Adjustement value changed");
		this.repaint();
		this.revalidate();
		
	}
}