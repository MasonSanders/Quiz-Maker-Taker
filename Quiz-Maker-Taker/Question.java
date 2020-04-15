//Question.java
import java.util.*;
import java.awt.*;
public interface Question {
	
	public void setQuestion(String q);
	public String getQuestion();
	public void addAnswer(String ans);
	public ArrayList<String> getAnswerList();
	public void createMakerPanel();
	public Panel getMakerPanel();
	
}