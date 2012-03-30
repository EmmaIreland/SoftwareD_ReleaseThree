package survey

class Enrollment {
    static belongsTo = [course:Course, person:Person]
    static constraints = {
        course()
        person(unique: 'course')
    }
    
    String toString() {
        "Course: ${course}, Student: ${person}"
    }
}
