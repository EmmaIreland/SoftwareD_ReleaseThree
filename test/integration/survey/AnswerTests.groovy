package survey

import grails.test.*
import survey.questions.*

class AnswerTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetAnswer() {
	CheckboxQuestion question1 = new CheckboxQuestion(prompt: "What is your name?", choices: ["stuff"]).save(failOnError: true)
	Answer answer1 = new Answer(response: "Nic McPhee", person: Person.get(1), question: question1).save(failOnError: true)
	assertEquals question1.getAnswer(Person.get(1)), answer1
    }
}