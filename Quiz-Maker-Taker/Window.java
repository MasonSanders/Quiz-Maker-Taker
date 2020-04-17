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
	
	//makerTypeSelection method
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
	}//end makerTypeSelection
	
	//nameScore method
	public void nameScore() {
		//set previous screen for the back button
		this.previousScreen = Screens.MAKER_TYPE_SELECTION;
		this.btnOptions.removeAll(this.btnOptions);
		this.textFields.removeAll(this.textFields);
	
		this.surface.removeAll();
		this.surface.repaint();
		this.surface.revalidate();
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
		
	}//end nameScore
	
	//makerScore method
	public void makerScore(String name) {
		//Setup
		//remove all and revalidate.
		this.previousScreen = Screens.NAME_SCORE;
		this.btnOptions.removeAll(this.btnOptions);
		this.textFields.removeAll(this.textFields);
		this.surface.removeAll();
		this.surface.repaint();
		this.surface.revalidate();
		
		//set the gridbaglayout and define the constriants
		this.surface.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		
		//back button
		this.btnOptions.add(new JButton("Back"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.0;
		constr.gridx = 0;
		constr.gridy = 0;
		constr.insets = new Insets(10, 10, 10, 10);
		this.surface.add(this.btnOptions.get(this.btnOptions.size() - 1), constr);
		
		//title label
		JLabel title = new JLabel(name);
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx =0.2;
		constr.gridx = 1;
		constr.gridy = 0;
		constr.insets = new Insets(0, 0, 0, 0);
		this.surface.add(title, constr);
		
		//create the quiz object
		ScoreBasedQuiz newQuiz = new ScoreBasedQuiz();
		this.quizzes.add(newQuiz);
		this.quizzes.get(this.quizzes.size() - 1).setName(name);
		

		constr.fill = GridBagConstraints.BOTH;
		constr.weightx = 1.0;
		constr.weighty = 1.0;
		constr.gridx = 0;
		constr.gridy = 1;
		constr.gridwidth = 60;
		constr.gridheight = 60;
		constr.anchor = GridBagConstraints.FIRST_LINE_START;
		this.surface.add(this.quizzes.get(this.quizzes.size() - 1).getMakerPanel(), constr);
		
		
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.0;
		constr.gridx = 0;
		constr.gridy = 62;
		constr.gridwidth = 1;
		constr.gridheight = 1;
		constr.insets = new Insets(10, 10, 10 ,10);
		constr.anchor = GridBagConstraints.CENTER;
		JButton finishBtn = new JButton("Save and Finish");
		finishBtn.addActionListener(this);
		this.surface.add(finishBtn, constr);
		this.pack();
	}
	
	//saveQuizzes method
	public void saveQuizzes() {
		try {
			FileOutputStream outFile = new FileOutputStream("Quizzes.dat");
			ObjectOutputStream outObj = new ObjectOutputStream(outFile);
			outObj.writeObject(this.quizzes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}//end saveQuizzes
	
	@SuppressWarnings("unchecked")
	public void loadQuizzes() {
		try {
			FileInputStream inFile = new FileInputStream("Quizzes.dat");
			ObjectInputStream inObj = new ObjectInputStream(inFile);
			this.quizzes = (ArrayList<Quiz>)inObj.readObject();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
	//actionPerformed method
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
				this.quizzes.remove(this.quizzes.get(this.quizzes.size() - 1));
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
		//select Save and Finish
		if (eventButton.getText().equals("Save and Finish")) {
			this.saveQuizzes();
			this.mainMenu();
		}
	}//end actionPerformed
}