<%@ page import="survey.Enrollment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'enrollment.label', default: 'Enrollment')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>
            	<g:if test="${enrollmentInstance.course}">Add students to <g:link class="uline" controller="course" action="show" id="${enrollmentInstance.course.id}">${enrollmentInstance.course.name}</g:link></g:if>
            	<g:else><g:message code="default.create.label" args="[entityName]" /></g:else>
           	</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${enrollmentInstance}">
            <div class="errors">
                <g:renderErrors bean="${enrollmentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>

                         <tbody>

                            <tr class="prop">
                             	<g:if test="${enrollmentInstance.course}">
	                            	<td valign="top" class="name">
	                            		<label for="course">${enrollmentInstance.course.enrollments ? "Students already enrolled:" : "No students are enrolled yet" }</label>
	                            	</td>
	                           		<td valign="top" class="value">
	                            		<ul>
		                            		<g:each in="${enrollmentInstance.course.enrollments.sort({it.person.name})}" var= "e">
		                            		<li>${e.person.name}</li>
		                            		</g:each>
	                            		</ul>
	                            	</td>
	                            	<input type="hidden" name="course.id" value="${enrollmentInstance?.course?.id}"/>
                            	</g:if>
                        		<g:else>
	                                <td valign="top" class="name">
	                                    <label for="course"><g:message code="enrollment.course.label" default="Course" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: enrollmentInstance, field: 'course', 'errors')}">
	                                    <g:select name="course.id" from="${survey.Course.list()}" optionKey="id" value="${enrollmentInstance?.course?.id}"  />
	                                </td>
                                </g:else>
                            </tr>

                            <tr class="prop">
                                <td valign="middle" class="name">
                                    <label for="person">Student to add:</label>
                                </td>

                                <td valign="top" class="value ${hasErrors(bean: enrollmentInstance, field: 'person', 'errors')}">
                                 	<g:if test="${hasAvailableStudents}">                              
    								 	<g:select name="person.id" from="${availableStudents}" optionKey="id" value="${enrollmentInstance?.person?.id}"  />
	                                </g:if>
	                                <g:else>
	                               		No unenrolled students
	                                </g:else>
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="Add" /></span>
                </div>
            </g:form>       
            <g:if test="${false && params?.course?.id}"> <!-- Disabled: Doesn't do error checking -->
            <g:form action="save">
            	<h2 style="margin-top: 25px;">Add new individual to system and course:</h2>
                <div class="dialog" >
                    <table>
                        <tbody>
                        	<g:hiddenField name="course.id" value="${courseInstance ? courseInstance.id : params.course.id}" />
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="person.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${personInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="person.email.label" default="Email" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${personInstance?.email}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="Add" /></span>
                </div>
            </g:form>
            </g:if>
        </div>
    </body>
</html>
