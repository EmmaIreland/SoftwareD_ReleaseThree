
<%@ page import="survey.Enrollment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'enrollment.label', default: 'Enrollment')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>

                            <th><g:message code="enrollment.course.label" default="Course" /></th>
                        
                            <th><g:message code="enrollment.person.label" default="Person" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${enrollmentInstanceList}" status="i" var="enrollmentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            
                        
                            <td><g:link action="show" id="${enrollmentInstance.id}">${fieldValue(bean: enrollmentInstance, field: "course")}</g:link></td>
                        
                            <td>${fieldValue(bean: enrollmentInstance, field: "person")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${enrollmentInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
