package survey

class Answer {
    String response
    static belongsTo = [question: Question, person: Person]
    
    static constraints = {
	response(blank: false)
    }
    
    String toString() {
	response
    }
}
