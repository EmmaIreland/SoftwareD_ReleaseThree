

<%@ page import="survey.questions.ShortTextQuestion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'shortTextQuestion.label', default: 'ShortTextQuestion')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${shortTextQuestionInstance}">
            <div class="errors">
                <g:renderErrors bean="${shortTextQuestionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${shortTextQuestionInstance?.id}" />
                <g:hiddenField name="version" value="${shortTextQuestionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="prompt"><g:message code="shortTextQuestion.prompt.label" default="Prompt" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shortTextQuestionInstance, field: 'prompt', 'errors')}">
                                    <g:textField name="prompt" value="${shortTextQuestionInstance?.prompt}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="responses"><g:message code="shortTextQuestion.responses.label" default="Responses" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shortTextQuestionInstance, field: 'responses', 'errors')}">
                                    
<ul>
<g:each in="${shortTextQuestionInstance?.responses?}" var="r">
    <li><g:link controller="answer" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="answer" action="create" params="['shortTextQuestion.id': shortTextQuestionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'answer.label', default: 'Answer')])}</g:link>

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
