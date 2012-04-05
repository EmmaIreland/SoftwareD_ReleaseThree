package survey

class EnrollmentController {

    static post = 'POST'
    def listString = 'list'
    def editString = 'edit'
    def createString = 'create'
    def showString = 'show'
    def defaultNotFoundMessage = 'default.not.found.message'

    static allowedMethods = [save: 'POST', update: 'POST', delete: 'POST']

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

        return [enrollmentInstance: enrollmentInstance, availableStudents: availableStudents.sort {it.name}, hasAvailableStudents: (availableStudents.size() > 0)]
    }

    def save = {
        def personInstance
        if (params?.name != null) {
            personInstance = new Person(params)
            personInstance.save(flush: true)
        }

        def enrollmentInstance = personInstance ? new Enrollment(person: personInstance, course: Course.get(params.course.id)) : new Enrollment(params)
        if (enrollmentInstance.save(flush: true)) {
            redirect(controller: 'enrollment', action: createString, params:['course.id': enrollmentInstance.course.id])
        }
        else {
            enrollmentInstance.errors.rejectValue('person', 'enrollment.person.unique')
            render(view: createString, model: [enrollmentInstance: enrollmentInstance])
        }
    }

    def show = {
        def enrollmentInstance = Enrollment.get(params.id)
        if (!enrollmentInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
        else {
            [enrollmentInstance: enrollmentInstance]
        }
    }

    def edit = {
        def enrollmentInstance = Enrollment.get(params.id)
        if (!enrollmentInstance) {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
        else {
            return [enrollmentInstance: enrollmentInstance]
        }
    }

    def update = {
        def enrollmentInstance = Enrollment.get(params.id)
        if (enrollmentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (enrollmentInstance.version > version) {

                    enrollmentInstance.errors.rejectValue('version', 'default.optimistic.locking.failure', [message(code: 'enrollment.label', default: 'Enrollment')] as Object[], 'Another user has updated this Enrollment while you were editing')
                    render(view: editString, model: [enrollmentInstance: enrollmentInstance])
                    return
                }
            }
            enrollmentInstance.properties = params
            if (!enrollmentInstance.hasErrors() && enrollmentInstance.save(flush: true)) {
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
        enrollmentInstance.delete(flush: true)
        render('Success')
    }

    private makeMessage(code, enrollmentId) {
        return "${message(code: code, args: [enrollmentLabel(), enrollmentId])}"
    }

    private enrollmentLabel() {
        message(code: 'enrollment.label', default: 'Enrollment')
    }
}
