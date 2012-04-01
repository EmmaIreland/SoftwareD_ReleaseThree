package survey.questions

class ShortTextQuestionController {

    static allowedMethods = [save: 'POST', update: 'POST', delete: 'POST']

    def index = {
        redirect(action: 'list', params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [shortTextQuestionInstanceList: ShortTextQuestion.list(params), shortTextQuestionInstanceTotal: ShortTextQuestion.count()]
    }

    def create = {
        def shortTextQuestionInstance = new ShortTextQuestion()
        shortTextQuestionInstance.properties = params
        return [shortTextQuestionInstance: shortTextQuestionInstance]
    }

    def save = {
        def shortTextQuestionInstance = new ShortTextQuestion(params)
        if (shortTextQuestionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'shortTextQuestion.label', default: 'ShortTextQuestion'), shortTextQuestionInstance.id])}"
            redirect(action: 'show', id: shortTextQuestionInstance.id)
        }
        else {
            render(view: 'create', model: [shortTextQuestionInstance: shortTextQuestionInstance])
        }
    }

    def show = {
        def shortTextQuestionInstance = ShortTextQuestion.get(params.id)
        if (!shortTextQuestionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shortTextQuestion.label', default: 'ShortTextQuestion'), params.id])}"
            redirect(action: 'list')
        }
        else {
            [shortTextQuestionInstance: shortTextQuestionInstance]
        }
    }

    def edit = {
        def shortTextQuestionInstance = ShortTextQuestion.get(params.id)
        if (!shortTextQuestionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shortTextQuestion.label', default: 'ShortTextQuestion'), params.id])}"
            redirect(action: 'list')
        }
        else {
            return [shortTextQuestionInstance: shortTextQuestionInstance]
        }
    }

    def update = {
        def shortTextQuestionInstance = ShortTextQuestion.get(params.id)
        if (shortTextQuestionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (shortTextQuestionInstance.version > version) {
                    
                    shortTextQuestionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure', [message(code: 'shortTextQuestion.label', default: 'ShortTextQuestion')] as Object[], 'Another user has updated this ShortTextQuestion while you were editing')
                    render(view: 'edit', model: [shortTextQuestionInstance: shortTextQuestionInstance])
                    return
                }
            }
            shortTextQuestionInstance.properties = params
            if (!shortTextQuestionInstance.hasErrors() && shortTextQuestionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'shortTextQuestion.label', default: 'ShortTextQuestion'), shortTextQuestionInstance.id])}"
                redirect(action: 'show', id: shortTextQuestionInstance.id)
            }
            else {
                render(view: 'edit', model: [shortTextQuestionInstance: shortTextQuestionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shortTextQuestion.label', default: 'ShortTextQuestion'), params.id])}"
            redirect(action: 'list')
        }
    }

    def delete = {
        def shortTextQuestionInstance = ShortTextQuestion.get(params.id)
        if (shortTextQuestionInstance) {
            try {
                shortTextQuestionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'shortTextQuestion.label', default: 'ShortTextQuestion'), params.id])}"
                redirect(action: 'list')
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'shortTextQuestion.label', default: 'ShortTextQuestion'), params.id])}"
                redirect(action: 'show', id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shortTextQuestion.label', default: 'ShortTextQuestion'), params.id])}"
            redirect(action: 'list')
        }
    }
}
