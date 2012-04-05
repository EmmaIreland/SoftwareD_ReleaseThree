package survey
import survey.answers.*
import survey.questions.*
import grails.converters.JSON

class SurveyController {

    static post = 'POST'
    def listString = 'list'
    def editString = 'edit'
    def createString = 'create'
    def showString = 'show'
    def defaultNotFoundMessage = 'default.not.found.message'

    static allowedMethods = [save: post, update: post, delete: post, preview: post]

    def index = {
        redirect(action: listString, params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [surveyInstanceList: Survey.list(params), surveyInstanceTotal: Survey.count()]
    }

    def create = {
        def surveyInstance = new Survey()
        surveyInstance.properties = params
        return [surveyInstance: surveyInstance]
    }

    def save = {
        def surveyInstance = new Survey(params)
        if (surveyInstance.save(flush: true)) {
            flash.message = makeMessage('default.created.message', surveyInstance.title)
            redirect(action: showString, id: surveyInstance.id)
        }
        else {
            render(view: createString, model: [surveyInstance: surveyInstance])
        }
    }

    private removeEmptyElements(def input) {
        def list = input as List
        list.retainAll{
            it.length() > 0
        }
        return list
    }

    def addQuestion = {
        def surveyInstance = Survey.get(params.surveyid)
        def surveyType = params.type
        def questionInstance

        switch(surveyType){
            case 'existing':
                questionInstance = Question.get(params.questionid).copyQuestion()
                break
            case 'checkbox':
                def choices = removeEmptyElements(params.cbChoices)
                questionInstance = new CheckboxQuestion(prompt: params.cbPrompt, choices: choices).save(failOnError: true)
                break
            case 'multipleChoice':
                def choices = removeEmptyElements(params.mcChoices)
                questionInstance = new MultipleChoiceQuestion(prompt: params.mcPrompt, choices: choices).save(failOnError: true)
                break
            case 'shortResponse':
                questionInstance = new ShortTextQuestion(prompt: params.stPrompt).save(failOnError: true)
                break
            case 'longResponse':
                questionInstance = new LongTextQuestion(prompt: params.ltPrompt).save(failOnError: true)
                break
        }
        surveyInstance.addToQuestions(questionInstance)
        surveyInstance.save(failOnError: true)
        render questionInstance as JSON
    }

    def show = {
        def surveyInstance = Survey.get(params.id)
        def existingQuestions = Question.findAll()
        if (!surveyInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
        else {
            [surveyInstance: surveyInstance, existingQuestions: existingQuestions]
        }
    }

    def preview = {
        def surveyInstance = Survey.get(params.id)
        if (!surveyInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
        else {
            [surveyInstance: surveyInstance]
        }
    }

    def submit = {
        def surveyInstance = Survey.get(params.id)
        def personInstance = Person.get(params.personid)
        if (!surveyInstance || !personInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
            return
        }
        def questions = surveyInstance.questions
        def questionIds = params.keySet().findAll { isNumber(it) }

        questions.each { question ->
            def serverResponse = params["" + question.id]
            createAnswer(question, personInstance, serverResponse)
        }
        redirect(action: list)
    }

    private createAnswer(question, person, serverResponse) {
        def answer
        switch(question.templateName) {
            case 'Long':
            case 'Short':
                answer = new TextAnswer(response: serverResponse, person: person, question: question).save(failOnError: true)
                break
            case 'MultipleChoice':
                answer = new MultipleChoiceAnswer(responseIndex: serverResponse, person: person, question: question).save(failOnError: true)
                break
            case 'Checkbox':
                def responseMap = [:]
                println "the serverResponse class is" + serverResponse.class
                if (serverResponse instanceof String) {
                    serverResponse = [serverResponse]
                } else {
                    serverResponse = serverResponse as List
                }

                question.choices.eachWithIndex { choice, index ->
                    

                responseMap[index] = serverResponse.contains(index)
                }
                println("About to create a CheckboxAnswer")
                answer = new CheckboxAnswer(responses: responseMap, person: person, question: question)
                println("Constructed the CheckboxAnswer")
                answer.save(failOnError: true)
                println("Saved the CheckboxAnswer")
                break
            default:
                println("We reached the default case in createAnswer")
                break
        }
        println("We've left the switch")
        println answer
        println("We've printed the answer")
    }

    private isNumber(string) {
        try {
            string.toInteger()
            true
        } catch (NumberFormatException e) {
            false
        }
    }

    def take = {
        def surveyInstance = Survey.get(params.id)
        def personInstance = Person.get(params.personid)
        if (!surveyInstance || !personInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
        else {
            [surveyInstance: surveyInstance, personInstance: personInstance]
        }
    }

    def edit = {
        def surveyInstance = Survey.get(params.id)
        if (!surveyInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
        else {
            return [surveyInstance: surveyInstance]
        }
    }

    def update = {
        def surveyInstance = Survey.get(params.id)
        if (surveyInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (surveyInstance.version > version) {

                    surveyInstance.errors.rejectValue('version', 'default.optimistic.locking.failure', [message(code: 'survey.label', default: 'Survey')] as Object[], 'Another user has updated this Survey while you were editing')
                    render(view: editString, model: [surveyInstance: surveyInstance])
                    return
                }
            }
            surveyInstance.properties = params
            if (!surveyInstance.hasErrors() && surveyInstance.save(flush: true)) {
                flash.message = makeMessage('default.updated.message', surveyInstance.title)
                redirect(action: showString, id: surveyInstance.id)
            }
            else {
                render(view: editString, model: [surveyInstance: surveyInstance])
            }
        }
        else {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
    }

    def delete = {
        def surveyInstance = Survey.get(params.id)
        if (surveyInstance) {
            try {
                surveyInstance.delete(flush: true)
                flash.message = makeMessage('default.deleted.message', surveyInstance.title)
                redirect(action: listString)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = makeMessage('default.not.deleted.message', params.id)
                redirect(action: showString, id: params.id)
            }
        }
        else {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
    }

    private makeMessage(code, surveyId) {
        return "${message(code: code, args: [surveyLabel(), surveyId])}"
    }

    private surveyLabel() {
        message(code: 'survey.label', default: 'Survey')
    }

}
