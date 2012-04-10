package survey

import grails.plugin.spock.*

class EnrollmentControllerSpec extends ControllerSpec {

        Person matt= new Person(name:'matt', email:'matt@matt.matt', password:'poop')
        Person bob = new Person(name:'bob', email:'bob@bob.bob', password:'poop')
        Course softwareDesign = new Course(abbreviation:'CSCI 3601', name:'Software Design', term:'Spring',year: 2012, owner: matt)
        Enrollment testEnroll = new Enrollment(person: matt, course: softwareDesign)
        
        def setup(){
            
            mockForConstraintsTests(Person)
            mockForConstraintsTests(Course)
            mockForConstraintsTests(Enrollment)
            mockDomain(Enrollment, [testEnroll])
            mockDomain(Person, [matt, bob])
            mockDomain(Course, [softwareDesign])
        }
        
        def 'Test valid enrollment'(){
            expect:
                testEnroll.person == matt
                testEnroll.course == softwareDesign
                
        }
        
        def 'Test Edit'() {

                
        }
        
    
}
