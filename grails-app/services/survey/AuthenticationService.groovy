package survey

import org.springframework.web.context.request.RequestContextHolder

class AuthenticationService {
    
    static transactional = true
    
    def serviceMethod() {

    }
    
    def validateLogin(String email, String password) {
	def person = Person.findByEmail(email)
	( person && person.password == hashPassword(password) ) ? person : null
    }
    
    def loginPerson(Person person) {
        def session = RequestContextHolder.currentRequestAttributes().getSession()
	session['user'] = person.id
    }
    
    def logout() {
        def session = RequestContextHolder.currentRequestAttributes().getSession()
	session.invalidate()
    }
    
    // TODO Use a real hash algorithm
    def hashPassword(String password) {
    	password + '*'
    }
}
