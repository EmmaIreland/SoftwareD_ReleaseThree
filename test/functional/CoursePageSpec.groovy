import geb.spock.GebReportingSpec
import spock.lang.*
import pages.*
import pages.CourseShowPage
import pages.CourseCreatePage

class CoursePageSpec extends GebReportingSpec {
    
    def "course create should go to course show"() {
	when:
	to CourseCreatePage
        courseAbbreviationBox.value("SA")
        courseNameBox.value("Some Name")
	courseCreateButton.click()
	
	then:
	at CourseShowPage
    }
}