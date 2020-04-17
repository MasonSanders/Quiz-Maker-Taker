//Question.java
import java.util.*;
import java.awt.*;
import javax.swing.*;
public interface Question {
	
	public void setQuestion(String q);
	public String getQuestion();
	public void addAnswer(String ans);
	public ArrayList<String> getAnswerList();
	public void createMakerPanel();
	public JPanel getMakerPanel();
	
}