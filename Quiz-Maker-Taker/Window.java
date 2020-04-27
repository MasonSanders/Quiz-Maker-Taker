//Window.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Window extends JFrame implements ActionListener {
	Screens currentScreen = Screens.MAIN_MENU;
	String qName;
	//ArrayLists of different types of components
	Container surface;
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
		//set the current screen
		this.currentScreen = Screens.MAIN_MENU;
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
		JButton makeBtn = new JButton("Make a Quiz");
		makeBtn.addActionListener(this);
		bottom.add(makeBtn);
		
		//take a quiz button
		JButton takeBtn = new JButton("Take a Quiz");
		takeBtn.addActionListener(this);
		bottom.add(takeBtn);
		
		
		//add the panels to the surface
		this.surface.add(top);
		this.surface.add(bottom);
		//pack the surface
		this.pack();
	}//end mainMenu
		
	//nameQuiz method
	public void nameQuiz(Quiz newQuiz) {
		//set previous screen for the back button
		this.currentScreen = Screens.NAME_QUIZ;
		
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
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		bottom.add(backBtn);
		
		//Submit button
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(this);
		bottom.add(submitBtn);
		
		this.surface.add(top);
		this.surface.add(bottom);
		this.pack();
		
	}//end nameScore
	
	//quizUI method
	public void quizUI(Screens scrn, int index) {
		//Setup
		//remove all and revalidate.
		this.currentScreen = scrn;
		this.textFields.removeAll(this.textFields);
		this.surface.removeAll();
		this.surface.repaint();
		this.surface.revalidate();
		
		//set the gridbaglayout and define the constriants
		this.surface.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		//back button
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.0;
		constr.gridx = 0;
		constr.gridy = 0;
		constr.insets = new Insets(10, 10, 10, 10);
		this.surface.add(backBtn, constr);
		
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
		JButton finishBtn = new JButton("Save and Finish");
		finishBtn.addActionListener(this);
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.0;
		constr.gridx = 0;
		constr.gridy = 62;
		constr.gridwidth = 1;
		constr.gridheight = 1;
		constr.insets = new Insets(10, 10, 10 ,10);
		constr.anchor = GridBagConstraints.CENTER;
		this.surface.add(finishBtn, constr);
		this.pack();
	}//end quizUI
	
	//takerSelection method
	public void takerSelection() {
		this.currentScreen = Screens.TAKER_SELECTION;
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
		this.currentScreen = Screens.RESULTS;
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
			if (this.currentScreen == Screens.NAME_QUIZ) {
				this.quizzes.remove(this.quizzes.get(this.quizzes.size() - 1));
				this.mainMenu();
			} else if (this.currentScreen == Screens.MAKER) {
				this.quizzes.remove(this.quizzes.get(this.quizzes.size() - 1));
				this.mainMenu();
			} else if (this.currentScreen == Screens.TAKER_SELECTION) {
				this.mainMenu();
			} else if (this.currentScreen == Screens.TAKER) {
				this.takerSelection();	
			} else {
				System.out.println("no previous screen.");
			}
		}
		//select submit
		if (eventButton.getText().equals("Submit")) {
			if (!this.textFields.get(0).getText().equals("")) {
				this.quizzes.get(this.quizzes.size() - 1).setName(this.textFields.get(0).getText());
				this.quizUI(Screens.MAKER, this.quizzes.size() - 1);
			} else {
				JOptionPane.showMessageDialog(this, "Your quiz must have a name.");
			}
		}
		//select make a quiz
		if (eventButton.getText().equals("Make a Quiz")) {
			//go to the makerTypeSelection screen
			this.nameQuiz(new ScoreBasedQuiz());
		}
		//select take a quiz
		if (eventButton.getText().equals("Take a Quiz")) {
			this.takerSelection();
		}
		//select Save and Finish
		if (eventButton.getText().equals("Save and Finish")) {
			if (this.currentScreen == Screens.MAKER) {
				if (this.quizzes.get(this.quizzes.size() - 1).isValid()) {
					this.quizzes.get(this.quizzes.size() - 1).updateQuestions();
					this.quizzes.get(this.quizzes.size() - 1).createTakerPanel();
					this.saveQuizzes();
					this.mainMenu();
				} else {
					JOptionPane.showMessageDialog(this, 
						"Make sure all of your questions and answers are filled out properly");
					
				}
			} else if (this.currentScreen == Screens.TAKER) {
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
				} else {
					JOptionPane.showMessageDialog(this, "You must enter your name to submit");
				}
			}
		}
		//select a quiz to take
		for (int i = 0; i < this.quizzes.size(); i++) {
			if (eventButton.getText().equals(this.quizzes.get(i).getName())) {
				this.qName = this.quizzes.get(i).getName();
				this.quizzes.get(i).createTakerPanel();
				this.quizUI(Screens.TAKER, i);
				break;
			}
		}
	}//end actionPerformed
}