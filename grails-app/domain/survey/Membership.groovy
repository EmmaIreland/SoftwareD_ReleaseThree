package survey

class Membership implements Comparable {
    static belongsTo = [team: Team, member: Person]
    
    static constraints = {
	member(unique: 'team')
	
    }
    
    String toString() {
	member
    }
    
    
    @Override
    public int compareTo(Object o) {
	member.name.compareTo(o.member.name)
    }
}
