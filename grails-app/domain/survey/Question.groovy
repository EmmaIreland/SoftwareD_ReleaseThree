package survey
import survey.questions.*

class Question {
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
