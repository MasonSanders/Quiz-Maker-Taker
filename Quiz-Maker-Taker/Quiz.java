//Quiz.java
import java.awt.*;
import javax.swing.*;
//Quizzes will be linked lists of questions
public interface Quiz {
	public void setName(String name);
	public String getName();
	//public Question getQuestion(int index);
	public void addQuestion(String type);
	public void removeQuestion(int index);
	public void createMakerPanel();
	public Panel getMakerPanel();
}