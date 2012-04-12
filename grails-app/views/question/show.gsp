
<%@ page import="survey.Question" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>${questionInstance.prompt}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                          <tr class="prop">
                            <td valign="top" class="name"><g:message code="question.class" default="Type" /></td>
                            
                            <td valign="top" class="value">${questionInstance.templateName}</td>
                            
                        </tr>
                        <g:if test="${(questionInstance.templateName) == 'Checkbox' | (questionInstance.templateName) == 'MultipleChoice'}"> 
                        
                         <tr class="prop">
                           		<td valign="top" class="name"><g:message code="question.class" default="Choices" /></td>
                           		<td valign="top" class="value">
                           		 	<ul>
                       					<g:each in="${questionInstance.choices}" var="c">
                           		 			<li>${c}</li>
                            
                    					</g:each>
                    				</ul>
                    			</td>
                    		</tr>
                    	</g:if>
                    	
                    	<tr class="prop">
                    		<td valign="top" class="name"><g:message code="question.class" default="Survey" /></td>
                    		
                    		<td valign="top" class="value"><g:link controller="survey" action="show" id="${questionInstance.getSurvey().id}">${questionInstance.getSurvey()}</g:link>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${questionInstance?.id}" />
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
