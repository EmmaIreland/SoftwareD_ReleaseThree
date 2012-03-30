

<%@ page import="survey.Membership" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'membership.label', default: 'Membership')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${membershipInstance}">
            <div class="errors">
                <g:renderErrors bean="${membershipInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="team"><g:message code="membership.team.label" default="Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'team', 'errors')}">
                                    <g:select name="team.id" from="${survey.Team.list()}" optionKey="id" value="${membershipInstance?.team?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="member"><g:message code="membership.member.label" default="Member" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'member', 'errors')}">
                                    <g:select name="member.id" from="${survey.Person.list()}" optionKey="id" value="${membershipInstance?.member?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
