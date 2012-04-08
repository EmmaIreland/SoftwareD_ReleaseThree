<%@ page import="survey.Course" %>
<html>
    <head>
        <title>Cevin's Cyqulists</title>
        <meta name="layout" content="main" />
    </head>
    <body>
 
        <div class="body">
            <h1>Welcome to Cevin's Cyqulists' Survey Tool</h1>
            <p>Below are links to story implementations.  Note: This is the development home page only.</p>

			<div style="background: #ddd; border-radius: 6px; margin-top: 8px; padding: 8px;">
				<h2>Story Links</h2>
				<ul id="story_list" style="margin-top: 6px;">
					<style type="text/css">
						#story_list li {
							margin-top: 3px;
						}
					</style>
					<li><g:link controller="course" action="create">Make course</g:link></li>
					<li>View a list of students in a class:
						<ul>
							<g:each in="${Course.list()}" var="course">
							<li><g:link controller="course" action="show" id="${course.id}">${course.name}</g:link></li>
							</g:each>
						</ul>
					</li>
					<li>Add a student to a class:
						<ul>
							<g:each in="${Course.list()}" var="course">
							<li><g:link controller="enrollment" action="create" params="${['course.id': course.id]}">${course.name}</g:link></li>
							</g:each>
						</ul>
					</li>
					<li><g:link controller="project" action="create">Create a project within a class</g:link></li>
					<li><g:link controller="survey" action="create">Create a survey for project</g:link></li>
				</ul>
			</div>
    </body>
</html>
