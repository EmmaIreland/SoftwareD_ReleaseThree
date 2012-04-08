
<%@ page import="survey.Course"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName"
	value="${message(code: 'course.label', default: 'Course')}" />
<title>Courses</title>

</head>
<body>
	<div class="nav">
		<span class="menuButton"><a class="home"
			href="${createLink(uri: '/')}"><g:message
					code="default.home.label" /> </a> </span> <span class="menuButton"><g:link
				class="create" action="create">
				<g:message code="default.new.label" args="[entityName]" />
			</g:link> </span>
	</div>

	<div class="body">

		<h1>Courses</h1>
 		<g:if test="${courseInstanceList.size() != 0}">
		<g:each in="${courseInstanceList}" var="course">
			<trinkets:collapsibleDiv title="${course.abbreviation}">

				<div id="${course.id}">
					<br>
					<h2>
					<g:link action="show" id="${course.id}">
							${fieldValue(bean: course, field: "abbreviation")}:${fieldValue(bean: course, field: "name")}
					</g:link>
					</h2>
					<h3>
						${fieldValue(bean: course, field: "term")}
						${course.year}
					</h3>
					<br> <b>Course Instructor: ${course?.owner}
					</b> <br>
					<br>


					<h2>Enrolled Students:</h2>
					<div style="text-align: left;" class="value">
						<g:link controller="enrollment" action="create" params="${['course.id': course.id] }">Add or Remove a student</g:link>
						<g:each in="${course.enrollments}" var="p">
							<ul>
								<ul>
									<li><g:link controller="person" action="show" id="${p.person.id}">
											${p.person}
										</g:link>
									</li>
								</ul>
							</ul>
						</g:each>
					</div>

					<br></br>
					<h3>Projects:</h3>
					<g:link controller="project" action="create" params="${['course.id': course.id]}">
						<b>Create new Project</b>
					</g:link>
					<g:each in="${course.projects}" var="p">
						<ul>
							<ul>
								<li><g:link controller="project" action="show" id="${p.id}">
										${p?.name?.encodeAsHTML()}
									</g:link>
								</li>
							</ul>
						</ul>
					</g:each>

					<br></br>
					<div style="text-align: right;">
						<span class="button"><g:link class="edit" controller="course" action="edit" id="${course.id}">
								<h2>
									${'Edit Course'}
								</h2>
											</g:link> 
						</span>
					</div>
				</div>
			</trinkets:collapsibleDiv>
		</g:each>
		</g:if>
		<g:else>
				<h2><g:link controller="course" action="create" params="${[course: courseInstance]}">No courses made. Click here to create a new course.</g:link></h2>
		</g:else>
	</div>

</body>
</html>
