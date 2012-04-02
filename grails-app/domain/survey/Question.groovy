package survey
import survey.questions.*

class Question implements Comparable {
    String prompt
    Date dateCreated
    
    static hasMany = [answers: Answer]

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

    def getSurvey() {
	def allSurveys = Survey.findAll()

	for(i in allSurveys){
	    if(i.questions.contains(this)){
		return i
		break
	    }
	}

    }

}
