//ScoreBasedQuiz.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class ScoreBasedQuiz implements Quiz, ActionListener, Serializable {
	LinkedList<Question> questions = new LinkedList<Question>();
	String name;
	
	//maker components
	JPanel quizPanel = new JPanel();
	JButton addSnglAnsBtn = new JButton("Add Single Answer Question");
	JButton addMltiAnsBtn = new JButton("Add Multi Answer Question");
	ArrayList<JButton> removeBtns = new ArrayList<JButton>();
	
	//constructor
	public ScoreBasedQuiz() {
		this.name = "";
		this.addMltiAnsBtn.addActionListener(this);
		this.addSnglAnsBtn.addActionListener(this);
		this.createMakerPanel();
	}//end constructor
	
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
			this.removeBtns.add(new JButton("-"));
			this.removeBtns.get(this.removeBtns.size() - 1).addActionListener(this);
		} else {
			this.questions.add(new MultiAnswerQ());
			this.removeBtns.add(new JButton("-"));
			this.removeBtns.get(this.removeBtns.size() - 1).addActionListener(this);
		}
	}//end addQuestion
	
	//removeQuestion method
	public void removeQuestion(int index) {
		this.questions.remove(index);
		this.removeBtns.remove(index);
	}//end removeQuestion
	
	//createMakerPanel method
	public void createMakerPanel() {
		//clean the panel before recreating it;
		this.quizPanel.removeAll();
		this.quizPanel.revalidate();
		this.quizPanel.setPreferredSize(new Dimension(800, 600));
		this.quizPanel.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		JPanel contentPnl = new JPanel();
		contentPnl.setLayout(new GridBagLayout());
		for(int i = 0; i < this.questions.size(); i++) {
			
			JLabel qNumLbl = new JLabel(Integer.toString(i + 1));
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.weighty = 0.0;
			constr.gridx = 0;
			constr.gridy = i;
			constr.gridwidth = 1;
			constr.gridheight = 1;
			constr.anchor = GridBagConstraints.FIRST_LINE_START;
			constr.insets = new Insets(0, 0, 0, 0);
			contentPnl.add(qNumLbl, constr);
			
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.gridx = 1;
			constr.gridy = i;
			constr.insets = new Insets(0, 5, 0, 5);
			constr.gridwidth = 1;
			constr.gridheight = 1;
			constr.anchor = GridBagConstraints.FIRST_LINE_START;
			contentPnl.add(this.removeBtns.get(i), constr);
			
			JPanel questionPnl = this.questions.get(i).getPanel();
			constr.fill = GridBagConstraints.BOTH;
			constr.weightx = 1.0;
			constr.weighty = 0.0;
			constr.gridx = 2;
			constr.gridy = i;
			constr.gridwidth = 10;
			constr.gridheight = 1;
			constr.anchor = GridBagConstraints.CENTER;
			constr.insets = new Insets(0, 0, 10, 0);
			contentPnl.add(questionPnl, constr);
		}
		
		//single answer question button
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.0;
		constr.weighty = 1.0;
		constr.gridx = 0;
		constr.gridy = this.questions.size();
		constr.gridwidth = 3;
		constr.insets = new Insets(0, 10, 0, 10);
		contentPnl.add(this.addSnglAnsBtn, constr);
		
		//multi answer question button
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.0;
		constr.weighty = 1.0;
		constr.gridx = 3;
		constr.gridy = this.questions.size();
		constr.gridwidth = 3;
		contentPnl.add(this.addMltiAnsBtn, constr);
		
		//scrollpane
		JScrollPane pane = new JScrollPane(contentPnl);
		pane.setPreferredSize(this.quizPanel.getPreferredSize());
		pane.setBorder(BorderFactory.createEmptyBorder());
		
		constr.fill = GridBagConstraints.BOTH;
		constr.weightx = 1.0;
		constr.weighty = 1.0;
		constr.gridx = 0;
		constr.gridy = 0;
		constr.anchor = GridBagConstraints.FIRST_LINE_START;
		this.quizPanel.add(pane, constr);
	}//end createMakerPanel
	
	//createTakerPanel method
	public void createTakerPanel() {
		//have to create the taker panel for each question before getting started
		for (int i = 0; i < this.questions.size(); i++) {
			this.questions.get(i).createTakerPanel();
		}
		
		this.quizPanel.removeAll();
		this.quizPanel.repaint();
		this.quizPanel.revalidate();
		this.quizPanel.setPreferredSize(new Dimension(800, 600));
		this.quizPanel.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		JPanel contentPnl = new JPanel();
		contentPnl.setLayout(new GridBagLayout());
		for (int i = 0; i < this.questions.size(); i++) {
			//number label
			JLabel qNumLbl = new JLabel(Integer.toString(i + 1) + ".");
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.gridx = 0;
			constr.gridy = i;
			constr.gridwidth = 1;
			constr.gridheight = 1;
			constr.anchor = GridBagConstraints.FIRST_LINE_START;
			constr.insets = new Insets(0, 10, 10, 10);
			contentPnl.add(qNumLbl, constr);
			
			//question panel
			JPanel questionPnl = this.questions.get(i).getPanel();
			constr.fill = GridBagConstraints.BOTH;
			constr.weightx = 1.0;
			constr.weighty = 0.0;
			constr.gridx = 2;
			constr.gridy = i;
			constr.gridwidth = 10;
			constr.gridheight = 1;
			constr.anchor = GridBagConstraints.FIRST_LINE_START;
			constr.insets = new Insets(0, 0, 10, 0);
			contentPnl.add(questionPnl, constr);
		}
		
		//scrollpane
		JScrollPane pane = new JScrollPane(contentPnl);
		pane.setPreferredSize(this.quizPanel.getPreferredSize());
		pane.setBorder(BorderFactory.createEmptyBorder());
		
		
		constr.fill = GridBagConstraints.BOTH;
		constr.weightx = 1.0;
		constr.weighty = 1.0;
		constr.gridx = 0;
		constr.gridy = 0;
		constr.anchor = GridBagConstraints.FIRST_LINE_START;
		constr.insets = new Insets(0, 0, 0, 0);
		this.quizPanel.add(pane, constr);
		
	}//end createTakerPanel
	
	//getPanel method
	public JPanel getPanel() {
		return this.quizPanel;
	}//end getPanel
	
	public void updateQuestions() {
		for (int i = 0; i < this.questions.size(); i++) {
			this.questions.get(i).update();
		}
	}
	
	public boolean isValid() {
		boolean valid = true;
		if (this.questions.size() > 0) {
			for (int i = 0; i < this.questions.size(); i++) {
				this.questions.get(i).update();
				if (!this.questions.get(i).isValid()) {
					valid = false;
				}
			}
		} else {
			valid = false;
		}
		return valid;
	}
	
	//getResult method
	public String getResult() {
		int correctCtr = 0;
		String score = "";
		boolean allMatch = true;
		ArrayList<String> selectedAnswers;
		ArrayList<String> correctAnswers;
		for (int i = 0; i < this.questions.size(); i++) {
			allMatch = false;
			selectedAnswers = this.questions.get(i).getSelectedAnswer();
			correctAnswers = this.questions.get(i).getCorrectAnswer();
			for (int j = 0; j < correctAnswers.size(); j++) {
				if (selectedAnswers.indexOf(correctAnswers.get(j)) != -1) {
					allMatch = true;
				} else {
					allMatch = false;
				}
			}
			if (allMatch) {
				correctCtr += 1;
			}
		}
		score = Integer.toString(correctCtr) + "/" + this.questions.size();
		return score;
		
	}//end getResult
	
	public void actionPerformed(ActionEvent e) {
		JButton eventButton = null;
		
		if (e.getSource() instanceof JButton) {
			eventButton = (JButton)e.getSource();
		}
		
		if (eventButton.getText().equals("Add Multi Answer Question")) {
			this.addQuestion("Multi Answer");
			this.createMakerPanel();
			//this.quizPanel.revalidate(); 
		}
		if (eventButton.getText().equals("Add Single Answer Question")) {
			this.addQuestion("Single Answer");
			this.createMakerPanel();
			//this.quizPanel.revalidate();
		}
		
		//specific case for the remove buttons
		for (int i = 0; i < this.removeBtns.size(); i++) {
			if (eventButton == this.removeBtns.get(i)) {
				this.removeQuestion(i);
				this.createMakerPanel();
				this.quizPanel.revalidate();
			}
		}
	}
}