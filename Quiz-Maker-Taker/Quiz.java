//Quiz.java
import java.awt.*;
import javax.swing.*;

public interface Quiz {
	public void setName(String name);
	public String getName();
	public void addQuestion(String type);
	public void removeQuestion(int index);
	public void createMakerPanel();
	public void createTakerPanel();
	public JPanel getPanel();
	public void updateQuestions();
	public boolean isValid();
	public String getResult();
}