package survey

class Team {
    String name
    String comments
    SortedSet memberships
    static hasMany = [memberships: Membership]
    static belongsTo = [project: Project]

    static constraints = {
        name(nullable: true, blank: false)      
        project(nullable: true)
	comments(nullable: true)
        memberships()
    }
    
    String toString() {
      name
    }
}