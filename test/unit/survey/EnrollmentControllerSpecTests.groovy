package survey

import grails.plugin.spock.*

class EnrollmentControllerSpec extends UnitSpec {

        Person matt= new Person(name:'matt', email:'matt@matt.matt')
        Course softwareDesign = new Course(abbreviation:'CSCI 3601', name:'Software Design', term:'Spring',year: 2012, owner: matt)
        Enrollment testEnroll = new Enrollment(person: matt, course: softwareDesign)
        def setup(){
            mockForConstraintsTests(Person)
            mockForConstraintsTests(Course)
            mockForConstraintsTests(Enrollment)
        }
        
        def 'Test valid enrollment'(){
            expect:
                testEnroll.person == matt
                testEnroll.course == softwareDesign
                
        }
        
    
}
