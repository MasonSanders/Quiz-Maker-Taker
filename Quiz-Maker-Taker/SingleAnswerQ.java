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
	JPanel qPanel = new JPanel();
	JButton addAnswerBtn = new JButton("Add Answer");
	JTextField qField = new JTextField("Question");
	ArrayList<JRadioButton> correctAnsRdo = new ArrayList<JRadioButton>();
	ArrayList<JTextField> answerFields = new ArrayList<JTextField>();
	ButtonGroup rdoGroup = new ButtonGroup();
	
	
	//constructor
	public SingleAnswerQ() {
		this.qstn = "";
		this.correctAns = "";
		this.addAnswerBtn.addActionListener(this);
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
		this.qPanel.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 1.0;
		constr.gridx = 0;
		constr.gridy = 0;
		constr.gridwidth = 7;
		constr.anchor = GridBagConstraints.FIRST_LINE_START;
		this.qPanel.add(this.qField, constr);
		
		//make the fields for each added question. shouldn't loop initially.
		for (int i = 0; i < this.answerFields.size(); i++) {
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.gridx = 0;
			constr.gridy = i + 1;
			constr.gridwidth = 1;
			JButton removeBtn = new JButton("-");
			removeBtn.addActionListener(this);
			constr.insets = new Insets(0, 0, 10, 10);
			this.qPanel.add(removeBtn, constr);
			
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.gridx = 1;
			constr.gridy = i + 1;
			constr.gridwidth = 6;
			constr.insets = new Insets(0, 0, 0, 0);
			qPanel.add(this.answerFields.get(i), constr);
			
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.gridx = 7;
			constr.gridy = i + 1;
			constr.gridwidth = 1;
			this.qPanel.add(this.correctAnsRdo.get(i), constr);
			
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.gridx = 8;
			constr.gridy = i + 1;
			constr.gridwidth = 1;
			constr.insets = new Insets(0, 0, 0, 10);
			this.qPanel.add(new JLabel("Correct Answer"), constr);
		}
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0;
		constr.gridx = 0;
		constr.gridy = this.answerFields.size() + 1;
		constr.gridwidth = 1;
		this.qPanel.add(this.addAnswerBtn, constr);
		
		
	}
	
	public JPanel getMakerPanel() {
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
		this.answerFields.add(new JTextField("Answer"));
		this.correctAnsRdo.add(new JRadioButton());
		this.rdoGroup.add(this.correctAnsRdo.get(this.correctAnsRdo.size() - 1));
	}
	
	//actionPerformed method
	public void actionPerformed(ActionEvent e) {
		JButton eventButton = null;
		if (e.getSource() instanceof JButton) {
			eventButton = (JButton)e.getSource();
		}
		
		if (eventButton.getText().equals("Add Answer")) {
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