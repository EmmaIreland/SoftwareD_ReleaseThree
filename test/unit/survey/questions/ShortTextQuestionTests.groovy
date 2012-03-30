package survey.questions

import grails.test.*

class ShortTextQuestionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
	mockForConstraintsTests(ShortTextQuestion)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testClassName() {
	ShortTextQuestion aQuestion = new ShortTextQuestion(prompt: 'What did you eat today?')
	assertEquals aQuestion.getTemplateName(), 'Short'

    }
}
