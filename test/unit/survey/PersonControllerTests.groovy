package survey

import grails.test.*
import groovy.mock.interceptor.MockFor
class PersonControllerTests extends ControllerUnitTestCase {
    Person jimbob = new Person(name: 'Jimbob B', email: 'jimbob@bobob.jimbobjim', password: 'password')
    Person joe = new Person(name: 'Joe E', email: 'hipsterglasses@obscurethings.fm', password: 'macchiato')
    
    protected void setUp() {
        super.setUp()
        mockForConstraintsTests(Person)
        mockDomain Person, [joe, jimbob]
        controller.metaClass.message = { "showString" }
        
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
        controller.params.password = 'hunter1'
        def results = controller.create()
        assertEquals controller.create().personInstance.name, 'Jimjoe'
        assertEquals controller.create().personInstance.email, 'joejim@jimjoe.edu'
    }

    void testSavePerson(){
        def controller = new PersonController()
        def mockAuth = new MockFor(AuthenticationService)
        mockAuth.demand.hashPassword{'password'}
        controller.authenticationService = mockAuth
        Person newPerson = new Person(id: 3, name:'Pam', email:'pam@pam.pam', password: 'iampam')
        assertEquals Person.list().size(), 2
        controller.params.name = 'Pam'
        controller.params.email = 'pam@pam.pam'
        controller.params.password = 'iampam'
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
        def results = controller.delete()
        assertEquals Person.list().size(), 1
        println results
        controller.params.id = 100
        controller.delete()
        assertEquals controller.redirectArgs.action, controller.listString
    }

    void testUpdatePerson(){
        controller.params.id = 1
        controller.metaClass.message = { "showString" }
        controller.update()
        assertEquals controller.redirectArgs.action, 'show'
        assertEquals controller.redirectArgs.id, controller.params.id
    }
    void testUpdateInvalidPerson() {
        controller.params.id = 100
        controller.metaClass.message = { "editString" }
        controller.update()
    }
    void testSendLogin() {
        controller.params.email = 'hipsterglasses@obscurethings.fm'
        controller.params.password = 'macchiato'
        def results = controller.sendLogin()
        println results
    }
}

