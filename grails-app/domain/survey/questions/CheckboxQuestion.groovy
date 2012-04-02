package survey.questions

import survey.*

class CheckboxQuestion extends Question implements QuestionInterface {
    List choices
    static hasMany = [choices: String]
    
    static constraints = {
        choices(validator: { choices -> !choices.isEmpty() })
    }
    
    @Override
    public Object getTemplateName() {
	'Checkbox'	
    }
    
    Question copyQuestion() {
        def newQuestion = new CheckboxQuestion(prompt: prompt, choices: copyList(choices)).save(failOnError: true)
        newQuestion
    }
}
