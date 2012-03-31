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
	assertEquals 'Course: Intro to the Computing World, Student: KK Lamberty', kkInIntro.toString()


    }
}
