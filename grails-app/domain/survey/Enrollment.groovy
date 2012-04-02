package survey

class Enrollment implements Comparable{
    static belongsTo = [course:Course, person:Person]
    static constraints = {
        course()
        person(unique: 'course')
    }
    
    String toString() {
        person
    }
    
    @Override
    public int compareTo(Object o) {
	person.name.compareTo(o.person.name)
    }
}
