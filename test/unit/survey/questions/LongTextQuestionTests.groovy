package survey.questions

import grails.test.*

class LongTextQuestionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
	mockForConstraintsTests(LongTextQuestion)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testClassName() {
	LongTextQuestion aQuestion = new LongTextQuestion(prompt: 'What did you eat today?')
	assertEquals aQuestion.getTemplateName(), 'Long'

    }
}
