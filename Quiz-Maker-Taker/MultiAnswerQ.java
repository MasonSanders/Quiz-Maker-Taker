//MultiAnswerQ.java
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class MultiAnswerQ implements Question, ActionListener, Serializable {
	
	String qstn;
	ArrayList<String> correctAns = new ArrayList<String>();
	ArrayList<String> answers = new ArrayList<String>();
	
	//Jcomponents
	JPanel qPanel = new JPanel();
	JButton addAnswerBtn = new JButton("Add Answer");
	JTextField qField = new JTextField("Question");
	
	ArrayList<JCheckBox> correctAnsChk = new ArrayList<JCheckBox>();
	
	ArrayList<JTextField> answerFields = new ArrayList<JTextField>();
	ArrayList<JLabel> answerLbls = new ArrayList<JLabel>();
	ArrayList<JButton> removeBtns = new ArrayList<JButton>();
	
	
	//constructor
	public MultiAnswerQ() {
		this.qstn = "";
		this.addAnswerBtn.addActionListener(this);
		this.createMakerPanel();
	}//end constructor
	
	//setQuestion method
	public void setQuestion(String q) {
		this.qstn = q;
	}//end setQuestion
	
	//getQuestion method
	public String getQuestion() {
		return this.qstn;
	}//end getQuestion
	
	//addAnswer method
	public void addAnswer(String a) {
		this.answers.add(a);
	}//end addAnswer
	
	//getAnswerList method
	public ArrayList<String> getAnswerList() {
		return this.answers;
	}//end getAnswerList
	
	//setCorrectAnswer method
	public void addCorrectAnswer(String ans) {
		this.correctAns.add(ans);
	}//end setCorrectAnswer
	
	//getCorrectAnswer method
	public ArrayList<String> getCorrectAnswer() {
		return this.correctAns;
	}//end getCorrectAnswer
	
	//getSelectedAnswer method
	public ArrayList<String> getSelectedAnswer() {
		ArrayList<String> selectedList = new ArrayList<String>();
		for(int i = 0; i < this.answers.size(); i++) {
			if (correctAnsChk.get(i).isSelected()) {
				selectedList.add(this.answers.get(i));
			}
		}
		return selectedList;
	}//end getSelectedAnswer
	
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
			constr.insets = new Insets(0, 0, 10, 10);
			this.qPanel.add(this.removeBtns.get(i), constr);
			
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
			this.qPanel.add(this.correctAnsChk.get(i), constr);
			
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
		
	}//end createMakerPanel
	
	//createTakerPanel method
	public void createTakerPanel() {
		this.qPanel.removeAll();
		this.answerLbls.removeAll(this.answerLbls);
		
		for (int i = 0; i < this.correctAnsChk.size(); i++) {
			this.correctAnsChk.get(i).setSelected(false);
		}
		this.qPanel.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		
		//question label
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 1.0;
		constr.gridx = 0;
		constr.gridy = 0;
		constr.gridwidth = 7;
		constr.anchor = GridBagConstraints.FIRST_LINE_START;
		this.qPanel.add(new JLabel(this.getQuestion()), constr);
		
		for (int i = 0; i < this.answers.size(); i++) {
			//radio button
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 0.0;
			constr.gridx = 0;
			constr.gridy = i + 1;
			constr.gridwidth = 1;
			this.qPanel.add(this.correctAnsChk.get(i), constr);
			
			//answer label
			constr.fill = GridBagConstraints.HORIZONTAL;
			constr.weightx = 1.0;
			constr.gridx = 1;
			constr.gridy = i+1;
			this.answerLbls.add(new JLabel(this.answers.get(i)));
			this.qPanel.add(this.answerLbls.get(i), constr);
		}
	}//end createTakerPanel
	
	//getPanel method
	public JPanel getPanel() {
		return this.qPanel;
	}//end getMakerPanel
	
	public boolean isValid() {
		boolean valid = true;
		if (this.qstn.equals("")) {
			valid = false;
		}
		if (this.correctAns.size() == 0) {
			valid = false;
		}
		if (this.answers.size() == 0) {
			valid = false;
		}
		return valid;
	}
	
	//update method
	//stores all of the strings to non-component variables
	public void update() {
		this.answers.removeAll(this.answers);
		this.correctAns.removeAll(this.correctAns);
		for (int i = 0; i < this.answerFields.size(); i++) {
			this.addAnswer(this.answerFields.get(i).getText());
			if (this.correctAnsChk.get(i).isSelected()) {
				this.addCorrectAnswer(this.answerFields.get(i).getText());
			}
		}
		this.qstn = this.qField.getText();
	}//end update
	
	//addNewAnswerFields method
	public void addNewAnswerFields() {
		this.answerFields.add(new JTextField("Answer"));
		this.correctAnsChk.add(new JCheckBox());
		this.removeBtns.add(new JButton("-"));
		this.removeBtns.get(this.removeBtns.size() - 1).addActionListener(this);
	}//end addNewAnswerFields
	
	//removeAnswer method
	public void removeAnswerFields(int index) {
		this.answerFields.remove(index);
		this.correctAnsChk.remove(index);
		this.removeBtns.remove(index);
	}//end removeAnswer
	
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
		
		//special case for the remove buttons
		for (int i = 0; i < this.removeBtns.size(); i++) {
			if (eventButton == this.removeBtns.get(i)) {
				this.removeAnswerFields(i);
				this.update();
				this.createMakerPanel();
				this.qPanel.revalidate();
			}
		} 
	}//end actionPerformed
}