package survey

class Person {
    String name
    String email
    static hasMany = [ownedCourses:Course, enrollments:Enrollment, memberships: Membership, answers: Answer]
    
    static constraints = {
        name(blank: false)
        email(email: true, blank:false, unique: true)
    }
    
    String toString() {
        name
    }
}
