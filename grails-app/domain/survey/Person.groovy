package survey

class Person {
    String name
    String email
    String password
    Boolean isAdmin = false
    static hasMany = [ownedCourses:Course, enrollments:Enrollment, memberships: Membership, answers: Answer]
    
    static constraints = {
        name(blank: false)
        email(email: true, blank:false, unique: true)
	password(nullable: true) // TODO make blank false and size constraint, true now for tests
    }
    
    String toString() {
        name
    }
}
