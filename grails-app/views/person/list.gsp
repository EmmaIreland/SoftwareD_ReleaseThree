
<%@ page import="survey.Person" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
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
                    
                    <tbody>
                    <g:each in="${personInstanceList}" status="i" var="personInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        	<trinkets:collapsibleDiv title="${personInstance.name}">
                        		
                        		Email: ${fieldValue(bean: personInstance, field: "email")}

                            	<div id="${personInstance.enrollments.course}">
                            		<br>
                            		<h4>Enrollments</h4>
                            			<g:each in="${personInstance.enrollments.course}" var="e">
                              		      	<ul>
                             		       		<ul>
                              		      			<li><g:link controller="course" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
                              		      		</ul>
                                  		  	</ul>
                                		</g:each>
                                </div>
   
   								<div id="${personInstance.memberships.team}">
   									<br>
                                		<h4>Memberships</h4>
                                			<g:each in="${personInstance.memberships.team}" var="m">
                                				<ul>
                             		       			<ul>
                              		      				<li><g:link controller="project" action="show" id="${m.project.id}">${m?.encodeAsHTML()}</g:link></li>
                              		      			</ul>
                              		      		</ul>
                                			</g:each>
                                </div>
                                
                                <div id="${personInstance.ownedCourses}">
   									<br>
                                		<h4>Owned Courses</h4>
                                			<g:each in="${personInstance.ownedCourses}" var="o">
                                				<ul>
                             		       			<ul>
                              		      				<li><g:link controller="course" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
                              		      			</ul>
                              		      		</ul>
                                			</g:each>
                                </div>
                                
                        	</trinkets:collapsibleDiv>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${personInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
