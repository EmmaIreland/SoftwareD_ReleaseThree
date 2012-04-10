import geb.spock.GebReportingSpec
import spock.lang.*
import pages.*

class HomePageSpec extends GebReportingSpec {
    def "can reach the home page"() {
        when:
        to HomePage
        
        then:
        at HomePage
    }
}