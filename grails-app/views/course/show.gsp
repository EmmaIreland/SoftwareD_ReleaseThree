
<%@ page import="survey.Course" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>${courseInstance.name}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                                                         
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="course.abbreviation.label" default="Abbreviation" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: courseInstance, field: "abbreviation")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="course.term.label" default="Term" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: courseInstance, field: "term")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="course.year.label" default="Year" /></td>
                            
                            <td valign="top" class="value"><g:formatNumber number="${courseInstance.year}" groupingUsed="false"/></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Instructor:</td>
                            
                            <td valign="top" class="value"><g:link controller="person" action="show" id="${courseInstance?.owner?.id}">${courseInstance?.owner?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Enrolled Students:</td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                            	<g:link controller="enrollment" action="create" params="${['course.id': courseInstance.id] }">Add a student</g:link>
                                <ul>
                                <g:each in="${courseInstance.enrollments}" var="e">
                                    <li><g:link controller="person" action="show" id="${e.person.id}">${e?.person?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Projects:</td>
                            
                            <td valign="top" class="value" class="uline"><g:link controller="project" action="create" params="${['course.id': courseInstance.id]}"><b>Create new Project</b></g:link>
                                <ul>
                                <g:each in="${courseInstance.projects}" var="p">
                                    <li><g:link controller="project" action="show" id="${p.id}">${p?.name?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>                        
                    
                    </tbody>
                </table>
                <g:if test="${addStudent}">
                
                </g:if>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${courseInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
