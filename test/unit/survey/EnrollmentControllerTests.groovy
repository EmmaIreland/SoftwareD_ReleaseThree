package survey

import grails.test.*

class EnrollmentControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAvailableStudents() {
	
	Person person1 = new Person(name: "Eric", email: "sup@sup.com")
	Person person2 = new Person(name: "Joe", email: "sup@sup.com")
	Person person3 = new Person(name: "Mike", email: "sup@sup.com")
	Person owner = new Person(name: "Kate", email: "sup@sup.com")	
	mockDomain(Person,[person1,person2,person3,owner])
	
	Course course1 = new Course(owner: owner, name: "course1")	
	mockDomain(Course, [course1])
		
	Enrollment enrollment1 = new Enrollment(course: course1, person: person1)
	mockDomain(Enrollment,[enrollment1])
	
	course1.enrollments = [enrollment1]
	
	controller.params.course = course1
	def results = controller.create()
	
	assertNotNull results
		
	assertTrue results.availableStudents.containsAll([person2, person3,owner])
	//assertEquals 1 , results.unavailableStudents.size()
	assertEquals 3, results.availableStudents.size()
	

    }
}
