package survey

public interface QuestionInterface {
    def getAnswer(Person person)
    def getTemplateName()
    Question copyQuestion()
}