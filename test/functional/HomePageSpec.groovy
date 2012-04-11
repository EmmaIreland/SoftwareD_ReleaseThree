import geb.spock.GebReportingSpec
import spock.lang.*
import pages.*
import pages.HomePage
import pages.CourseCreatePage
import pages.CourseListPage
import pages.PersonListPage

class HomePageSpec extends GebReportingSpec {

    def "can reach the home page"() {
	when:
	to HomePage

	then:
	at HomePage
    }

    def "can see Course List"() {
	when:
	to HomePage
	coursesButton.click()

	then:
	at CourseListPage
    }

    def "can reach course create page"() {
	when:
	to HomePage
	courseCreateLink.click() 
	
	then:
	at CourseCreatePage
	}
    
    def "can reach the person list page"(){
	when:
	to HomePage
	peopleButton().click()
	
	then:
	at PersonListPage
	
    }
    
    def "can reach the question list page"(){
	when: 
	to HomePage
	questionsButton().click()
	
	then:
	at QuestionListPage
    }
    
    def "can reach the survey list page"(){
	when:
	to HomePage
	surveysButton().click()
	
	then:
	at SurveyListPage
    }
    
    
    def "can reach the project list page"(){
	when:
	to HomePage
	projectsButton().click()
	
	then:
	at ProjectListPage
    }
}