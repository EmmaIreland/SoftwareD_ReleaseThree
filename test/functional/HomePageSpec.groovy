import geb.spock.GebReportingSpec
import spock.lang.*
import pages.*
import pages.HomePage
import pages.CourseCreatePage
import pages.CourseListPage


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
    
}