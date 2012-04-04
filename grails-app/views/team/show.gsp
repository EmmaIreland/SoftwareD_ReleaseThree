
<%@ page import="survey.Team" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'team.label', default: 'Team')}" />
        <title><g:message code="Group Description" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="Group List" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>${teamInstance.name}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="team.project.label" default="Project" /></td>
                            
                            <td valign="top" class="value"><g:link controller="project" action="show" id="${teamInstance?.project?.id}">${teamInstance?.project?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                        
                       <tr class="prop">
                            <td valign="top" class="name"><g:message code="team.comments.label" default="Comments" /></td>
                             
                             <td valign="top" class="value">${fieldValue(bean: teamInstance, field: "comments")}</td>
                                                        
                        </tr>
                        
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="team.memberships.label" default="Members" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value"><g:link controller="team" action="list" params="${[project: teamInstance.project.id]}">Manage Students in Group</g:link>
                                <ul>
                                <g:each in="${teamInstance.memberships}" var="m">
                                    <li><g:link controller="person" action="show" id="${m.member.id}">${m?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${teamInstance?.id}" />
                    <span class="button"><g:actionSubmit class=edit action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
