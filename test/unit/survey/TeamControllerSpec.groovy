package survey

import grails.plugin.spock.*

class TeamControllerSpec extends ControllerSpec {
    Person admin = new Person(name:'admin', email:'admin@admin.com', password:'admin')
    Person mcPhee = new Person(name: 'Nic McPhee', email: 'mcphee@morris.umn.edu')
    Person bob = new Person(name: 'bob', email: 'bob@bob.bob')
    Person steve = new Person(name: 'steve', email: 'steve@steve.com')
    Course testCourse = new Course(name:'test course', abbreviation:'test', term:'Fall', year:2012, owner:admin)
    Enrollment bobEnroll = new Enrollment(person: bob, course: testCourse)
    Enrollment adminEnroll = new Enrollment(person: admin, course: testCourse)
    Project project = new Project(name: 'Lab 1', course: testCourse)
    Team testTeam = new Team(name:'test team', project: project, memberships:bobInTeam)
    Team invalid = new Team(name:'Isuck', project:project)
    Membership steveInTeam = new Membership(team:testTeam, member: steve)
    Membership bobInTeam = new Membership(team:testTeam, member: bob)
    def setup(){
        mockForConstraintsTests(Team)
        mockDomain(Person, [admin, mcPhee, bob, steve])
        mockDomain(Course, [testCourse])
        mockDomain(Enrollment, [bobEnroll, adminEnroll])
        mockDomain(Project, [project])
        mockDomain(Team, [testTeam, invalid])
    }
    def 'Test Index'(){
        when:
            controller.index()
        then:
            controller.redirectArgs.action == 'list'            
    }
    def 'Test addmany'(){
        when:
            controller.params.project = project
            controller.params.project.id = project.id
            controller.params.num_groups = '2'
            controller.params.random = 'on'
        then:
            controller.params != null
           
    }
}
