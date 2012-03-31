package survey

import grails.test.*

class PersonControllerTests extends ControllerUnitTestCase {
    Person jimbob = new Person(name: 'Jimbob B', email: 'jimbob@bobob.jimbobjim')
    Person joe = new Person(name: 'Joe E', email: 'hipsterglasses@obscurethings.fm')
	
    protected void setUp() {
        super.setUp()
		mockForConstraintsTests(Person)
		mockDomain Person, [joe, jimbob]
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testIndex(){
		controller.index()
		assertEquals 'list', controller.redirectArgs.action
    }
	
	void testCreatePerson(){
		controller.params.name = 'Jimjoe'
		controller.params.email = 'joejim@jimjoe.edu'
		assertEquals controller.create().personInstance.name, 'Jimjoe'
		assertEquals controller.create().personInstance.email, 'joejim@jimjoe.edu'
	}
	
	void testSavePerson(){
		Person newPerson = new Person(name:'Pam', email:'pam@pam.pam')
		assertEquals Person.list().size(), 2
		assertEquals newPerson.save(), newPerson
		assertEquals Person.list().size(), 3
	}
	
	void testShowPerson(){
		controller.params.id = 1
		assertEquals controller.show().personInstance, joe
		
		controller.params.id = 2
		assertEquals controller.show().personInstance, jimbob

		
		controller.params.id = 100
		assertEquals controller.redirectArgs.action, controller.request.message
		
	}
		
	void testEditPerson(){
		controller.params.id = 1
		assertEquals controller.edit().personInstance, joe
		
		controller.params.id = 2
		assertEquals controller.edit().personInstance, jimbob

		
		controller.params.id = 100
		assertEquals controller.redirectArgs.action, controller.request.message
	}
	
	void testDeletePerson(){
		controller.params.id = 1
		assertEquals controller.redirectArgs.action, controller.request.message
		assertEquals Person.list().size(), 2
	}
	
	
	
}

