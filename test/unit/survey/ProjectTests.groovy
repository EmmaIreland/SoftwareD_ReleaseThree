package survey

import grails.test.*

class ProjectTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
	mockForConstraintsTests(Project)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDueDateInFuture() {
	Person elena = new Person (name: 'Elena', email: 'elenam@morris.umn.edu')
	Course programmingLanguages = new Course(abbreviation: 'CSci 4651', name: 'Programming Languages', term: 'Spring', year: 2012, owner: elena)
	Project releaseOne = new Project(name: 'Release One', description: 'Students do cool things', course: programmingLanguages, dueDate: new Date().next())
    	assertTrue releaseOne.validate()
	
    }
    
    void testPastDueDate() {
	Person elena = new Person (name: 'Elena', email: 'elenam@morris.umn.edu')
	Course programmingLanguages = new Course(abbreviation: 'CSci 4651', name: 'Programming Languages', term: 'Spring', year: 2012, owner: elena)
	Project releaseOne = new Project(name: 'Release One', description: 'Students do cool things', course: programmingLanguages, dueDate: new Date())
	assertFalse releaseOne.validate()
	assertEquals 'validator', releaseOne.errors['dueDate']
    }
    
}
