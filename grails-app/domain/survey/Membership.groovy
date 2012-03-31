package survey

class Membership {
    static belongsTo = [team: Team, member: Person]
    
    static constraints = {
	team(validator: oneMembershipPerProject)
	member(unique: 'team')
	
    }
    
    String toString() {
	member
    }
    
    static oneMembershipPerProject = { team, membership -> 
	def duplicateNotFound = true
	def groups = team.project.teams
	for(Team group: groups) {
	    if ((group.id != team.id) && (Membership.findByTeamAndMember(group, membership.member)) != null) {
		duplicateNotFound = false
	    }
	}
	duplicateNotFound
    }
}
