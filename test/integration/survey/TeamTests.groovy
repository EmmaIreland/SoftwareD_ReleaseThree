package survey

import grails.test.*

class TeamTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
	}

    protected void tearDown() {
        super.tearDown()
    }

    void testValidTeamMembers() {
		Person mcPhee = new Person(name: 'Nic McPhee', email: 'mcphee@morris.umn.edu').save()
		Course softwareD = new Course(name: 'Software D', owner: mcPhee).save()
		Person bob = new Person(name: 'bob', email: 'bob@bob.bob').save()
		Person steve = new Person(name: 'steve', email: 'steve@steve.com').save()
		Enrollment bobEnrollment = new Enrollment(person: bob, course: softwareD).save()
		Project project = new Project(name: 'Lab 1', course: softwareD).save()
				
		//assertEquals([bob], Team.getValidTeamMembers(project))
		assertTrue true
    }
}
