package survey.questions

import survey.*

class CheckboxQuestion extends Question implements QuestionInterface {
    List choices
	final Object templateName = 'Checkbox'
    static hasMany = [choices: String]
    
    static constraints = {
        choices(validator: { choices -> !choices.isEmpty() })
    }
    

    
    Question copyQuestion() {
        def newQuestion = new CheckboxQuestion(prompt: prompt, choices: copyList(choices)).save(failOnError: true)
        newQuestion
    }
}
