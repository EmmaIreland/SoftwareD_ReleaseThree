package survey

import grails.plugin.spock.*

class MembershipSpec extends UnitSpec {
    Person matt = new Person(name: 'Matt', email: 'cats@gmail.com')
    Person owner = new Person(name:"WHo cares", email: "him@go.com")
    Course testCourse = new Course(name: "The Course", abbreviation: "CRSE 101", term: 'Fall', year: 2013, owner: owner)
    Project projectOne = new Project(name: "Project One", description: "Stuff happens", dueDate: new Date().next(), course: testCourse, teams:[])
    Team teamOne = new Team(name: "Team One", project: projectOne)
    Team teamTwo = new Team(name: "Team Two", project: projectOne)
    def setup(){
        mockForConstraintsTests(Membership)
    }
    def 'Test Valid Membership'(){
        Membership mattInOne = new Membership(team: teamOne, member: matt)
        
        when: mockDomain(Membership, [mattInOne])
        
        then: mattInOne.validate() == true
        
    }
    
    def 'Test Invalid Membership'(){
        Membership mattInOne = new Membership(team: teamOne, member: matt)
        Membership imInvalid = new Membership(team: teamOne, member: matt)
        
        when: mockDomain(Membership, [mattInOne, imInvalid])
        
        then: imInvalid.validate() == false
    }
    
    def 'Test CompareTo'(){
        Membership mattInOne = new Membership(team: teamOne, member: matt)
        Membership imInvalid = new Membership(team: teamOne, member: matt)
        Membership compareMe = new Membership(team: teamOne, member: owner)
        
        when: mockDomain(Membership, [mattInOne, imInvalid])
        
        then: imInvalid.compareTo(mattInOne) == 0
              mattInOne.compareTo(compareMe) == -10
              compareMe.compareTo(mattInOne) == 10
        
    }
}
