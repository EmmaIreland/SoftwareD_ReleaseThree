package survey

import grails.test.*
import survey.questions.*

class SurveyTests extends GrailsUnitTestCase {
    def question = new CheckboxQuestion(prompt: "how old?", choices: ["legal", "illegal"]);
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDueDateInFuture() {
	Date today = new Date()
	Survey testSurvey = new Survey(title: "title", dueDate: today.next(), questions: [question], project: Project.get(1)).save(failOnError: true)
	assertTrue testSurvey.validate()
	
	Survey testSurvey2 = new Survey(title: "title", dueDate: today, questions: [question], project: Project.get(1)).save(validate: false)
	assertFalse testSurvey2.validate()
    }
}
