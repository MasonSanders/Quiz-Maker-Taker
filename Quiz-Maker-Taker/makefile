Question.class: Question.java
	javac Question.java

SingleAnswerQ.class: SingleAnswerQ.java Question.class
	javac SingleAnswerQ.java

MultiAnswerQ.class: MultiAnswerQ.java Question.class
	javac MultiAnswerQ.java

Quiz.class: Quiz.java SingleAnswerQ.class MultiAnswerQ.class
	javac Quiz.java

ScoreBasedQuiz.class: ScoreBasedQuiz.java Quiz.class
	javac ScoreBasedQuiz.java

Screens.class: Screens.java
	javac Screens.java

Window.class: Window.java Screens.class ScoreBasedQuiz.class
	javac Window.java

clean:
	rm *.class
	rm *.dat
	rm *.txt

run: Window.class
	java Window