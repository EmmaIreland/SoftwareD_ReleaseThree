package survey.questions

import survey.*

class ShortTextQuestion extends Question implements QuestionInterface {
	final Object templateName = 'Short'
    static hasMany = [responses: Answer]

    static constraints = {
    }


    @Override
    public Object getAnswer(Person person) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    Question copyQuestion() {
        def newQuestion = new ShortTextQuestion(prompt: prompt).save(failOnError: true)
        newQuestion
    }

}
