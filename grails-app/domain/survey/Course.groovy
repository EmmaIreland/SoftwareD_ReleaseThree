package survey

class Course {
    String abbreviation
    String name
    String term
    int year
    SortedSet enrollments

    private static final String cYear = new Date().format('yyyy')
    private static final int currentYear = Integer.parseInt(cYear)
    
    static hasMany = [enrollments:Enrollment, projects:Project]
    static belongsTo = [owner:Person]
    
    static constraints = {
        abbreviation blank: false
        name blank: false
        term(blank: false, inList: [ 'Fall', 'Spring', 'May', 'Summer I', 'Summer II' ] )
	year(blank: false, inList: [ currentYear, currentYear + 1 ] )
    }
    
    String toString() {
        name
    }
}
