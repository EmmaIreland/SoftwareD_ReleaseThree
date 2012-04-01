package survey.questions

import survey.Question
import survey.QuestionInterface

class MultipleChoiceQuestion extends Question implements QuestionInterface{
    static hasMany = [choices: String]

    static constraints = {
        choices(validator: { choices -> !choices.isEmpty() })
    }

    @Override
    public Object getTemplateName() {
	'MultipleChoice'
    }

    
    Question copyQuestion() {
        def newQuestion = new MultipleChoiceQuestion(prompt: prompt, choices: copyList(choices)).save(failOnError: true)
        newQuestion
    }
}
