<%@ page import="survey.Survey"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName"
	value="${message(code: 'survey.label', default: 'Survey')}" />
<title><g:message code="default.show.label" args="[entityName]" />
</title>
<g:javascript library="jquery" plugin="jquery" />
<script type="text/javascript">

		function deleteQuestion(image) {
			if (confirm("Are you sure you want to remove this question from the survey?")) {
				questionId = $(image).parent().attr("id");
				jQuery.post("../../question/delete/" , {id: questionId});
				$(image).parent().remove();
			}
		}
        
        </script>
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
		</span> <span class="menuButton"><g:link class="create"
				action="create">
				<g:message code="default.new.label" args="[entityName]" />
			</g:link>
		</span>
	</div>
	<div class="body">
		<h1>
			<g:message code="default.preview.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message">
				${flash.message}
			</div>
		</g:if>
		<div class="dialog">
			<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name"><g:message
								code="survey.title.label" default="Title" />
						</td>

						<td valign="top" class="value">
							${fieldValue(bean: surveyInstance, field: "title")}
						</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message
								code="survey.dueDate.label" default="Due Date" />
						</td>

						<td valign="top" class="value"><g:formatDate
								date="${surveyInstance?.dueDate}" format="MMMMM d, yyyy" />
						</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message
								code="survey.project.label" default="Project" />
						</td>

						<td valign="top" class="value"><g:link controller="project"
								action="show" id="${surveyInstance?.project?.id}">
								${surveyInstance?.project?.encodeAsHTML()}
							</g:link>
						</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message
								code="survey.question.label" default="Questions" />
						</td>

						<td valign="top" class="value">
							<g:each in="${surveyInstance.questions}" var="question">
								<g:render template="${question.getTemplateName()}"
									bean="${question}" var="question" />
							</g:each>
						</td>

					</tr>

				</tbody>
			</table>
		</div>
		<div class="buttons">
			<g:form action="show" id="${surveyInstance?.id }">
				<g:hiddenField name="id" value="${surveyInstance?.id}" />
				<span class="button"><g:actionSubmit class="show"
						action="show"
						value="${message(code: 'default.button.show.label', default: 'Back to survey edit')}"/>
				</span>
			</g:form>
		</div>
	</div>
</body>
</html>
