package survey

import grails.test.*

class CourseTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
	mockForConstraintsTests(Course)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testYearAfterMinimum() {
	Person elena = new Person (name: 'Elena', email: 'elenam@morris.umn.edu')
    	Course programmingLanguages = new Course(abbreviation: 'CSci 4651', name: 'Programming Languages', term: 'Spring', year: '2012', owner: elena)
	assertTrue programmingLanguages.validate()
    }
    
    void testTermNotInList() {
	Person elena = new Person (name: 'Elena', email: 'elenam@morris.umn.edu')
	Course programmingLanguages = new Course(abbreviation: 'CSci 4651', name: 'Programming Languages', term: 'Spring I', year: '2012', owner: elena)
	assertFalse programmingLanguages.validate()
	assertEquals 'inList', programmingLanguages.errors['term'] 
    }
}
