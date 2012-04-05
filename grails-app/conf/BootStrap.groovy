import survey.*
import survey.questions.*
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
        
        if (Person.count() == 0) { //GrailsUtil.environment != 'production') {
            Person nic = new Person(name: 'Nic McPhee', email: 'mcphee@morris.umn.edu').save(failOnError: true)
            Person kkLamberty = new Person(name: 'KK Lamberty', email: 'lamberty@morris.umn.edu').save(failOnError: true)
            
            Course softwareDesign = new Course(abbreviation:'CSCI 3601', name:'Software Design', term:'Spring',year: 2012, owner: nic).save(failOnError: true)
            Course introToComputing = new Course(abbreviation:'CSCI 1001', name:'Intro to the Computing World', term:'Spring',year: 2012, owner: kkLamberty).save(failOnError:true)
            
            def buildPerson = { person -> new Person(name: person[0] + ' ' + person[1], email: person[2]) }
            def enrollPerson = { person, course -> new Enrollment(person: person, course: course).save(failOnError: true) }
            
            def softwareDesignPeople = []
            [
                ['Phil', 'McGraw', 'phil@gmail.com'],
                ['Oprah', 'Winfrey', 'oprah@oprah.com'],
                ['Rachael', 'Ray', 'rr@cooking.com'],
                ['Whoopi', 'Goldberg', 'whoopi@view.com'],
                ['Regis', 'Philbin', 'i_hate_kathy@live.tv'],
                ['Chris', 'Aga', 'chris@aga.me'],
                ['Joseph', 'Einertson', 'joe@einertson.me'],
                ['Christopher', 'Thomas', 'chris@thomas.me'],
                ['Michael', 'Rislow', 'michael@rislow.me'],
                ['Kevin', 'Viratyosin', 'kevin@viratyosin.me'],
                ['Matt', 'Cotter', 'matt@cotter.me'],
                ['Steve', 'Jungst', 'steve@jungst.me'],
                ['Steve', 'Jobs', 'steve@apple.com'],
                ['Edgar', 'Poe', 'eap@poe.org'],
                ['Annabel', 'Lee', 'annabel@poe.org'],
                ['Phou', 'Lee', 'phou@lee.me'],
                ['Scott', 'Steffes', 'scott@steffes.me']
            ].each { person -> softwareDesignPeople.add(buildPerson(person)) }
            softwareDesignPeople.each { it.save(failOnError: true) }
            softwareDesignPeople.each { enrollPerson it, softwareDesign }
            
            def introToComputingPeople = []
            [
                ['James', 'Aronson', 'james@aronson.me'],
                ['Darcey', 'Aronson', 'darcey@aronson.me'],
                ['Bill', 'Gates', 'bill@microsoft.com'],
                ['George', 'Bush', 'george@bush.com'],
                ['Joevin', 'Einertyosin', 'joevin@einertyosin.org'],
                ['Reed', 'Wallace', 'reed@wallace.me']
            ].each { person -> introToComputingPeople.add(buildPerson(person)) }
            introToComputingPeople.each { it.save(failOnError: true) }
            introToComputingPeople.each { enrollPerson it, introToComputing }
            
            Project releaseOne = new Project(name: 'Release One', description: 'Students do cool things', course: softwareDesign, dueDate: new Date().next()).save(failOnError: true)
            Team teamAwesome = new Team(name: 'Team Awesome', project: releaseOne).save(failOnError: true)
            Team teamHumble = new Team(name: 'Team Humble', project: releaseOne).save(failOnError: true)
            
            for (i in 0..3) {
                new Membership(team: teamAwesome, member: softwareDesignPeople[i]).save(failOnError: true)
            }
            for (i in 6..10) {
                new Membership(team: teamHumble, member: softwareDesignPeople[i]).save(failOnError: true)
            } // TODO: make these for loops w/o duplication
                        
            Survey surveyReleaseOne = new Survey(title: 'Survey for Release One', dueDate: new Date().next(), questions: [], project: releaseOne).save(failOnError: true)
            
            def scootsSurveyQuestions = [
                new LongTextQuestion(prompt: 'So, tell me \'bout yerself.'),
                new CheckboxQuestion(prompt: 'Are you a boy or a girl?', choices: ['Boy', 'Girl', 'Other']),
                new LongTextQuestion(prompt: 'And how d\'you feel \'bout that?'),
                new ShortTextQuestion(prompt: 'If you were an animal, what animal would you be?'),
                new MultipleChoiceQuestion(prompt: 'What\'s your favorite range in the visible light spectrum? (Choices are given by wavelength in nm.)',
                    choices: ['380-450 (violet)', '450-475 (blue)', '476-495 (cyan)', '495-570 (green)', '570-590 (yellow)', '590-620 (orange)', '620-750 (red)', '760+ (infrared)']),
                new ShortTextQuestion(prompt: 'What is your favorite vowel-less word?'),
                new LongTextQuestion(prompt: 'Tell me a story.'),
                new CheckboxQuestion(prompt: 'Do you love me?', choices: ['Yes', 'More than yes (wink, wink)'])
            ]
            Survey scootsSurvey = new Survey(title: 'Scoot\'s Finest Survey', dueDate: new Date().next(), questions: scootsSurveyQuestions, project: releaseOne).save(failOnError: true)
        }
    }
    
    def destroy = {
    }
}
