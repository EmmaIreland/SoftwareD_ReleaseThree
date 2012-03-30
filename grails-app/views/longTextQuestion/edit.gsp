

<%@ page import="survey.questions.LongTextQuestion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'longTextQuestion.label', default: 'LongTextQuestion')}" />
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
            <g:hasErrors bean="${longTextQuestionInstance}">
            <div class="errors">
                <g:renderErrors bean="${longTextQuestionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${longTextQuestionInstance?.id}" />
                <g:hiddenField name="version" value="${longTextQuestionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="prompt"><g:message code="longTextQuestion.prompt.label" default="Prompt" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: longTextQuestionInstance, field: 'prompt', 'errors')}">
                                    <g:textField name="prompt" value="${longTextQuestionInstance?.prompt}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="responses"><g:message code="longTextQuestion.responses.label" default="Responses" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: longTextQuestionInstance, field: 'responses', 'errors')}">
                                    
<ul>
<g:each in="${longTextQuestionInstance?.responses?}" var="r">
    <li><g:link controller="answer" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="answer" action="create" params="['longTextQuestion.id': longTextQuestionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'answer.label', default: 'Answer')])}</g:link>

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
