package survey

import grails.plugin.spock.*

class PersonSpec extends UnitSpec {
	Person matt = new Person(name: 'Matt', email: 'cats@gmail.com')
	
	def setup() {
		mockForConstraintsTests(Person)
	}
	
	def 'Test Name and Email'() {
		expect:
			matt.name == 'Matt'
			matt.email == 'cats@gmail.com'
	}
	
	def 'Test toString'() {
		expect:
			matt.toString() == 'Matt'
	}
	
	def 'Test User Validate'() {
		when: mockDomain(Person, [matt])

		then: matt.validate() == true
	}
}
