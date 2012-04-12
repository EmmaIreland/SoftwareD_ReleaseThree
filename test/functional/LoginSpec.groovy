import geb.spock.GebReportingSpec
import spock.lang.*
import pages.*
import pages.HomePage
import pages.CourseCreatePage
import pages.CourseListPage
import pages.PersonListPage
import pages.LoginPage

class LoginSpec extends GebReportingSpec {

    def "can reach the login page"() {
	when:
	to LoginPage

	then:
	at LoginPage
    }
    
    def "logging in with correct data goes home"() {
        when:
        to LoginPage
        loginEmailField.value('mcphee@morris.umn.edu')
        loginPasswordField.value('thomas')
        loginButton.click()
        
        then:
        at HomePage
    }
    
    def "logging in with incorrect data goes back to LoginPage"() {
        when:
        to LoginPage
        loginEmailField.value('mcphee@morris.umn.edu')
        loginPasswordField.value('foo')
        loginButton.click()
        
        then:
        at LoginPage
    }
    
}