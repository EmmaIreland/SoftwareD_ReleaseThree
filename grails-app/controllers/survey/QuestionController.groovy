package survey

class QuestionController {
	
	def editString = 'edit'
	def showString = 'show'
	static post = 'POST'
	def listString = 'list'
	def createString = 'create'
	def defaultNotFoundMessage = 'default.not.found.message'
	def flush = [flush: true]	

    static allowedMethods = [save: post, update: post, delete: post]

    def index = {
        redirect(action: listString, params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [questionInstanceList: Question.list(params), questionInstanceTotal: Question.count()]
    }

    def show = {
        def questionInstance = Question.get(params.id)
        if (!questionInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
        else {
            [questionInstance: questionInstance]
        }
    }

    def edit = {
        def questionInstance = Question.get(params.id)
        if (!questionInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
        else {
            return [questionInstance: questionInstance]
        }
    }

    def update = {
        def questionInstance = Question.get(params.id)
        if (questionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (questionInstance.version > version) {
                    
                    questionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure', [message(code: 'question.label', default: 'Question')] as Object[], 'Another user has updated this Question while you were editing')
                    render(view: editString, model: [questionInstance: questionInstance])
                    return
                }
            }
            questionInstance.properties = params
            if (!questionInstance.hasErrors() && questionInstance.save(flush)) {
                flash.message = makeMessage('default.updated.message', questionInstance.id)
                redirect(action: showString, id: questionInstance.id)
            }
            else {
                render(view: editString, model: [questionInstance: questionInstance])
            }
        }
        else {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
    }

    
    def delete = {
        def questionInstance = Question.get(params.id)
        if (questionInstance) {
            def allSurveys = Survey.findAll()
            
            for ( i in allSurveys ) {
                if (i.questions.contains(questionInstance)) {
                    i.questions.remove(questionInstance)
                    break
                }
            }
            questionInstance.delete(flush)
        }
        render('Success.')
    }
	
	private makeMessage(code, questionId) {
		return "${message(code: code, args: [questionLabel(), questionId])}"
	}
 
	private questionLabel() {
		message(code: 'question.label', default: 'Question')
	}
	
}
