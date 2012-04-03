package survey

class Survey {
    String title
    Date dueDate
    List questions
    static hasMany = [questions: Question]
    static belongsTo = [project: Project]
    
    static constraints = {
	title(blank: false)
	dueDate(validator: { dueDate -> dueDate.after(new Date().previous()) } )
	questions(nullable: true)
	project()
    }
    
    String toString() {
	title
    }
}
