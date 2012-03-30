package survey.questions

import grails.test.*

class MultipleChoiceQuestionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
	mockForConstraintsTests(MultipleChoiceQuestion)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testChoicesNotEmpty() {
    	MultipleChoiceQuestion whatTimeIsIt = new MultipleChoiceQuestion(prompt: 'What time is it', choices: ['party time'])
	assertTrue whatTimeIsIt.validate()
    }
    
    void testClassName() {
	MultipleChoiceQuestion aQuestion = new MultipleChoiceQuestion(prompt: 'What did you eat today?', choices: ['pizza'])
	assertEquals aQuestion.getTemplateName(), 'MultipleChoice'
    }
}
