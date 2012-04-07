

<%@ page import="survey.Course"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName"
	value="${message(code: 'course.label', default: 'Course')}" />
<title><g:message code="default.create.label"
		args="[entityName]" />
</title>
<g:javascript>
	$(function() {
		$("[name='addCourse']").validate({
			errorPlacement: function(error, element) {
				error.insertAfter(element);  // default jquery.validation action.
			}, submitHandler: function(form) {
				form.submit();
			}
			
		});
	});
</g:javascript>
</head>
<body>
	<div class="nav">
		<span class="menuButton"><a class="home"
			href="${createLink(uri: '/')}"><g:message
					code="default.home.label" />
		</a>
		</span> <span class="menuButton"><g:link class="list" action="list">
				<g:message code="default.list.label" args="[entityName]" />
			</g:link>
		</span>
	</div>
	<div class="body">
		<h1>
			<g:message code="default.create.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message">
				${flash.message}
			</div>
		</g:if>
		<g:hasErrors bean="${courseInstance}">
			<div class="errors">
				<g:renderErrors bean="${courseInstance}" as="list" />
			</div>
		</g:hasErrors>
		<g:form name="addCourse" action="save">
			<div class="dialog">
				<table>
					<tbody>

						<tr class="prop">
							<td valign="top" class="name"><label for="abbreviation"><g:message
										code="course.abbreviation.label" default="Abbreviation" />
							</label></td>
							<td valign="top"
								class="value ${hasErrors(bean: courseInstance, field: 'abbreviation', 'errors')}">
								<label for="abbreviation" class="error" style="display:none;">Please enter the course abbreviation.<br></label>
								<g:textField name="abbreviation" class="required"
									value="${courseInstance?.abbreviation}" /></td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name"><label for="name"><g:message
										code="course.name.label" default="Name" />
							</label></td>
							<td valign="top"
								class="value ${hasErrors(bean: courseInstance, field: 'name', 'errors')}">
								<label for="name" class="error" style="display:none;">Please enter the name of this course.<br></label>
								<g:textField name="name" class="required" value="${courseInstance?.name}" /></td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name"><label for="term"><g:message
										code="course.term.label" default="Term" />
							</label></td>
							<td valign="top"
								class="value ${hasErrors(bean: courseInstance, field: 'term', 'errors')}">
								<g:select name="term"
									from="${courseInstance.constraints.term.inList}"
									value="${courseInstance?.term}"
									valueMessagePrefix="course.term" /></td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name"><label for="year"><g:message
										code="course.year.label" default="Year" />
							</label></td>
							<td valign="top"
								class="value ${hasErrors(bean: courseInstance, field: 'year', 'errors')}">
								<g:select name="year"
									from="${courseInstance.constraints.year.inList}"
									value="${courseInstance?.year}"
									valueMessagePrefix="course.year" /></td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name"><label for="owner"><g:message
										code="course.owner.label" default="Owner" />
							</label></td>
							<td valign="top"
								class="value ${hasErrors(bean: courseInstance, field: 'owner', 'errors')}">
								<g:select name="owner.id" from="${survey.Person.list()}"
									optionKey="id" value="${courseInstance?.owner?.id}" /></td>
						</tr>

					</tbody>
				</table>
			</div>
			<div class="buttons">
				<span class="button"><g:submitButton name="create"
						class="save"
						value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</span>
			</div>
		</g:form>
	</div>
</body>
</html>
