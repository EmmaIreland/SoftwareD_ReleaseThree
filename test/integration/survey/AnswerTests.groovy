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
	Person nic = new Person(name: 'Nic McPhee', email: 'mcphee@umn.edu').save(failOnError: true)
        CheckboxQuestion question1 = new CheckboxQuestion(prompt: "What is your name?", choices: ["stuff"]).save(failOnError: true)
	Answer answer1 = new Answer(response: "Nic McPhee", person: nic, question: question1).save(failOnError: true)
	assertEquals question1.getAnswer(nic), answer1
    }
}