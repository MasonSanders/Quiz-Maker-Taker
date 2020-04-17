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
	JPanel quizPanel = new JPanel();
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
		this.quizPanel.setPreferredSize(new Dimension(800, 600));
		this.quizPanel.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		JPanel contentPnl = new JPanel();
		//contentPnl.setMaximumSize(new Dimension(800, Integer.MAX_VALUE));
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
			
			JButton removeBtn = new JButton("-");
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.gridx = 1;
			constr.gridy = i;
			constr.insets = new Insets(0, 5, 0, 5);
			constr.gridwidth = 1;
			constr.gridheight = 1;
			constr.anchor = GridBagConstraints.FIRST_LINE_START;
			contentPnl.add(removeBtn, constr);
			
			JPanel questionPnl = this.questions.get(i).getMakerPanel();
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
		
		//singleanswerq button
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.0;
		constr.weighty = 1.0;
		constr.gridx = 0;
		constr.gridy = this.questions.size();
		constr.gridwidth = 3;
		constr.insets = new Insets(0, 10, 0, 10);
		contentPnl.add(this.addSnglAnsBtn, constr);
		
		//multianswerq button
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
		pane.setBorder(null);
		
		constr.fill = GridBagConstraints.BOTH;
		constr.weightx = 1.0;
		constr.weighty = 1.0;
		constr.gridx = 0;
		constr.gridy = 0;
		constr.anchor = GridBagConstraints.FIRST_LINE_START;
		this.quizPanel.add(pane);
	}
	
	public JPanel getMakerPanel() {
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