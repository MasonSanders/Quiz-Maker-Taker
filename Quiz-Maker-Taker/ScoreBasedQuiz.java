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
	Panel quizPanel = new Panel();
	JButton addSnglAnsBtn = new JButton("Add Single Answer Question");
	JButton addMltiAnsBtn = new JButton("Add Multi Answer Question");
	
	//constructor
	public ScoreBasedQuiz() {
		this.name = "";
		this.addMltiAnsBtn.addActionListener(this);
		this.addSnglAnsBtn.addActionListener(this);
		this.createMakerPanel();
	}
	
	//setName method
	public void setName(String name) {
		this.name = name;
	}
	
	//getName method
	public String getName() {
		return this.name;
	}
	
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
	
	//createMakerPanel method
	public void createMakerPanel() {
		//try to clean the pane before it needs updated
		this.quizPanel.removeAll();
		this.quizPanel.revalidate();
		this.quizPanel.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		Panel panePnl = new Panel();
		panePnl.setLayout(new BoxLayout(panePnl, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < this.questions.size(); i++) {
			Panel nestPanel = new Panel();
			nestPanel.setLayout(new GridBagLayout());
			
			JLabel qNumLbl = new JLabel(Integer.toString(i + 1));
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.gridx = 0;
			constr.gridy = 0;
			constr.gridwidth = 1;
			constr.gridheight = 1;
			nestPanel.add(qNumLbl, constr);
			
			JButton removeBtn = new JButton("-");
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.gridx = 1;
			constr.gridy = 0;
			constr.insets = new Insets(0, 5, 0, 5);
			constr.gridwidth = 1;
			constr.gridheight = 1;
			nestPanel.add(removeBtn, constr);
			
			Panel questionPnl = this.questions.get(i).getMakerPanel();
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 1.0;
			constr.gridx = 2;
			constr.gridy = 0;
			constr.gridwidth = 4;
			constr.gridheight = 2;
			nestPanel.add(questionPnl, constr);
			
			panePnl.add(nestPanel);
		}
		Panel bottom = new Panel();
		bottom.setLayout(new GridBagLayout());
		
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.0;
		constr.gridx = 0;
		constr.gridy = 0;
		constr.gridwidth = 1;
		bottom.add(this.addSnglAnsBtn, constr);
		
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.0;
		constr.gridx = 1;
		constr.gridy = 0;
		constr.gridwidth = 1;
		bottom.add(this.addMltiAnsBtn, constr);
		
		panePnl.add(bottom);
		
		JScrollPane pane = new JScrollPane(panePnl);
		
		constr.fill = GridBagConstraints.BOTH;
		constr.gridx = 0;
		constr.gridy = 0;
		this.quizPanel.add(pane);
	}
	
	public Panel getMakerPanel() {
		return this.quizPanel;
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
			this.createMakerPanel();
			this.quizPanel.revalidate();
		}
	}
}