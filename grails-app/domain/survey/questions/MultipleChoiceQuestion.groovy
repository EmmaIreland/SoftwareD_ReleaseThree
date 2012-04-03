package survey.questions

import survey.Question
import survey.QuestionInterface

class MultipleChoiceQuestion extends Question implements QuestionInterface{
	final Object templateName = 'MultipleChoice'
    List choices
    static hasMany = [choices: String]

    static constraints = {
        choices(validator: { choices -> !choices.isEmpty() })
    }

    
    Question copyQuestion() {
        def newQuestion = new MultipleChoiceQuestion(prompt: prompt, choices: copyList(choices)).save(failOnError: true)
        newQuestion
    }
}
