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
	
	course1.enrollments = new TreeSet([enrollment1])
	
	controller.params.course = course1
	def results = controller.create()
	assertNotNull results
	assertTrue results.availableStudents.containsAll([person2, person3])
        println results.availableStudents
	assertEquals 2, results.availableStudents.size()
    }
    
    void testSaveStudents() {  
        
        Person owner = new Person(name: "Kate", email: "sup@sup.com")
        mockDomain(Person, [owner])
        Course course1 = new Course(owner: owner, name: "course1")      
        mockDomain(Course, [course1])
        Enrollment enrollment = new Enrollment(course:course1, person: owner)
        mockDomain(Enrollment,[])
        
        controller.params.course = course1
        controller.params.name = owner.name
        def results = controller.save()
        assertNull results
        assertEquals controller.redirectArgs.action, "create"
        assertEquals controller.redirectArgs.controller, "enrollment"
        assertEquals controller.redirectArgs.params, ['course.id': course1.id]
        
    }
    void testInvalidEnrollment() {
        Person owner = new Person(name: "Kate", email: "sup@sup.com")
        mockDomain(Person, [owner])
        Course course1 = new Course(owner: owner, name: "course1")
        mockDomain(Course, [course1])
        mockDomain(Enrollment,[])
        controller.params.course = course1
        controller.params.name = null
        def results = controller.save()
        assertEquals results.Student, null
        assertEquals controller.renderArgs.view, "create"
        
    }
    void testShowEnrollment() {
        Person owner = new Person(name: "Kate", email: "sup@sup.com")
        mockDomain(Person, [owner])
        Course course1 = new Course(owner: owner, name: "course1")
        mockDomain(Course, [course1])
        Enrollment enrollment = new Enrollment(course:course1, person: owner)
        mockDomain(Enrollment,[enrollment])
         controller.params.id = owner.id
         def results = controller.show()
         assertEquals results.enrollmentInstance, enrollment
    }
    void testShowInvalidEnrollment(){
        Person owner = new Person(name: "Kate", email: "sup@sup.com")
        mockDomain(Person, [owner])
        Course course1 = new Course(owner: owner, name: "course1")
        mockDomain(Course, [course1])
        Enrollment enrollment = new Enrollment(course:course1, person: owner)
        mockDomain(Enrollment,[enrollment])
        controller.params.id = null
        assertEquals controller.request.message, null
    }
}
