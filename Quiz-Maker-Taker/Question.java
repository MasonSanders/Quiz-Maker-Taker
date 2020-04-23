//Question.java
import java.util.*;
import java.awt.*;
import javax.swing.*;
public interface Question {
	public void setQuestion(String q);
	public String getQuestion();
	public void addAnswer(String ans);
	public ArrayList<String> getAnswerList();
	public ArrayList<String> getCorrectAnswer();
	public ArrayList<String> getSelectedAnswer();
	public void createMakerPanel();
	public JPanel getPanel();
	public void createTakerPanel();
	public void addNewAnswerFields();
	public void removeAnswerFields(int index);
	public boolean isValid();
	public void update();
}