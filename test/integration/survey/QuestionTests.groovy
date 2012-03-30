package survey

import grails.test.*
import survey.questions.*

class QuestionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testQuestionNotNull() {
	Question checkQuestionWrapOne = new Question(question: null)
	assertFalse checkQuestionWrapOne.validate()

    }
    
    
    void testQuestionNotInstanceOfQuestionInterface() {
	CheckboxQuestion checkQuestionOne = new CheckboxQuestion(prompt: 'Why do you suck?', choices: ['I just do'])
	assertTrue checkQuestionOne.validate()
	
	Question checkQuestionWrapOne = new Question(question: "bob")
	assertFalse checkQuestionWrapOne.validate()

    }
}
