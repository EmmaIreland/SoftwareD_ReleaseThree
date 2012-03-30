package survey

class Course {
    String abbreviation
    String name
    String term
    String year
    
    private static final MINIMUM_YEAR = 2000
    
    static hasMany = [enrollments:Enrollment, projects:Project]
    static belongsTo = [owner:Person]
    
    static constraints = {
        abbreviation blank: false
        name blank: false
        term(blank: false, inList: [ 'Fall', 'Spring', 'May', 'Summer I', 'Summer II' ] )
        year validator: { year, course ->
            try {
                Integer.parseInt(year) >= MINIMUM_YEAR
            } catch ( e ) {
                false
            }
        }
    }
    
    String toString() {
        name
    }
}
