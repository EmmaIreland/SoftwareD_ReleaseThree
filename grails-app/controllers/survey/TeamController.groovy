package survey


class TeamController {
	
	static post = 'POST'
	def listString = 'list'
	def editString = 'edit'
	def createString = 'create'
	def showString = 'show'
	def defaultNotFoundMessage = 'default.not.found.message'
	def flush = [flush: true]
	def failOnError = [failOnError: true]

    static allowedMethods = [save: post, update: post, delete: post, changeMember: post]

    static int genericGroupNum = 1
    
    def index = {
        redirect(action: listString, params: params)
    }

    def list = {
        def projectInstance = Project.get(params.project)
        if (projectInstance) {
            def courseInstance = projectInstance.course
            def courseHasStudents = courseInstance.enrollments.size() > 0
            def projectHasTeams = projectInstance.teams.size() > 0
            def unassignedStudents = getUnassignedStudents(courseInstance, projectInstance)
             render(view: 'manageteams',
                    model: [courseInstance: courseInstance,
                            projectInstance: projectInstance,
                            unassignedStudents: unassignedStudents,
                            courseHasStudents: courseHasStudents,
                            projectHasTeams: projectHasTeams])
        }
        else {
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            [teamInstanceList: Team.list(params), teamInstanceTotal: Team.count()]
        }
    }

    private getUnassignedStudents(course, project) {
        def allStudents = course.enrollments*.person
        def assignedStudents = []
        project.teams.each() { team -> assignedStudents += team.memberships*.member}
        allStudents - assignedStudents
    }
    
    def create = {
        def teamInstance = new Team()
        teamInstance.properties = params
        return [teamInstance: teamInstance]
    }

    def save = {
        def teamInstance = new Team(params)
        if (teamInstance.save(flush)) {
            redirect(action: listString, params: [project: teamInstance.project.id])
        }
        else {
            render(view: createString, model: [teamInstance: teamInstance])
        }
    }
    
    def addmany = {
        def project = Project.get(params.project?.id)
        if (params.num_groups?.isNumber()) {
            def numGroups = Integer.parseInt(params.num_groups)
            def newGroups = []
            numGroups.times() {
                newGroups << new Team(name: "Group ${genericGroupNum++}", project: project).save(flush)
            }
            
            if (params.random == 'on') {
                Random rand = new Random()
                def unassignedStudents = getUnassignedStudents(project.course, project)
                int numUnassignedStudents = unassignedStudents.size()
                
                int smallGroupSize = numUnassignedStudents / numGroups
                int numLargeGroups = numUnassignedStudents % numGroups
                
                newGroups.eachWithIndex() { group, index ->
                    (smallGroupSize + (index < numLargeGroups ? 1 : 0)).times() {
                        def person = unassignedStudents.remove(rand.nextInt(unassignedStudents.size()))
                        new Membership(team: group, member: person).save(flush)
                    }
                }
            }
        }
        redirect(action: listString, params: [project: project.id])
    }

    def show = {
        def teamInstance = Team.get(params.id)
        if (teamInstance) {
            [teamInstance: teamInstance]
        }
        else {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
    }
   
    def edit = {
        def teamInstance = Team.get(params.id)
        if (teamInstance) {
            return [teamInstance: teamInstance]
        }
        else {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
    }

    def update = {
        def teamInstance = Team.get(params.id)
        if (teamInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (teamInstance.version > version) {
                    
                    teamInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
						 [message(code: 'team.label', default: 'Team')] as Object[],
						  'Another user has updated this Team while you were editing')
                    render(view: editString, model: [teamInstance: teamInstance])
                    return
                }
            }
            teamInstance.properties = params
            if (!teamInstance.hasErrors() && teamInstance.save(flush)) {
                flash.message = makeMessage('default.updated.message', teamInstance.name)
                redirect(controller: 'project', action: showString, id: teamInstance.project.id)
            }
            else {
                render(view: editString, model: [teamInstance: teamInstance])
            }
        }
        else {
            flash.message = makeMessage(defaultNotFoundMessage, params.id)
            redirect(action: listString)
        }
    }

    def delete = {
        def teamInstance = Team.get(params.id)
        if (teamInstance) {
                teamInstance.delete(flush)
                render('Success.')
        }
    }
    
    def changeMember = {
        def teamInstance = Team.get(params.g_id)
        def personInstance = Person.get(params.s_id)            
        
        if (params.o_id?.isNumber() && params.o_id != '0') {
            def oldTeam = Team.get(params.o_id)
            def oldMembership = Membership.findByTeamAndMember(oldTeam, personInstance)
            oldMembership.delete(flush, failOnError)
        }
        
        if (teamInstance) { 
            new Membership(team: teamInstance, member: personInstance).save(failOnError)
        }
       
        render('')
    }
	
	private makeMessage(code, teamId) {
		return "${message(code: code, args: [teamLabel(), teamId])}"
	}
 
	private teamLabel() {
		message(code: 'team.label', default: 'Team')
	}
}
