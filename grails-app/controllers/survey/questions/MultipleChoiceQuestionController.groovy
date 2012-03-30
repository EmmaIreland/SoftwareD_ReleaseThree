package survey.questions

class MultipleChoiceQuestionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [multipleChoiceQuestionInstanceList: MultipleChoiceQuestion.list(params), multipleChoiceQuestionInstanceTotal: MultipleChoiceQuestion.count()]
    }

    def create = {
        def multipleChoiceQuestionInstance = new MultipleChoiceQuestion()
        multipleChoiceQuestionInstance.properties = params
        return [multipleChoiceQuestionInstance: multipleChoiceQuestionInstance]
    }

    def save = {
        def multipleChoiceQuestionInstance = new MultipleChoiceQuestion(params)
        if (multipleChoiceQuestionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'multipleChoiceQuestion.label', default: 'MultipleChoiceQuestion'), multipleChoiceQuestionInstance.id])}"
            redirect(action: "show", id: multipleChoiceQuestionInstance.id)
        }
        else {
            render(view: "create", model: [multipleChoiceQuestionInstance: multipleChoiceQuestionInstance])
        }
    }

    def show = {
        def multipleChoiceQuestionInstance = MultipleChoiceQuestion.get(params.id)
        if (!multipleChoiceQuestionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'multipleChoiceQuestion.label', default: 'MultipleChoiceQuestion'), params.id])}"
            redirect(action: "list")
        }
        else {
            [multipleChoiceQuestionInstance: multipleChoiceQuestionInstance]
        }
    }

    def edit = {
        def multipleChoiceQuestionInstance = MultipleChoiceQuestion.get(params.id)
        if (!multipleChoiceQuestionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'multipleChoiceQuestion.label', default: 'MultipleChoiceQuestion'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [multipleChoiceQuestionInstance: multipleChoiceQuestionInstance]
        }
    }

    def update = {
        def multipleChoiceQuestionInstance = MultipleChoiceQuestion.get(params.id)
        if (multipleChoiceQuestionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (multipleChoiceQuestionInstance.version > version) {
                    
                    multipleChoiceQuestionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'multipleChoiceQuestion.label', default: 'MultipleChoiceQuestion')] as Object[], "Another user has updated this MultipleChoiceQuestion while you were editing")
                    render(view: "edit", model: [multipleChoiceQuestionInstance: multipleChoiceQuestionInstance])
                    return
                }
            }
            multipleChoiceQuestionInstance.properties = params
            if (!multipleChoiceQuestionInstance.hasErrors() && multipleChoiceQuestionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'multipleChoiceQuestion.label', default: 'MultipleChoiceQuestion'), multipleChoiceQuestionInstance.id])}"
                redirect(action: "show", id: multipleChoiceQuestionInstance.id)
            }
            else {
                render(view: "edit", model: [multipleChoiceQuestionInstance: multipleChoiceQuestionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'multipleChoiceQuestion.label', default: 'MultipleChoiceQuestion'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def multipleChoiceQuestionInstance = MultipleChoiceQuestion.get(params.id)
        if (multipleChoiceQuestionInstance) {
            try {
                multipleChoiceQuestionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'multipleChoiceQuestion.label', default: 'MultipleChoiceQuestion'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'multipleChoiceQuestion.label', default: 'MultipleChoiceQuestion'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'multipleChoiceQuestion.label', default: 'MultipleChoiceQuestion'), params.id])}"
            redirect(action: "list")
        }
    }
}
