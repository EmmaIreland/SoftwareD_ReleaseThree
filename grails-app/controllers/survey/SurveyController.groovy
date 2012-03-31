package survey
import survey.questions.*
import grails.converters.JSON

class SurveyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", preview: "POST"]

    def index = {
       redirect(action: "list", params: params)
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
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'survey.label', default: 'Survey'), surveyInstance.id])}"
            redirect(action: "show", id: surveyInstance.id)
        }
        else {
            render(view: "create", model: [surveyInstance: surveyInstance])
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
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'survey.label', default: 'Survey'), params.id])}"
            redirect(action: "list")
        }
        else {
            [surveyInstance: surveyInstance, existingQuestions: existingQuestions]
        }
    }
    
    def preview = {
        def surveyInstance = Survey.get(params.id)
        if (!surveyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'survey.label', default: 'Survey'), params.id])}"
            redirect(action: "list")
        }
        else {
            [surveyInstance: surveyInstance]
        }
    }

    def edit = {
        def surveyInstance = Survey.get(params.id)
        if (!surveyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'survey.label', default: 'Survey'), params.id])}"
            redirect(action: "list")
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
                    
                    surveyInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'survey.label', default: 'Survey')] as Object[], "Another user has updated this Survey while you were editing")
                    render(view: "edit", model: [surveyInstance: surveyInstance])
                    return
                }
            }
            surveyInstance.properties = params
            if (!surveyInstance.hasErrors() && surveyInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'survey.label', default: 'Survey'), surveyInstance.id])}"
                redirect(action: "show", id: surveyInstance.id)
            }
            else {
                render(view: "edit", model: [surveyInstance: surveyInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'survey.label', default: 'Survey'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def surveyInstance = Survey.get(params.id)
        if (surveyInstance) {
            try {
                surveyInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'survey.label', default: 'Survey'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'survey.label', default: 'Survey'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'survey.label', default: 'Survey'), params.id])}"
            redirect(action: "list")
        }
    }
}
