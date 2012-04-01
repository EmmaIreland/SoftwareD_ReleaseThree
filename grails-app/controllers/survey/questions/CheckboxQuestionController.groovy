package survey.questions

import survey.*

class CheckboxQuestionController {

    static allowedMethods = [save: 'POST', update: 'POST', delete: 'POST']

    def index = {
        redirect(action: 'list', params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [checkboxQuestionInstanceList: CheckboxQuestion.list(params), checkboxQuestionInstanceTotal: CheckboxQuestion.count()]
    }

    def create = {
        def checkboxQuestionInstance = new CheckboxQuestion()
        checkboxQuestionInstance.properties = params
        return [checkboxQuestionInstance: checkboxQuestionInstance]
    }

    def save = {
        params.choices = params.choices.findAll { it.length() > 0 }
        def checkboxQuestionInstance = new CheckboxQuestion(params)
        if (checkboxQuestionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'checkboxQuestion.label', default: 'CheckboxQuestion'), checkboxQuestionInstance.id])}"
            redirect(action: 'show', id: checkboxQuestionInstance.id)
        }
        else {
            render(view: 'create', model: [checkboxQuestionInstance: checkboxQuestionInstance])
        }
    }

    def show = {
        def checkboxQuestionInstance = CheckboxQuestion.get(params.id)
        if (!checkboxQuestionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'checkboxQuestion.label', default: 'CheckboxQuestion'), params.id])}"
            redirect(action: 'list')
        }
        else {
            [checkboxQuestionInstance: checkboxQuestionInstance]
        }
    }

    def edit = {
        def checkboxQuestionInstance = CheckboxQuestion.get(params.id)
        if (!checkboxQuestionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'checkboxQuestion.label', default: 'CheckboxQuestion'), params.id])}"
            redirect(action: 'list')
        }
        else {
            return [checkboxQuestionInstance: checkboxQuestionInstance]
        }
    }

    def update = {
        def checkboxQuestionInstance = CheckboxQuestion.get(params.id)
        if (checkboxQuestionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (checkboxQuestionInstance.version > version) {
                    
                    checkboxQuestionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure', [message(code: 'checkboxQuestion.label', default: 'CheckboxQuestion')] as Object[], 'Another user has updated this CheckboxQuestion while you were editing')
                    render(view: 'edit', model: [checkboxQuestionInstance: checkboxQuestionInstance])
                    return
                }
            }
            checkboxQuestionInstance.properties = params
            if (!checkboxQuestionInstance.hasErrors() && checkboxQuestionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'checkboxQuestion.label', default: 'CheckboxQuestion'), checkboxQuestionInstance.id])}"
                redirect(action: 'show', id: checkboxQuestionInstance.id)
            }
            else {
                render(view: 'edit', model: [checkboxQuestionInstance: checkboxQuestionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'checkboxQuestion.label', default: 'CheckboxQuestion'), params.id])}"
            redirect(action: 'list')
        }
    }

    def delete = {
        def checkboxQuestionInstance = CheckboxQuestion.get(params.id)
        if (checkboxQuestionInstance) {
            try {
                checkboxQuestionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'checkboxQuestion.label', default: 'CheckboxQuestion'), params.id])}"
                redirect(action: 'list')
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'checkboxQuestion.label', default: 'CheckboxQuestion'), params.id])}"
                redirect(action: 'show', id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'checkboxQuestion.label', default: 'CheckboxQuestion'), params.id])}"
            redirect(action: 'list')
        }
    }
}
