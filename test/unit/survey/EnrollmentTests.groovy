package survey

import grails.test.*

class EnrollmentTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
	mockForConstraintsTests(Enrollment)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testToString(){
	Person kkLamberty = new Person(name: 'KK Lamberty', email: 'lamberty@morris.umn.edu')
	Course introToComputing = new Course(abbreviation:'CSCI 1001', name:'Intro to the Computing World', term:'Spring',year: 2012, owner: kkLamberty)
	Enrollment kkInIntro = new Enrollment(course: introToComputing, person: kkLamberty)
	assertTrue kkInIntro.validate()
	assertEquals 'KK Lamberty', kkInIntro.toString()
    }
    void testCompareTo(){
        Person kkLamberty = new Person(name: 'KK Lamberty', email: 'lamberty@morris.umn.edu')
        Person bill = new Person(name: 'bill', email: 'bill@morris.umn.edu')
      
        Course introToComputing = new Course(abbreviation:'CSCI 1001', name:'Intro to the Computing World', term:'Spring',year: 2012, owner: kkLamberty)
        Course introToLlamas = new Course(abbreviation:'Llama1001', name:'Llamas', term:'Spring',year: 2012, owner: bill)
        
        Enrollment kkInIntro = new Enrollment(course: introToComputing, person: kkLamberty)
        Enrollment billInLlamas = new Enrollment(course: introToLlamas, person: bill)
        
        assertEquals billInLlamas.compareTo(billInLlamas), 0
        assertEquals billInLlamas.compareTo(kkInIntro), 23
        assertEquals kkInIntro.compareTo(billInLlamas), -23
    }
}
