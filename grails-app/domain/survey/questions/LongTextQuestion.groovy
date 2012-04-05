package survey.questions

import survey.*

class LongTextQuestion extends Question implements QuestionInterface{
    final Object templateName = 'Long'
    static hasMany = [responses: Answer]
    
    static constraints = {
    }

    @Override
    public Object getAnswer(Person person) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }
    
    Question copyQuestion() {
        def newQuestion = new LongTextQuestion(prompt: prompt).save(failOnError: true)
        newQuestion
    }
}
