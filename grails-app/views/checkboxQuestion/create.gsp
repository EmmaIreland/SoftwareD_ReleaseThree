<%@ page import="survey.questions.CheckboxQuestion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'checkboxQuestion.label', default: 'CheckboxQuestion')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <g:javascript library="jquery" plugin="jquery" />
        <script type="text/javascript">
        $(function() {
            $(document).on("keyup", ".addOnChange", function(x) {
                var ele = $(x.target);
                if (ele.val().length > 0 && ele.next().length == 0) {
                    ele.after("<br /><input type='text' name='choices' class='addOnChange' />");
                }
            });
        });
        </script>
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
            <g:hasErrors bean="${checkboxQuestionInstance}">
            <div class="errors">
                <g:renderErrors bean="${checkboxQuestionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="prompt"><g:message code="checkboxQuestion.prompt.label" default="Prompt" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: checkboxQuestionInstance, field: 'prompt', 'errors')}">
                                    <g:textField name="prompt" value="${checkboxQuestionInstance?.prompt}" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="choices"><g:message code="checkboxQuestion.choices.label" default="Choices" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: checkboxQuestionInstance, field: 'prompt', 'errors')}">
                                    <g:textField name="choices" class="addOnChange" id="choice1" />
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
