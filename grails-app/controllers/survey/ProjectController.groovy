package survey

class ProjectController {

	def flush = [flush: true]
	def editString = 'edit'
    static post = 'POST'
    def listString = 'list'
    def createString = 'create'
    def showString = 'show'
    def defaultNotFoundMessage = 'default.not.found.message'
	
    static allowedMethods = [save: post, update: post, delete: post]

    def index = {
	redirect(action: listString, params: params)
    }

    def list = {
	params.max = Math.min(params.max ? params.int('max') : 10, 100)
	[projectInstanceList: Project.list(params), projectInstanceTotal: Project.count()]
    }

    def create = {
	def projectInstance = new Project()
	projectInstance.properties = params
	return [projectInstance: projectInstance]
    }

    def save = {
	def projectInstance = new Project(params)
	if (projectInstance.save(flush)) {
	    flash.message = makeMessage('default.created.message', params.name)
	    redirect(action: showString, id: projectInstance.id)
	}
	else {
	    render(view: createString, model: [projectInstance: projectInstance])
	}
    }

    def show = {
	def projectInstance = Project.get(params.id)
	def numUnassignedStudents = getUnassignedStudents(projectInstance.course, projectInstance).size()
	if (projectInstance) {
            [projectInstance: projectInstance, numUnassignedStudents: numUnassignedStudents]
	}
	else {
            flash.message =  makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
	}
    }

    def edit = {
	def projectInstance = Project.get(params.id)
	if (projectInstance) {
            return [projectInstance: projectInstance]
	}
	else {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
	}
    }

    def update = {
	def projectInstance = Project.get(params.id)
	if (projectInstance) {
	    if (params.version) {
		def version = params.version.toLong()
		if (projectInstance.version > version) {

		    projectInstance.errors.rejectValue('version', 'default.optimistic.locking.failure', [message(code: 'project.label', default: 'Project')] as Object[], 'Another user has updated this Project while you were editing')
		    render(view: editString, model: [projectInstance: projectInstance])
		    return
		}
	    }
	    projectInstance.properties = params
	    if (!projectInstance.hasErrors() && projectInstance.save(flush)) {
		flash.message = makeMessage('default.updated.message', projectInstance.name)
		redirect(action: showString, id: projectInstance.id)
	    }
	    else {
		render(view: editString, model: [projectInstance: projectInstance])
	    }
	}
	else {
	    flash.message = makeMessage(defaultNotFoundMessage, params.id)
	    redirect(action: listString)
	}
    }

    def delete = {
	def projectInstance = Project.get(params.id)
	if (projectInstance) {
	    try {
		projectInstance.delete(flush)
		flash.message = makeMessage('default.deleted.message', projectInstance.name)
		redirect(action: listString)
	    }
	    catch (org.springframework.dao.DataIntegrityViolationException e) {
		flash.message = makeMessage('default.not.deleted.message', projectInstance.name)
		redirect(action: showString, id: params.id)
	    }
	}
	else {
	    flash.message = makeMessage(defaultNotFoundMessage, params.id)
	    redirect(action: listString)
	}
    }

    private makeMessage(code, projectId) {
	return "${message(code: code, args: [projectLabel(), projectId])}"
    }

    private projectLabel() {
	message(code: 'project.label', default: 'Project')
    }
    
    private getUnassignedStudents(course, project) {
	def allStudents = course.enrollments*.person
	def assignedStudents = []
	project.teams.each() { team -> assignedStudents += team.memberships*.member}
	allStudents - assignedStudents
    }
}
