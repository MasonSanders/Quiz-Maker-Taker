//SingleAnswerQ.java
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class SingleAnswerQ implements Question, ActionListener, Serializable {
	String qstn;
	String correctAns;
	ArrayList<String> answers = new ArrayList<String>();
	
	//maker components
	Panel qPanel = new Panel();
	JButton addAnswerBtn = new JButton("+");
	JButton removeAnswerBtn = new JButton("-");
	JTextField qField = new JTextField(30);
	ArrayList<JRadioButton> correctAnsRdo = new ArrayList<JRadioButton>();
	ArrayList<JTextField> answerFields = new ArrayList<JTextField>();
	
	
	//constructor
	public SingleAnswerQ() {
		this.qstn = "";
		this.correctAns = "";
		this.addAnswerBtn.addActionListener(this);
		this.removeAnswerBtn.addActionListener(this);
		this.createMakerPanel();
	}
	
	//setQuestion method
	public void setQuestion(String q) {
		this.qstn = q;
	}
	
	//getQuestion method
	public String getQuestion() {
		return this.qstn;
	}
	
	//addAnswer method
	public void addAnswer(String a) {
		this.answers.add(a);
	}
	
	//getAnswerList method
	public ArrayList<String> getAnswerList() {
		return this.answers;
	}
	
	//setCorrectAnswer method
	public void setCorrectAnswer(String ans) {
		this.correctAns = ans;
	}
	
	//getCorrectAnswer method
	public String getCorrectAnswer() {
		return this.correctAns;
	}
	
	//createMakerPanel method, creates the panel that shows for the question in the maker
	public void createMakerPanel() {
		this.qPanel.removeAll();
		this.qPanel.setLayout(new GridLayout(2, 1));
		
		//create nested panels
		Panel top = new Panel();
		top.setLayout(new FlowLayout());
		top.add(this.qField);
		
		Panel bottom = new Panel();
		bottom.setLayout(new GridLayout(0, 4));
		//make the fields for each added question. shouldn't loop initially.
		for (int i = 0; i < this.answerFields.size(); i++) {
			bottom.add(this.removeAnswerBtn);
			bottom.add(this.answerFields.get(i));
			bottom.add(this.correctAnsRdo.get(i));
			bottom.add(new JLabel("Correct Answer"));
		}
		bottom.add(this.addAnswerBtn);
		this.qPanel.add(top);
		this.qPanel.add(bottom);
		
	}
	
	public Panel getMakerPanel() {
		return this.qPanel;
	}
	
	//update method
	//stores all of the strings to not component variables
	public void update() {
		this.answers.removeAll(this.answers);
		for (int i = 0; i < this.answerFields.size(); i++) {
			this.addAnswer(this.answerFields.get(i).getText());
			if (this.correctAnsRdo.get(i).isSelected()) {
				this.setCorrectAnswer(this.answerFields.get(i).getText());
			}
		}
		this.qstn = this.qField.getText();
	}
	
	//addNewAnswerFields method
	//adds the components for a new answer and then 
	public void addNewAnswerFields() {
		this.answerFields.add(new JTextField(25));
		this.correctAnsRdo.add(new JRadioButton());
	}
	
	//actionPerformed method
	public void actionPerformed(ActionEvent e) {
		JButton eventButton = null;
		if (e.getSource() instanceof JButton) {
			eventButton = (JButton)e.getSource();
		}
		
		if (eventButton.getText().equals("+")) {
			this.update();
			this.addNewAnswerFields();
			this.createMakerPanel();
			this.qPanel.revalidate();
		}
		if (eventButton.getText().equals("-")) {
			System.out.println("remove Answer btn");
		}
	}
	
}