

<%@ page import="survey.Project" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
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
            <g:hasErrors bean="${projectInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${projectInstance?.id}" />
                <g:hiddenField name="version" value="${projectInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="project.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${projectInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="course"><g:message code="project.course.label" default="Course" /></label>
                                </td>
                            <td valign="top" class="value"><g:link controller="course" action="show" id="${projectInstance?.course?.id}">${projectInstance?.course?.encodeAsHTML()}</g:link></td>
                                
                                </td>
                            </tr>
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="project.dueDate.label" default="Due Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectInstance, field: 'dueDate', 'errors')}">
                                    <g:datePicker name="dueDate" precision="day" value="${projectInstance?.dueDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                            <td valign="top" class="name"><g:message code="project.teams.label" default="Teams" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value"><g:link controller="team" action="list" params="${[project: projectInstance.id]}">Manage Teams</g:link>
                                <ul>
                                <g:each in="${projectInstance.teams}" var="t">
                                    <li><g:link controller="team" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                        
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
