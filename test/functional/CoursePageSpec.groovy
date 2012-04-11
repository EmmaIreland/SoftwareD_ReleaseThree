import geb.spock.GebReportingSpec
import spock.lang.*
import pages.*
import pages.CourseShowPage
import pages.CourseCreatePage

class CoursePageSpec extends GebReportingSpec {
    
    def "Course create should go to course show"() {
	when:
	to CourseCreatePate
	courseCreateButton.click()
	
	then:
	at CourseShowPage
    }
}