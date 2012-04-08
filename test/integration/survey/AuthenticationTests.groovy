package survey

import grails.test.*
import survey.questions.*

class AuthenticationTests extends GrailsUnitTestCase {
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testPreHashing() {
        AuthenticationService authenticationService = new AuthenticationService()
        def password = 'pills'
        Person polly = new Person(name: 'Polly Stevens', password: password, email: 'polly@testing.org').save(failOnError: true)
        
        assertEquals authenticationService.hashPassword(password), polly.password
    }
}
