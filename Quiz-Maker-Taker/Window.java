//Window.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Window extends JFrame implements ActionListener {
	Screens previousScreen = Screens.MAIN_MENU;
	String qName;
	//ArrayLists of different types of components
	Container surface;
	ArrayList<JButton> btnOptions = new ArrayList<JButton>();
	//the list of textFields
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
		this.setLocationRelativeTo(null);
		this.loadQuizzes();
		
		this.qName = "";
		this.mainMenu();
	}//end constructor
	
	//mainMenu method
	public void mainMenu() {
		//set the previous screen, main menu's previous screen is main menu
		this.previousScreen = Screens.MAIN_MENU;
		//do this on each screen to minimize space taken up by the btnOptions arrayList
		this.btnOptions.removeAll(this.btnOptions);
		
		this.surface.removeAll();
		this.surface.setLayout(new GridLayout(2, 1));
		//top panel for the main menu
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		//add label
		top.add(new JLabel("Quiz-Maker-Taker"));
		
		//bottom panel for the main menu
		JPanel bottom = new JPanel();
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
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		top.add(new JLabel("Select the type of quiz you want to make."));
		
		JPanel middle = new JPanel();
		middle.setLayout(new FlowLayout());
		
		//Score based quiz button
		this.btnOptions.add(new JButton("Score Based Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		middle.add(this.btnOptions.get(this.btnOptions.size() - 1));
		
		//Finite results quiz button
		this.btnOptions.add(new JButton("Finite Results Quiz"));
		this.btnOptions.get(this.btnOptions.size() - 1).addActionListener(this);
		middle.add(this.btnOptions.get(this.btnOptions.size() - 1));
		
		JPanel bottom = new JPanel();
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
	
	//nameQuiz method
	public <T extends Quiz> void nameQuiz(T newQuiz) {
		//set previous screen for the back button
		this.previousScreen = Screens.MAKER_TYPE_SELECTION;
		this.btnOptions.removeAll(this.btnOptions);
		this.textFields.removeAll(this.textFields);
	
		this.surface.removeAll();
		this.surface.repaint();
		this.surface.revalidate();
		this.surface.setLayout(new GridLayout(2, 1));
		
		//add the new quiz to the quizzes ArrayList
		this.quizzes.add(newQuiz);
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		top.add(new JLabel("Name your quiz:"));
		//textField for entering the name of the quiz
		this.textFields.add(new JTextField(20));
		top.add(this.textFields.get(this.textFields.size() - 1));
		
		JPanel bottom = new JPanel();
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
	
	//quizUI method
	public void quizUI(Screens scrn, int index) {
		//Setup
		//remove all and revalidate.
		this.previousScreen = scrn;
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
		JLabel title = new JLabel(this.quizzes.get(this.quizzes.size() - 1).getName());
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx =0.2;
		constr.gridx = 1;
		constr.gridy = 0;
		constr.insets = new Insets(0, 0, 0, 0);
		this.surface.add(title, constr);
		
		//quiz panel
		constr.fill = GridBagConstraints.BOTH;
		constr.weightx = 1.0;
		constr.weighty = 1.0;
		constr.gridx = 0;
		constr.gridy = 1;
		constr.gridwidth = 60;
		constr.gridheight = 60;
		constr.anchor = GridBagConstraints.FIRST_LINE_START;
		this.surface.add(this.quizzes.get(index).getPanel(), constr);
		
		//finish button
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
	}//end quizUI
	
	//takerSelection method
	public void takerSelection() {
		this.previousScreen = Screens.MAIN_MENU;
		this.btnOptions.removeAll(this.btnOptions);
		this.textFields.removeAll(this.textFields);
		this.surface.removeAll();
		this.surface.repaint();
		this.surface.revalidate();
		
		this.surface.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		//back button
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 0;
		constr.insets = new Insets(10, 10, 10, 10);
		this.surface.add(backBtn, constr);
		
		//select quiz lbl
		JLabel selectLbl = new JLabel("Select the Quiz you want to take.");
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 1;
		constr.gridy = 0;
		this.surface.add(selectLbl, constr);
		
		//panePnl goes inside the scrollpane
		JPanel panePnl = new JPanel();
		panePnl.setLayout(new FlowLayout());
		panePnl.setPreferredSize(new Dimension(800, 600));
		for (int i = 0; i < this.quizzes.size(); i++) {
			JButton quizBtn = new JButton(this.quizzes.get(i).getName());
			quizBtn.addActionListener(this);
			panePnl.add(quizBtn); 
		}
		
		//scrollpane
		JScrollPane pane = new JScrollPane(panePnl);
		pane.setBorder(BorderFactory.createEmptyBorder());
		constr.fill = GridBagConstraints.BOTH;
		constr.weightx = 1.0;
		constr.weighty = 1.0;
		constr.gridx = 0;
		constr.gridy = 1;
		constr.gridwidth = 60;
		constr.gridheight = 60;
		constr.anchor = GridBagConstraints.FIRST_LINE_START;
		this.surface.add(pane, constr);
		this.pack();
	}//end takerSelection
	
	//results method
	public void results(String rslt) {
		//clear the screen
		this.previousScreen = Screens.TAKER;
		this.surface.removeAll();
		this.surface.repaint();
		this.surface.revalidate();
		
		this.surface.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		//result label
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 0;
		JLabel rsltLbl = new JLabel("Your Result: " + rslt);
		this.surface.add(rsltLbl, constr);
		
		//name prompt label
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 1;
		JLabel enterNameLbl = new JLabel("Enter your name below and hit 'Save and Finish'");
		this.surface.add(enterNameLbl, constr);
		
		//name text field
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 1.0;
		constr.gridx = 0;
		constr.gridy = 2;
		this.textFields.add(new JTextField());
		this.surface.add(this.textFields.get(this.textFields.size() - 1), constr);
		
		//save and finish button
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.gridx = 0;
		constr.gridy = 3;
		JButton finishBtn = new JButton("Save and Finish");
		finishBtn.addActionListener(this);
		this.surface.add(finishBtn, constr);
		
		this.pack();
	}//end results
	
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
	
	//loadQuizzes method
	@SuppressWarnings("unchecked")
	public void loadQuizzes() {
		try {
			FileInputStream inFile = new FileInputStream("Quizzes.dat");
			ObjectInputStream inObj = new ObjectInputStream(inFile);
			this.quizzes = (ArrayList<Quiz>)inObj.readObject();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}//end loadQuizzes
	
	//saveScore method
	public void saveScore(String name, String score) {
		try {
			FileWriter outFile = new FileWriter("Scores.txt", true);
			PrintWriter output = new PrintWriter(outFile);
			
			output.println("Name: " + name);
			output.println("Quiz: " + this.qName);
			output.println("Score: " + score);
			output.println("---------------------------");
			outFile.close();
			output.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}//end saveScore
		
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
				this.quizzes.remove(this.quizzes.get(this.quizzes.size() - 1));
				this.makerTypeSelection();
			} else if (this.previousScreen == Screens.NAME_QUIZ) {
				this.quizzes.remove(this.quizzes.get(this.quizzes.size() - 1));
				this.makerTypeSelection();
			} else if (this.previousScreen == Screens.TAKER_SELECTION) {
				this.takerSelection();
				
			} else {
				System.out.println("no previous screen.");
			}
		}
		//select submit
		if (eventButton.getText().equals("Submit")) {
			//determine what the sumbit button does based on previous screen
			if (this.previousScreen == Screens.MAKER_TYPE_SELECTION) {
				if (!this.textFields.get(0).getText().equals("")) {
					this.quizzes.get(this.quizzes.size() - 1).setName(this.textFields.get(0).getText());
					this.quizUI(Screens.NAME_QUIZ, this.quizzes.size() - 1);
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
			this.takerSelection();
		}
		//select score based quiz
		if (eventButton.getText().equals("Score Based Quiz")) {
			this.nameQuiz(new ScoreBasedQuiz());
		}
		//select finite results quiz
		if (eventButton.getText().equals("Finite Results Quiz")) {
			this.nameQuiz(new ScoreBasedQuiz());
		}
		//select Save and Finish
		if (eventButton.getText().equals("Save and Finish")) {
			if (this.previousScreen == Screens.NAME_QUIZ) {
				if (this.quizzes.get(this.quizzes.size() - 1).isValid()) {
					this.quizzes.get(this.quizzes.size() - 1).updateQuestions();
					this.quizzes.get(this.quizzes.size() - 1).createTakerPanel();
					this.saveQuizzes();
					this.mainMenu();
				}
			} else if (this.previousScreen == Screens.TAKER_SELECTION) {
				for (int i = 0; i < this.quizzes.size(); i++) {
					if (this.quizzes.get(i).getName().equals(this.qName)) {
						this.results(this.quizzes.get(i).getResult());
						break;
					}
				}
			} else {
				if (!this.textFields.get(this.textFields.size() - 1).getText().equals("")) {
					for (int i = 0; i < this.quizzes.size(); i++) {
						if (this.quizzes.get(i).getName().equals(this.qName)) {
							String name = this.textFields.get(this.textFields.size() - 1).getText();
							String rslt = this.quizzes.get(i).getResult();
							this.saveScore(name, rslt);
							break;
						}
					}
					this.mainMenu();
				}
			}
		}
		//select a quiz to take
		for (int i = 0; i < this.quizzes.size(); i++) {
			if (eventButton.getText().equals(this.quizzes.get(i).getName())) {
				this.qName = this.quizzes.get(i).getName();
				this.quizUI(Screens.TAKER_SELECTION, i);
				break;
			}
		}
	}//end actionPerformed
}