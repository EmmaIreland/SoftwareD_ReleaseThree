package survey
import survey.questions.*

class Question implements Comparable {
    String prompt
    Date dateCreated

    static constraints = {
	prompt(blank: false)
    }
    
    def getAnswer(Person person) {
        Answer.findByPersonAndQuestion(person, this)
    }
    
    String toString() {
        prompt
    }
    
    def copyList(list) {
        def newList = list.collect { it }
        newList
    }

    @Override
    public int compareTo(Object o) {
	dateCreated.compareTo(o.dateCreated)
	
    }
}
