package survey

import grails.plugin.spock.*

class TeamSpec extends UnitSpec {
    Person mcPhee = new Person(name: 'Nic McPhee', email: 'mcphee@morris.umn.edu')
    Course softwareD = new Course(name: 'Software D', owner: mcPhee)
    Person bob = new Person(name: 'bob', email: 'bob@bob.bob')
    Person steve = new Person(name: 'steve', email: 'steve@steve.com')
    Enrollment bobEnrollment = new Enrollment(person: bob, course: softwareD)
    Project project = new Project(name: 'Lab 1', course: softwareD)
    Membership bobInTeam = new Membership(team: testTeam, member: bob)
    Team testTeam = new Team(name:'Team!', project: project)
    def setup() {
        mockDomain(Person, [mcPhee, bob, steve])
        mockDomain(Course, [softwareD])
        mockDomain(Project, [project])
        mockDomain(Enrollment, [bobEnrollment])
        mockDomain(Team, [testTeam])
        mockDomain(Membership, [bobInTeam])
        mockForConstraintsTests(Team)
    }

    def 'Test toString'() {
        expect: testTeam.toString() == testTeam.name
                
        
    }
}
