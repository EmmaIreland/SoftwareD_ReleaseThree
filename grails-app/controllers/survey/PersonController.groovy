package survey

class PersonController {
	
	static def post = 'POST'
	def listString = 'list'
	def editString = 'edit'
	def createString = 'create'
	def showString = 'show'
	def defaultNotFoundMessage = 'default.not.found.message'

    static allowedMethods = [save: post, update: post, delete: post]

    def index = {
        redirect(action: listString, params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [personInstanceList: Person.list(params), personInstanceTotal: Person.count()]
    }

    def create = {
        def personInstance = new Person()
        personInstance.properties = params
        return [personInstance: personInstance]
    }

    def save = {
/*		controller.params.id = 100
		assertEquals controller.request.message, controller.redirectArgs.action*/
        def personInstance = new Person(params)
        if (personInstance.save(flush: true)) {
            flash.message = makeMessage('default.created.message', personInstance.name)
            redirect(action: showString, id: personInstance.id)
        }
        else {
            render(view: createString, model: [personInstance: personInstance])
        }
    }

    def show = {
        def personInstance = Person.get(params.id)
        if (!personInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
        else {
            [personInstance: personInstance]
        }
    }

    def edit = {
        def personInstance = Person.get(params.id)
        if (!personInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
        else {
            return [personInstance: personInstance]
        }
    }

    def update = {
        def personInstance = Person.get(params.id)
        if (personInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (personInstance.version > version) {
                    
                    personInstance.errors.rejectValue('version', 'default.optimistic.locking.failure', [message(code: 'person.label', default: 'Person')] as Object[], 'Another user has updated this Person while you were editing')
                    render(view: editString, model: [personInstance: personInstance])
                    return
                }
            }
            personInstance.properties = params
            if (!personInstance.hasErrors() && personInstance.save(flush: true)) {
                flash.message = makeMessage('default.updated.message', personInstance.toString())
                redirect(action: showString, id: personInstance.id)
            }
            else {
                render(view: editString, model: [personInstance: personInstance])
            }
        }
        else {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
    }

    def delete = {
        def personInstance = Person.get(params.id)
        if (personInstance) {
            try {
                personInstance.delete(flush: true)
                flash.message = makeMessage('default.deleted.message', personInstance.toString())
                redirect(action: listString)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = makeMessage('default.not.deleted.message', personInstance.toString())
                redirect(action: showString, id: params.id)
            }
        }
        else {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
    }
	
	private makeMessage(code, personId) {
		return "${message(code: code, args: [personLabel(), personId])}"
	}
 
	private personLabel() {
		message(code: 'person.label', default: 'Person')
	}
}
