package survey

class Project {
    String name
    String description
    Date dueDate
    static belongsTo = [course: Course]
    static hasMany = [teams: Team, surveys: Survey]
    
    static constraints = {
        name(blank: false)
	description()
        course()
	dueDate(validator: { dueDate -> dueDate.after(new Date()) } )
        teams()
        surveys()
    }
    
    String toString() {
        name + ' for ' + course
    }
}
