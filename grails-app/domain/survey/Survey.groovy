package survey

class Survey {
    String title
    Date dueDate
    SortedSet questions
    static hasMany = [questions: Question]
    static belongsTo = [project: Project]
    
    static constraints = {
	title(blank: false)
	dueDate(validator: { dueDate -> dueDate.after(new Date()) } )
	questions(nullable: true)
	project()
    }
    
    String toString() {
	title
    }
}
