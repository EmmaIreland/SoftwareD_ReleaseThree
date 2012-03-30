package survey.questions

class LongTextQuestionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [longTextQuestionInstanceList: LongTextQuestion.list(params), longTextQuestionInstanceTotal: LongTextQuestion.count()]
    }

    def create = {
        def longTextQuestionInstance = new LongTextQuestion()
        longTextQuestionInstance.properties = params
        return [longTextQuestionInstance: longTextQuestionInstance]
    }

    def save = {
        def longTextQuestionInstance = new LongTextQuestion(params)
        if (longTextQuestionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'longTextQuestion.label', default: 'LongTextQuestion'), longTextQuestionInstance.id])}"
            redirect(action: "show", id: longTextQuestionInstance.id)
        }
        else {
            render(view: "create", model: [longTextQuestionInstance: longTextQuestionInstance])
        }
    }

    def show = {
        def longTextQuestionInstance = LongTextQuestion.get(params.id)
        if (!longTextQuestionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'longTextQuestion.label', default: 'LongTextQuestion'), params.id])}"
            redirect(action: "list")
        }
        else {
            [longTextQuestionInstance: longTextQuestionInstance]
        }
    }

    def edit = {
        def longTextQuestionInstance = LongTextQuestion.get(params.id)
        if (!longTextQuestionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'longTextQuestion.label', default: 'LongTextQuestion'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [longTextQuestionInstance: longTextQuestionInstance]
        }
    }

    def update = {
        def longTextQuestionInstance = LongTextQuestion.get(params.id)
        if (longTextQuestionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (longTextQuestionInstance.version > version) {
                    
                    longTextQuestionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'longTextQuestion.label', default: 'LongTextQuestion')] as Object[], "Another user has updated this LongTextQuestion while you were editing")
                    render(view: "edit", model: [longTextQuestionInstance: longTextQuestionInstance])
                    return
                }
            }
            longTextQuestionInstance.properties = params
            if (!longTextQuestionInstance.hasErrors() && longTextQuestionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'longTextQuestion.label', default: 'LongTextQuestion'), longTextQuestionInstance.id])}"
                redirect(action: "show", id: longTextQuestionInstance.id)
            }
            else {
                render(view: "edit", model: [longTextQuestionInstance: longTextQuestionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'longTextQuestion.label', default: 'LongTextQuestion'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def longTextQuestionInstance = LongTextQuestion.get(params.id)
        if (longTextQuestionInstance) {
            try {
                longTextQuestionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'longTextQuestion.label', default: 'LongTextQuestion'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'longTextQuestion.label', default: 'LongTextQuestion'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'longTextQuestion.label', default: 'LongTextQuestion'), params.id])}"
            redirect(action: "list")
        }
    }
}
