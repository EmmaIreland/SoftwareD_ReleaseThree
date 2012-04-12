package survey

class EnrollmentController {

	def defaultNotFoundMessage = 'default.not.found.message'
    def listString = 'list'
    def editString = 'edit'
    def createString = 'create'
	static post = 'POST'
    def showString = 'show'
	def flush = [flush: true]
	
    static allowedMethods = [save: post, update: post, delete: post]

    def index = {
        redirect(action: listString, params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [enrollmentInstanceList: Enrollment.list(params), enrollmentInstanceTotal: Enrollment.count()]
    }

    def create = {
        def enrollmentInstance = new Enrollment()
        def availableStudents = Person.list()

        if (params.course?.id) {
            def course = Course.get(params.course.id)
            availableStudents = availableStudents - course.enrollments*.person - course.owner
        }

        enrollmentInstance.properties = params

        return [enrollmentInstance: enrollmentInstance, availableStudents: availableStudents.sort {it.name},
			hasAvailableStudents: (availableStudents.size() > 0)]
    }

    def save = {
        def personInstance
        if (params?.name != null) {
            personInstance = new Person(params)
            personInstance.save(flush)
        }

        def enrollmentInstance = personInstance ? new Enrollment(person: personInstance,
			course: Course.get(params.course.id)) : new Enrollment(params)
        if (enrollmentInstance.save(flush)) {
            redirect(controller: 'enrollment', action: createString,
				 params:['course.id': enrollmentInstance.course.id])
        }
        else {
            enrollmentInstance.errors.rejectValue('person', 'enrollment.person.unique')
            render(view: createString, model: [enrollmentInstance: enrollmentInstance])
        }
    }

    def show = {
        def enrollmentInstance = Enrollment.get(params.id)
        if (enrollmentInstance) {
			[enrollmentInstance: enrollmentInstance]
        }
        else {
			flash.message = makeMessage(defaultNotFoundMessage, params.id)
			redirect(action: listString)
        }
    }

    def edit = {
        def enrollmentInstance = Enrollment.get(params.id)
        if (enrollmentInstance) {
			return [enrollmentInstance: enrollmentInstance]
        }
        else {
			flash.message = makeMessage(defaultNotFoundMessage, params.id)
			redirect(action: listString)
        }
    }

    def update = {
        def enrollmentInstance = Enrollment.get(params.id)
        if (enrollmentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (enrollmentInstance.version > version) {

                    enrollmentInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
						 [message(code: 'enrollment.label', default: 'Enrollment')] as Object[],
						  'Another user has updated this Enrollment while you were editing')
                    render(view: editString, model: [enrollmentInstance: enrollmentInstance])
                    return
                }
            }
            enrollmentInstance.properties = params
            if (!enrollmentInstance.hasErrors() && enrollmentInstance.save(flush)) {
                flash.message = makeMessage('default.updated.message', enrollmentInstance.id)
                redirect(action: showString, id: enrollmentInstance.id)
            }
            else {
                render(view: editString, model: [enrollmentInstance: enrollmentInstance])
            }
        }
        else {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
    }

    def delete = {
        def enrollmentInstance = Enrollment.get(params.id)
        enrollmentInstance.delete(flush)
        render('Success')
    }
    
    def deleteByPerson = {
        def course = Course.get(params.courseId)
        def person = Person.get(params.personId)
        Enrollment.findByCourseAndPerson(course, person).delete(flush)
    }

    private makeMessage(code, enrollmentId) {
        return "${message(code: code, args: [enrollmentLabel(), enrollmentId])}"
    }

    private enrollmentLabel() {
        message(code: 'enrollment.label', default: 'Enrollment')
    }
}
