package survey.questions

import grails.test.*

class CheckboxQuestionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
	mockForConstraintsTests(CheckboxQuestion)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testChoicesNotEmpty() {
	CheckboxQuestion selectAllThatApply = new CheckboxQuestion(prompt: 'select all that apply', choices: ['this', 'that'])
	assertTrue selectAllThatApply.validate()
    }
    
    void testClassName() {
	CheckboxQuestion aQuestion = new CheckboxQuestion(prompt: 'What did you eat today?', choices: ['pizza'])
	assertEquals aQuestion.getTemplateName(), 'Checkbox'
    }
}
