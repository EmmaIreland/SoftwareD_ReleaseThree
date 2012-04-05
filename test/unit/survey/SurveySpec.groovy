package survey

import grails.plugin.spock.*
import survey.questions.*

class SurveySpec extends UnitSpec {
    
    def question = new CheckboxQuestion(prompt: "how old?", choices: ["legal", "illegal"])
    Date today = new Date()
    Course course = new Course()
    Project releaseOne = new Project(name: 'Release One', description: 'Students do cool things', course: course, dueDate: new Date().next())
    Survey testSurvey = new Survey(title: "title", dueDate: today.next(), questions: [question], project: releaseOne)
    Survey testSurvey2 = new Survey(title: "title", dueDate: today, questions: [question], project: releaseOne)
    Survey testSurvey3 = new Survey(title: "title", dueDate: today.previous(), questions: [question], project: releaseOne)

    def setup(){
        mockDomain(Survey, [testSurvey, testSurvey2, testSurvey3])
        mockDomain(Question, [question])
        mockForConstraintsTests(Survey)
    }
    
    def 'Test Date Constraint'() {
        expect: testSurvey.validate() == true
                testSurvey3.validate() == false
    }
    def 'Test toString'() {
        expect: testSurvey.toString() == testSurvey.title
                testSurvey.toString() != testSurvey.id
    }
}
