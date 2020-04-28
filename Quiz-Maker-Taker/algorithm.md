### Window Class: extends JFrame and implements ActionListener  
* create a new Screens currentScreen that gets MAIN_MENU
* create a new String qName
* create a new Container surface
* create an ArrayList of JTextFields textFields
* create an Quiz LinkedList quizzes
#### main method:  
* create a new Window win
#### Constructor:  
* call super and pass a name for the frame
* set the defaultCloseOperation to EXIT_ON_CLOSE
* surface gets getContentPane for self
* call pack for self
* setVisible for self to true
* setLocationRelativeTo for self to null
* call loadQuizzes
* qName gets an empty string
* call mainMenu
#### mainMenu method:  
* currentScreen gets MAIN_MENU
* clear the surface
* set the layout of surface to GridLayout with 2 rows and 1 column
* create a new JPanel top
* set the layout of top to FlowLayout
* add a new JLabel to top that says Quiz-Maker-Taker
* create a new JPanel bottom
* set the layout of bottom to FlowLayout
* create a new JButton makeBtn that has the text Make a Quiz
* add an ActionListener to makeBtn
* add makeBtn to bottom
* create a new JButton takeBtn that has the text Take a Quiz
* add an ActionListener to takeBtn
* add takeBtn to bottom
* add top and bottom to surface
* call pack for self
#### nameQuiz method: gets passed a Quiz newQuiz  
* set currentScreen to NAME_QUIZ
* clear textFields
* clear surface and revalidate
* set the layout of surface to GridLayout with 2 rows and 1 column
* add newQuiz to quizzes
* create a new JPanel top
* set the layout of top to FlowLayout
* add a new JLabel to top prompting to name the quiz
* add a new JTextField to textFields
* add the last index of textFields to top
* create a new JPanel bottom
* set the layout of bottom to FlowLayout
* create a new JButton backBtn that gets the text Back
* add an ActionListener to backBtn
* add backBtn to bottom
* create a new JButton submitBtn that gets the text Submit
* add an ActionListener to submitBtn
* add submitBtn to bottom
* add top and bottom to surface
* call pack for self
#### quizUI method: gets passed a Screens scrn and an int index  
* set the currentScreen to scrn
* clear textFields
* clear surface and revalidate
* set the layout of surface to GridBagLayout
* create a new GridBagConstraints constr
* create a new JButton backBtn that gets the text Back
* add an actionListener to backBtn
* define constraints for constr
* add backButton with constr to surface
* create a JLabel title that gets the name of spot index of quizzes for text
* define constraints for constr
* add title with constr to surface
* define constraints for constr
* add getPanel for spot index of quizzes with constr to surface
* create a new JButton finishBtn that gets the text Save and Finish
* add an ActionListener to finishBtn
* define constraints for constr
* add finishBtn with constr to surface
#### takerSelection method:  
* set currentScreen to TAKER_SELECTION
* clear textFields
* clear surface and revalidate
* set the layout of surface to GridBagLayout
* create a new GridBagConstraints constr
* create a new JButton backBtn that gets the text Back
* add an ActionListener to backBtn
* define constriants for constr
* add backBtn with constr to surface
* create a new JLabel selectLbl that prompts the user to select a quiz
* define constraints for constr
* add selectLbl with constr to surface
* create a new JPanel panelPnl
* set the layout of panePnl to FlowLayout
* set the preferred size of panePnl to 800x600
* for an integer i that goes from 0 to the size of quizzes,
  * create a new JButton quizBtn that gets the text of getName for quizzes at spot index
  * add an ActionListener to quizBtn
  * add quizBtn to panePnl
* create a new JScrollPane pane that gets panePnl as a viewPort
* set pane to have no border
* define constraints for constr
* add pane with constr to surface
* call pack for self
#### results method: gets passed a string rslt
* set currentScreen to RESULTS
* clear the surface and revalidate
* set the layout of surface to GridBagLayout
* create a new GridBagConstraints constr
* define constraints for constr
* create a new JLabel rsltLbl that tells the user their result using rslt
* add rsltLbl with constr to surface
* define constraints for constr
* create a new JLabel enterNameLbl that has text telling the user to enter their name
* add enterNameLbl with constr to surface
* define constraints for constr
* add a new JTextField to textFields
* add the last index of textFields with constr to surface
* define constraints for constr
* create a new JButton finishBtn that gets the text Save and Finish
* add an actionListener to finishBtn
* add finishBtn with constr to surface
* call pack for self
#### saveQuizzes method:  
* try,
  * create a new FileOutputStream outFile that opens Quizzes.dat
  * create a new ObjectOutputStream outObj that gets outFile
  * output quizzes to outObj
* catch an Exception e,
  * output e's message
#### loadQuizzes method:  
* try, 
  * create a new FileInputStream inFile that opens Quizzes.dat
  * create a new ObjectInputStream inObj that gets inFile
  * quizzes gets readObject of inObj cast to a Quiz ArrayList
* catch an Exception e, 
  * output e's message
#### saveScore method: gets passed a string name and a String score
* try, 
  * create a new FileWriter outFile that opens Scores.txt with append mode set to true
  * create a new PrintWriter output that gets outFile
  * output name, qName, and score to output
  * close outFile
  * close output
* catch an Exception e,
  * output e's message
#### actionPerformed: gets passed an ActionEvent e  
* create a new JButton eventButton that gets null
* if getSource of e is a JButton,
  * eventButton gets getSource of e cast to a JButton
* if getText for eventButton is Back,
  * if currentScreen is NAME_QUIZ,
    * remove the last index of quizzes
    * call mainMenu
  * else if currentScreen is MAKER,
    * remove the last index of quizzes
    * call mainMenu
  * else if currentScreen is TAKER_SELECTION,
    * call mainMenu
  * else if currentScreen is TAKER,
    * call takerSelection
  * else,
    * output no current screen
* if getText for eventButton is Submit,
  * if getText for the first index of textField is not an empty string
    * setName for the last index of quizzes and pass getText for the first index of textFields
    * call quizUI and pass MAKER and the last index of quizzes
  * else,
    * show a dialog saying that the quiz must have a name
* if getText for eventButton is Make a Quiz,
  * call nameQuiz and pass a new ScoreBasedQuiz
* if getText for eventButton is Take a Quiz,
  * call takerSelection
* if getText for eventButton is Save and Finish
  * if currentScreen is MAKER,
    * if the last index of quizzes isValid,  
      * call updateQuestions for the last index of quizzes
      * call createTakerPanel for the last index of quizzes
      * call saveQuizzes
      * call mainMenu
    * else,
      * show a dialog saying to make sure every question and answer are valid
  * else if currentScreen is TAKER,
    * for an integer i that goes from 0 to the size of quizzes,
      * if getName for index i of quizzes is qName,
        * call results and pass getResult for index i of quizzes
        * break
  * else,
    * if getText for the last index of textFields is not an empty string,
      * for an integer i that goes from - to the size of quizzes,
        * if getName for index i of quizzes is qName
          * define a string name that gets getText for the last index of textFields
          * define a string rslt that gets getResult for index i of quizzes
          * call saveScore and pass name and rslt
          * break
      * call mainMenu
    * else,
      * show a dialog saying that the user must enter their name
* for an integer i that goes from 0 to the size of quizzes
  * if getText for eventButton is getName for index i of quizzes,
    * qName gets getName for index i of quizzes
    * call createTakerPanel for index i of quizzes
    * call quizUI and pass TAKER and i

### Screens enum:  
* define constants MAIN_MENU, NAME_QUIZ, TAKER_SELECTION, MAKER, TAKER, and RESULTS

### Quiz Interface:  
* define the setName method that gets passed a String name
* define the getName method that returns a String
* define the addQuestion method that gets passed a String type
* define the removeQuestion method that gets passed an int index
* define the createMakerPanel method
* define the createTakerPanel method
* define the getPanel method that returns a JPanel
* define the updateQuestions method
* define the isValid method that returns a boolean
* define the getResult method that returns a String

### Question Interface:  
* define the setQuestion method that gets passed a String q
* define the getQuestion method that returns a String
* define the addAnswer method that gets passed a String ans
* define the getAnswerList method that returns a String ArrayList
* define the getCorrectAnswer method that returns a String ArrayList
* define the getSelectedAnswer method that returns a String ArrayList
* define the createMakerPanel method
* define the getPanel method that returns a JPanel
* define the createTakerPanel method
* define the addNewAnswerFields method
* define the removeAnswerFields method that gets passed an int index
* define the isValid method that returns a boolean
* define the update method

### ScoreBasedQuiz Class: implements Quiz, ActionListener, and Serializable
* define a new Question LinkedList questions
* define a String name
* define a JPanel quizPanel
* define a new JButton addSnglAnsBtn that gets the text Add Single Answer Question
* define a new JButton addMltiAnsBtn that gets the text Add Multi Answer Question
* define a new JButton ArrayList removeBtns
#### Constructor:  
* name gets an empty String
* add an ActionListener to addMltiAnsBtn
* add an ActionListener to addSnglAnsBtn
* call createMakerPanel
#### setName method: gets passed a String name
* Self's name gets name
#### getName method: returns a String  
* return name
#### addQuestion method: gets passed a String type
* if type is Single Answer,
  * add a new SingleAnswerQ to questions
  * add a new JButton to removeBtns that gets the text -
  * add an actionListener to the last index of removeBtns
* else,  
  * add a new MultiAnswerQ to questions
  * add a new JButton to removeBtns that gets the text -
  * add an actionListener to the last index of removeBtns
#### removeQuestion method: gets passed an int index  
* remove spot index from questions
* remove spot index from removeBtns
#### createMakerPanel method:  
* clear quizPanel and revalidate
* set the preferred size of quizPanel to 800x600
* set the layout of quizPanel to GridBagLayout
* define a new GridBagConstraints constr
* create a new JPanel contentPnl
* set the layout of contentPnl to GridBagLayout
* for an integer i that goes from 0 to the size of questions,
  * define a new JLabel qNumLbl that gets the text i + 1 cast to a string
  * define constraints for constr
  * add qNumLbl with constr to contentPnl
  * define constraints for constr
  * add index i of removeBtns with constr to contentPnl
  * define a new JPanel question panel that gets getPanel for questions at index i
  * define constraints for constr
  * add questionpnl with constr to contentPnl
* define constraints for constr
* add addSnglAnsBtn with constr to contentPnl
* define constraints for constr
* add addMltiAnsBtn with constr to contentPnl
* create a new JScrollPane pane that gets contentPnl as its viewPort
* set the preferredSize for pane to quizPanel's preferred size
* define constraints for constr
* add pane with constr to quizPanel
#### createTakerPanel method:  
* for an integer i that goes from 0 to the size of questions
  * createTakerPanel for the question at index i of questions
* clear quizPanel and revalidate
* set the preferredSize for quizPanel to 800x600
* set the layout for quizPanel to GridBagLayout
* define a new GridBagConstraints constr
* define a new JPanel contentPnl
* set the layout for contentPnl to GridBagLayout
* for an integer i that goes from 0 to the size of questions,
  * define a new JLabel qNumLabel that gets the text i+1 cast to a string
  * define constraints for constr
  * add qNumLbl with constr to contentPnl
  * define a new JPanel questionPnl that gets getPanel for questions at index i
  * define constraints for constr
  * add questionPnl with constr to contentPnl
* create a new JScrollPane pane that gets contentPnl for its viewPort
* set the preferred size of pane to the preferred size of quizPanel
* set an empty border for pane
* define constraints for constr
* add pane with constr to quizPanel
#### getPanel method: returns a JPanel  
* return quizPanel
#### updateQuestions method:  
* for an integer i that goes from 0 to the size of questions,
  * call update for index i of questions
#### isValid method: returns a boolean
* define a boolean valid thet gets true
* if the size of questions is greater than 0,
  * for an integer i that goes from 0 to the size of questions,
    * call update for index i of questions
    * if isValid for index i of questions is false,
      * valid gets false
* else, 
  * valid gets false
* return valid
#### getResult method: returns a String
* define an int correctCtr that gets 0
* define a String score that gets an empty String
* define a boolean allMatch that gets true
* define a String ArrayList selectedAnswers
* define a String ArrayList correctAnswers
* for an integer i that goes from 0 to the size of questions, 
  * allMatch gets false
  * selectedAnswers gets getSelectedAnswer for index i of questions
  * correctAnswers gets getCorrectAnswer for index i of questions
  * for an integer j that goes from 0 to the size of correctAnswers
    * if correctAnswers at index j is in selectedAnswers,
      * allMatch gets true
    * else,
      * allMatch gets false
  * if allMatch is true,
    * add 1 to correctCtr
* score gets correctCtr cast to a string / the size of questions
* return score
#### actionPerformed method: gets passed an ActionEvent e,
* create a new JButton eventButton that gets null
* if getSource for e is a JButton,
  * eventButton gets getSource for e cast to a JButton
* if getText for eventButton is Add Multi Answer Question,
  * call addQuestion and pass Multi Answer
  * call createMakerPanel
* if getText for eventButton is Add Single Answer Question,
  * call addQuestion and pass Single Answer
  * call createMakerPanel
* for an integer i that goes from 0 to the size of removeBtns,
  * if eventButton is index i of removeBtns
    * call removeQuestion and pass i
    * call createMakerPanel
    * revalidate quizPanel

### SingleAnswerQ Class: implements Question, ActionListener, and Serializable
* define a String qstn
* define a String ArrayList correctAns
* define a String ArrayList answers
* define a JPanel qPanel
* define a JButton addAnswerBtn that gets the text Add Answer
* define a new JTextField that gets the text Question
* define a JRadioButton ArrayList correctAnsRdo
* define a JTextField ArrayList answerFields
* define a JLabel ArrayList answerLbls
* define a JButton ArrayList removeBtns
* define a ButtonGroupd rdoGroup
#### Constructor:  
* qstn gets an empty String
* add an actionListener to addAnswerBtn
* call createMakerPanel
#### setQuestion method: gets passed a String q  
* qstn gets q
#### getQuestion method: returns a String
* return qstn
#### addAnswer method: gets passed a String a
* add a to answers
#### getAnswerList method: returns a String ArrayList
* return answers
#### addCorrectAnswer method: gets passed a String ans
* add ans to correctAns
#### getCorrectAnswer method: returns a String ArrayList  
* return correctAns
#### getSelectedAnswer method: returns a String ArrayList
* define a String ArrayList selectedList
* for an integer i that goes from 0 to the size of answers,
  * if index i of correctAnsRdo isSelected,
    * add index i of answers to selectedList
* return selectedList
#### createMakerPanel method:  
* clear qPanel
* set the layout of qPanel to GridBagLayout
* define a new GridBagConstriants constr
* define constraints for constr
* add qField with constr to qPanel
* for an integer i that goes from 0 to the size of answerFields,
  * define constraints for constr
  * add index i of removeBtns with constr to qPanel
  * define constraints for constr
  * add index i of answerFields with constr to qPanel
  * define constraints for constr
  * add index i of correctAnsRdo with constr to qPanel
  * define constraints for constr
  * add a new JLabel with the text Correct Answer with constr to qPanel
* define constraints for constr
* add addAnswerBtn with constr to qPanel
#### createTakerPanel method:  
* clear qPanel
* clear answerLbls
* clearSelection for rdoGroup
* set the layout of qPanel to GridBagLayout
* define a new GridBagConstraints constr
* define constraints for constr
* add a new JLabel with getQuestion with constr to qPanel
* for an integer i that goes from 0 to the size of answers,
  * define constraints for constr
  * add index i of correctAnsRdo with constr to qPanel
  * define constraints for constr
  * add index i of answerLbls with constr to qPanel
#### getPanel method: returns a JPanel  
* return qPanel
#### isValid method: returns a boolean  
* create a boolean valid that gets true
* if qstn is an empty string,
  * valid is false
* if qstn is Question,
  * valid is false
* if the size of correctAns is 0,
  * valid is false
* if the size of answers is 0, 
  * valid is false
* for an integer i that goes from - to the size of answers,
  * if answers at index i is Answer,
    * valid is false
    * break
  * if answers at index i is an empty string,
    * valid is false
    * break
* return valid
#### update method:  
* clear answers
* clear correctAns
* for an integer i that goes from 0 to the size of answerFields
  * call addAnswer and pass getText for index i of answerFields
  * if index i of correctAnsRdo isSelected,
    * call addCorrectAnswer and pass getText for index i of answerFields
* qstn gets getText for qField
#### addNewAnswerFields method:  
* add a new JTextField with the text Answer to answerFields
* add a new JRadioButton to correctAnsRdo
* add the last index of correctAnsRdo to rdoGroup
* a new JButton with the text - to removeBtns
* add an actionListener to the last index of removeBtns
#### removeAnswerFields method: gets passed an int index  
* remove index from answerFields
* remove correctAnsRdo at index from rdoGroup
* remove index from correctAnsRdo
* remove index from removeBtns
#### actionPerformed method: gets passed an ActionEvent e  
* create a new JButton eventButton that gets null
* if getSource for e is a JButton,
  * eventButton gets getSource for e cast to a JButton
* if getText for evetnButton is Add Answer,
  * call update
  * call addNewAnswerFields
  * call createMakerPanel
  * revalidate qPanel
* for an integer i that goes from 0 to the size of removeBtns,
  * if eventButton is removeBtns at index i,
    * call removeAnswerFields and pass i
    * call update
    * call createMakerPanel
    * revalidate qPanel

#### MultiAnswerQ Class: implements Question, ActionListener, and Serializable  
MultiAnswerQ is almost the exact same as SingleAnswerQ.
It just replaces every instance of a JRadioButton with a JCheckBox.  
The reason I didn't merge the classes into a single generic class is because of line 
463 of this algorithm "call addNewAnswerFields." it's called within the given question class 
and I can't declare a "new T" within the class, it has to be passed from a static method, or from 
an outside class, which i didn't have the ability to do since the button to add an answer and its ActionListener 
need to be components within qPanel which is encapsulated by the object.   