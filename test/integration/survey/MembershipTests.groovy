package survey

import grails.test.*

class MembershipTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }
    
    protected void tearDown() {
        super.tearDown()
    }

    void testPersonHasOneTeamPerProject() {
	Person owner = new Person(name:"WHo cares", email: "him@go.com").save(failOnError: true)
	Course testCourse = new Course(name: "The Course", abbreviation: "CRSE 101", term: 'Fall', year: 2013, owner: owner).save(failOnError: true)
	Project projectOne = new Project(name: "Project One", description: "Stuff happens", dueDate: new Date().next(), course: testCourse, teams:[]).save(failOnError: true)
	Team teamOne = new Team(name: "Team One", project: projectOne).save(failOnError: true)
	Team teamTwo = new Team(name: "Team Two", project: projectOne).save(failOnError: true)
	projectOne.teams << teamOne
	projectOne.teams << teamTwo
	projectOne.save(failOnError: true)
	Person testPerson = new Person(name: "Phil", email: "bob@bob.bob").save(failOnError: true)
	Membership memberTeamOne = new Membership(member: testPerson, team: teamOne).save(failOnError: true)
	
	boolean result = memberTeamOne.validate()
	println "Validated first team member"
	println memberTeamOne.errors
	assertTrue result
	
	assertNull new Membership(member: testPerson, team: teamTwo).save()
    }
}
