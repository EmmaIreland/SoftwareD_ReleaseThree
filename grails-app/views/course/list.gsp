
<%@ page import="survey.Course"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName"
	value="${message(code: 'course.label', default: 'Course')}" />
<title>Courses</title>

<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>

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

		<div id="tabs">

			<ul>
				<g:each in="${courseInstanceList}" var="course">
					<li><a href="#${course.id}"> ${course.abbreviation} </a></li>

				</g:each>
			</ul>

			<g:each in="${courseInstanceList}" var="course">


				<div id="${course.id}">

					<g:link action="show" id="${course.id}">
						<h2>${fieldValue(bean: course, field: "abbreviation")}:${fieldValue(bean: course, field: "name")}</h2>
					</g:link>
					<h3>${fieldValue(bean: course, field: "term")} ${course.year} </h3> 
					<br>
					<b>Course Instructor: ${course?.owner}</b>
					<br><br>
					

					<h2>Enrolled Students:</h2>
					<div style="text-align: left;" class="value">
						<g:link	controller="enrollment" action="create"	params="${['course.id': course.id] }">Add a student</g:link>
							<g:each in="${course.enrollments}" var="p">
								<ul><ul>
									<li><g:link controller="person" action="show" id="${p.person.id}">	${p.person}	</g:link></li>
								</ul></ul>
							</g:each>
					</div>
					
					<br></br>
					<h3>Projects:</h3>
					<g:link controller="project" action="create" params="${['course.id': course.id]}"><b>Create new Project</b></g:link>
					<g:each in="${course.projects}" var="p">
						<ul><ul>
							<li><g:link controller="project" action="show" id="${p.id}">${p?.name?.encodeAsHTML()}</g:link></li>						
						</ul></ul>
					</g:each>
					
					<br></br>
					<div style="text-align: right;">
						<span class="button"><g:link class="edit" controller="course" action="edit" id="${course.id}"><h2>${'Edit Course'}</h2>
							</g:link>
						</span>
					</div>
				</div>
			</g:each>
		</div>
		
		<br/>
		<trinkets:collapsibleDiv title="Lorem Ipsum">
			Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut volutpat felis sit amet urna fringilla lobortis. Phasellus et nibh velit. Sed rutrum egestas est. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Praesent interdum molestie pulvinar. Pellentesque lobortis lectus nibh. Proin non libero bibendum tellus tempus luctus. Nunc mollis imperdiet vulputate. Morbi purus dolor, aliquet vitae rhoncus a, vestibulum ac arcu. Quisque in nulla eget nisi posuere aliquam eget ultrices enim. Pellentesque ultricies, lacus at auctor cursus, arcu enim lobortis leo, eget mattis dolor mi at arcu. Aliquam erat volutpat. Ut commodo rhoncus eros, at.
		</trinkets:collapsibleDiv>
		<trinkets:collapsibleDiv title="Some Div">Some Text</trinkets:collapsibleDiv>
		<trinkets:collapsibleDiv title="Some Div">Some Text</trinkets:collapsibleDiv>

	</div>

</body>
</html>
