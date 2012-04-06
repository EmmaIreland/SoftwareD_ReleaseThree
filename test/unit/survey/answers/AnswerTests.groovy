package survey.answers

import grails.test.*
import survey.Answer;
import survey.Person;
import survey.questions.*

class AnswerTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetAnswer() {
	Person nic = new Person(name: 'Nic McPhee', email: 'mcphee@umn.edu')
        mockDomain(Person, [nic])
        CheckboxQuestion question = new CheckboxQuestion(prompt: "What is your name?", choices: ["stuff"])
	mockDomain(CheckboxQuestion, [question])
        Answer answer = new Answer(person: nic, question: question)
	mockDomain(Answer, [answer])
        assertEquals question.getAnswer(nic), answer
    }
}