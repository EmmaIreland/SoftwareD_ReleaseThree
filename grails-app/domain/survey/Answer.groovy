package survey

class Answer {
    static belongsTo = [question: Question, person: Person]
    
    static constraints = {
    }
    
    String toString() {
	"" // TODO Fix this
    }
}
