package survey

class CourseController {

    static post = 'POST'
    def listString = 'list'
    def editString = 'edit'
    def createString = 'create'
    def showString = 'show'
    static allowedMethods = [save: post, update: post, delete: post]
    static listMap = [action: 'list']

    def index = {
        redirect(action: listString, params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [courseInstanceList: Course.list(params), courseInstanceTotal: Course.count()]
    }

    def create = {
        def courseInstance = new Course()
        courseInstance.properties = params
        return courseMap(courseInstance)
    }

    def save = {
        def courseInstance = new Course(params)
        if (courseInstance.save(flush: true)) {
            flash.message = makeMessage('default.created.message', params.name)
            redirect(action: showString, id: courseInstance.id)
        }
        else {
            render(view: createString, model: courseMap(courseInstance))
        }
    }

    def show = {
        def courseInstance = Course.get(params.id)
        if (!courseInstance) {
            flash.message = makeMessage('default.not.found.message', params.name)
            redirect(listMap)
        }
        else {
            if (params.addstudent) {
                [courseInstance: courseInstance, addStudent: true]
            }
            else {
                courseMap(courseInstance)
            }
        }
    }

    def edit = {
        def courseInstance = Course.get(params.id)
        if (!courseInstance) {
            flash.message = makeMessage('default.not.found.message', params.name)
            redirect(listMap)
        }
        else {
            return courseMap(courseInstance)
        }
    }

    def update = {
        def courseInstance = Course.get(params.id)
        if (courseInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (courseInstance.version > version) {

                    courseInstance.errors.rejectValue('version', 'default.optimistic.locking.failure', [label] as Object[], 'Another user has updated this Course while you were editing')
                    render(view: editString, model: courseMap(courseInstance))
                    return
                }
            }
            courseInstance.properties = params
            if (!courseInstance.hasErrors() && courseInstance.save(flush: true)) {
                flash.message = makeMessage('default.updated.message', params.name)
                redirect(action: showString, id: courseInstance.id)
            }
            else {
                render(view: editString, model: courseMap(courseInstance))
            }
        }
        else {
            flash.message = makeMessage('default.not.found.message', params.name)
            redirect(listMap)
        }
    }

    def delete = {
        def courseInstance = Course.get(params.id)
        if (courseInstance) {
            try {
                courseInstance.delete(flush: true)
                flash.message = 'Course Deleted'
                redirect(listMap)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = makeMessage('default.not.deleted.message', params.name)
                redirect(action: showString, id: params.id)
            }
        }
        else {
            flash.message = makeMessage('default.not.found.message', params.name)
            redirect(listMap)
        }
    }

    private makeMessage(code, instanceId) {
        "${message(code: code, args: [label, instanceId])}"
    }

    private getLabel() {
        message(code: 'course.label', default: '')
    }

    private courseMap(courseInstance) {
        [courseInstance: courseInstance]
    }
}
