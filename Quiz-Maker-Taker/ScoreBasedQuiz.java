//ScoreBasedQuiz.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class ScoreBasedQuiz implements Quiz, ActionListener, Serializable {
	ArrayList<Question> questions = new ArrayList<Question>();
	String name;
	
	//maker components
	//ArrayList<Panel> qPanels = new ArrayList<Panel>();
	boolean framesUpdated = true;
	Panel quizPanel = new Panel();
	JButton removeQuestionBtn = new JButton("-");
	JButton addSnglAnsBtn = new JButton("Add Single Answer Question");
	JButton addMltiAnsBtn = new JButton("Add Multi Answer Question");
	
	public ScoreBasedQuiz() {
		this.name = "";
		this.removeQuestionBtn.addActionListener(this);
		this.addSnglAnsBtn.addActionListener(this);
		this.addMltiAnsBtn.addActionListener(this);
		this.createMakerPanel();
	}
	//setName method
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	
	/* might not use this
	public Question getQuestion(int index) {
		return this.questions.get(index);
    }
	*/
	
	
	//addQuestion method
	//adds a question after another question
	public void addQuestion(String type) {
		if (type.equals("Single Answer")) {
			this.questions.add(new SingleAnswerQ());
		} else {
			System.out.println("Add multiAnswerQ");
		}
	}//end addQuestion
	
	//removeQuestion method
	public void removeQuestion(int index) {
		this.questions.remove(index);
	}//end removeQuestion
	
	public void createMakerPanel() {
		this.quizPanel.removeAll();
		this.quizPanel.setLayout(new BoxLayout(this.quizPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < this.questions.size(); i++) {
			Panel nestPanel = new Panel();
			nestPanel.setLayout(new FlowLayout());
			
			nestPanel.add(new JLabel(Integer.toString(i + 1)));
			nestPanel.add(this.removeQuestionBtn);
			nestPanel.add(this.questions.get(i).getMakerPanel());
			this.quizPanel.add(nestPanel);
		}
		this.quizPanel.add(this.addSnglAnsBtn);
		this.quizPanel.add(this.addMltiAnsBtn);
		this.framesUpdated = true;
		
		
	}
	public Panel getMakerPanel() {
		return this.quizPanel;
	}
	
	public boolean getUpdated() {
		return this.framesUpdated;
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton eventButton = null;
		
		if (e.getSource() instanceof JButton) {
			eventButton = (JButton)e.getSource();
		}
		
		if (eventButton.getText().equals("-")) {
			System.out.println("removeAnswerButton");
		}
		if (eventButton.getText().equals("Add Multi Answer Question")) {
			this.addQuestion("Multi Answer");
		}
		if (eventButton.getText().equals("Add Single Answer Question")) {
			this.addQuestion("Single Answer");
			this.framesUpdated = false;
			this.createMakerPanel();
			this.quizPanel.revalidate();
		}
	}
	
}