<%@ page import="survey.Survey" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'survey.label', default: 'Survey')}" />
        <g:javascript src="newquestion.js" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'new_question.css')}" />
    </head>
    <body>
    	<img src="../../images/delete.png" class="deleteIcon" onclick="deleteQuestion(this)" style="display:none" />
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>${surveyInstance.title}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="survey.dueDate.label" default="Due Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${surveyInstance?.dueDate}" format="MMMMM d, yyyy" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="survey.project.label" default="Project" /></td>
                            
                            <td valign="top" class="value"><g:link controller="project" action="show" id="${surveyInstance?.project?.id}">${surveyInstance?.project?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="survey.question.label" default="Questions" /></td>
                            
                            <td valign="top" class="value"><trinkets:addButton id="addButton" name="addButton" onclick="showQuestionFields();" width="120px">Add new question</trinkets:addButton>
                            <form id="newQuestionForm">
                            	<input type="hidden" value="${surveyInstance?.id}" name="surveyid" />
	                            <table id="newQuestionFields" style="display:none">
	                            <tbody>
		                            <tr style="display:none"><td colspan="2"><div class="errors"></div></td></tr>
		                            <tr><td>Type</td><td> <select name="type" id="typeDropdown" onchange="updateQuestionType(this);">
		                                    <option value="existing">Add existing question</option>
		                                    <option value="checkbox">New checkbox question</option>
		                                    <option value="multipleChoice">New multiple choice question</option>
		                                    <option value="shortResponse">New short text question</option>
		                                    <option value="longResponse">New long text question</option>
		                            </select></td></tr>
		                            <tr class="prop typeRow" id="existingRow">
		                                <td valign="top" class="name">
		                                    <label for="name"><g:message code="question.type.label" default="Select a Question" /></label>
		                                </td>
		                                <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'name', 'errors')}">
		                                    <g:select style="max-width: 240px" name="questionid" from="${existingQuestions.unique()}" optionKey="id"/>
		                                </td>
		                            </tr>
		                            
		                              <tr class="prop typeRow checkboxRow">
		                                <td valign="top" class="name">
		                                    <label for="prompt"><g:message code="checkboxQuestion.prompt.label" default="Prompt" /></label>
		                                </td>
		                                <td valign="top" class="value ${hasErrors(bean: checkboxQuestionInstance, field: 'prompt', 'errors')}">
		                                    <g:textField name="cbPrompt" value="${checkboxQuestionInstance?.prompt}" />
		                                </td>
		                            </tr>
		                            
		                            <tr class="prop typeRow checkboxRow">
		                                <td valign="top" class="name">
		                                    <label for="choices"><g:message code="checkboxQuestion.choices.label" default="Choices" /></label>
		                                </td>
		                                <td valign="top" class="value ${hasErrors(bean: checkboxQuestionInstance, field: 'prompt', 'errors')}">
		                                    <g:textField name="cbChoices" class="addOnChange" id="choice1" />
		                                </td>
		                            </tr>
		                            
		                             <tr class="prop typeRow multipleRow">
		                                <td valign="top" class="name">
		                                    <label for="prompt"><g:message code="multipleChoiceQuestion.prompt.label" default="Prompt" /></label>
		                                </td>
		                                <td valign="top" class="value ${hasErrors(bean: multipleChoiceQuestion, field: 'prompt', 'errors')}">
		                                    <g:textField name="mcPrompt" value="${multipleChoiceQuestion?.prompt}" />
		                                </td>
		                            </tr>
		                            
		                            <tr class="prop typeRow multipleRow">
		                                <td valign="top" class="name">
		                                    <label for="choices"><g:message code="multipleChoiceQuestion.choices.label" default="Choices" /></label>
		                                </td>
		                                <td valign="top" class="value ${hasErrors(bean: multipleChoiceQuestion, field: 'prompt', 'errors')}">
		                                    <g:textField name="mcChoices" class="addOnChange" id="choice1" />
		                                </td>
		                            </tr>
		                            
		                             <tr class="prop typeRow shortRow">
		                                <td valign="top" class="name">
		                                    <label for="prompt"><g:message code="shortTextQuestion.prompt.label" default="Prompt" /></label>
		                                </td>
		                                <td valign="top" class="value ${hasErrors(bean: shortTextQuestion, field: 'prompt', 'errors')}">
		                                    <g:textField name="stPrompt" value="${shortTextQuestion?.prompt}" />
		                                </td>
		                            </tr>
		                            
		                             <tr class="prop typeRow longRow">
		                                <td valign="top" class="name">
		                                    <label for="prompt"><g:message code="longTextQuestion.prompt.label" default="Prompt" /></label>
		                                </td>
		                                <td valign="top" class="value ${hasErrors(bean: longTextQuestion, field: 'prompt', 'errors')}">
		                                    <g:textField name="ltPrompt" value="${longTextQuestion?.prompt}" />
		                                </td>
		                            </tr>
		                            <tr><td><input id="addQuestionButton" type="button" value="Add Question" onclick="addQuestion()"></td><td></td></tr>
	                            </tbody>
	                            </table>
                            </form>
                            
                            
                            
                            <g:each in="${surveyInstance.questions}" var="question">
                            	<span id="${question.id}">
                            		<img src="../../images/delete.png" class="deleteIcon" onclick="deleteQuestion(this)" />
                            			<g:link controller="question" action="show" id="${question.id}"> ${question}</g:link>
                          		</span>
                          		<br/>
                          	</g:each></td>
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${surveyInstance?.id}" />
                    <g:hiddenField name="personid" value="1" /> <!--  // TODO Change this to use the actual user ID -->
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                    <span class="button"><g:actionSubmit class="preview" action="preview" value="${message(code: 'default.button.preview.label', default: 'Preview')}"/></span>
                    <span class="button"><g:actionSubmit class="take" action="take" value="${message(code: 'default.button.take.label', default: 'Take')}"/></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
