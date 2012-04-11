package survey

import grails.plugin.spock.*

class EnrollmentControllerSpec extends ControllerSpec {

        Person matt= new Person(name:'matt', email:'matt@matt.matt', password:'poop')
        Person bob = new Person(name:'bob', email:'bob@bob.bob', password:'poop')
        Course softwareDesign = new Course(abbreviation:'CSCI 3601', name:'Software Design', term:'Spring',year: 2012, owner: matt)
        Enrollment testEnrollMatt = new Enrollment(person: matt, course: softwareDesign)
        Enrollment testEnrollbob = new Enrollment(person:bob, course: softwareDesign)
        
        def setup(){
            
            mockForConstraintsTests(Person)
            mockForConstraintsTests(Course)
            mockForConstraintsTests(Enrollment)
            mockDomain(Enrollment, [testEnrollMatt, testEnrollbob])
            mockDomain(Person, [matt, bob])
            mockDomain(Course, [softwareDesign])
        }
        
        def 'Test valid enrollment'(){
            expect:
                testEnrollMatt.person == matt
                testEnrollMatt.course == softwareDesign
                
        }
}
