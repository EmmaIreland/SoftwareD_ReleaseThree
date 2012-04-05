package survey

import grails.plugin.spock.*

class QuestionSpec extends UnitSpec {
    Person matt = new Person(name: 'Matt', email: 'cats@gmail.com')
    Course course = new Course(abbreviation:'CSCI 1001', name:'Intro to the Computing World', term:'Spring',year: 2012, owner: matt)
    Project releaseOne = new Project(name: 'Release One', description: 'Students do cool things', course: course, dueDate: new Date().next())
    Question question1 = new Question(prompt:'A/S/L?')
    Question blankPrompt = new Question(prompt: '')
    Answer answer1 = new Answer(question: question1, person: matt)
    Survey survey1 = new Survey(title:'A Survey', questions: [question1])
    def setup(){
        mockDomain(Question, [question1, blankPrompt])
        mockDomain(Person, [matt])
        mockDomain(Answer, [answer1])
        mockDomain(Survey, [survey1])
        mockDomain(Course, [course])
        mockDomain(Project, [releaseOne])
        mockForConstraintsTests(Question)
    }
    def 'Test Validator'(){
        expect:
            blankPrompt.validate() == false
            question1.validate() == true
    }
    def 'Test Get Answer'(){
        expect:
            question1.getAnswer(matt) == answer1
    }
    def 'Test toString'(){
        expect:
            question1.toString() == 'A/S/L?'
    }
    def 'Test copyList'(){
        def testList1 = question1.list()
        def testList2 = question1.list()
        expect:
            question1.copyList(testList1) == testList1
            blankPrompt.copyList(testList2) == testList2
        
    }
    def 'Test getSurvey'(){
        expect:
            question1.getSurvey() == survey1
    }
}
