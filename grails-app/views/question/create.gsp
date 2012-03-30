<%@ page import="survey.Question" %>
<%@ page import="survey.Survey" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script type="text/javascript">
        $(function() {
        	// onLoad
        	updateQuestionType($("#typeDropdown"));
        	$(document).on("keyup", ".addOnChange", function(x) {
                var ele = $(x.target);
                if (ele.val().length > 0 && ele.next().length == 0) {
                    ele.after("<br /><input type='text' name='" + ele.attr('name') + "' class='addOnChange' />");
                }
            });
        });
        
        function updateQuestionType(dropdown) {
        	var type = $(dropdown).val();
        	$(".typeRow").hide();
        	switch(type) {
        		case "existing":
        			$("#existingRow").show();
        			break;
        		case "checkbox":
        			$(".checkboxRow").show();
        			break;
        		case "multipleChoice":
        		    $(".multipleRow").show();
        		    break;
        		case "shortResponse":
        		    $(".shortRow").show()
        		    break;
        		case "longResponse":
        		    $(".longRow").show()
        		    break;
        	}
        }

        function validateNewQuestionForm() {
            var visiblePromptValue = $("[name$='Prompt']").filter(":visible").val();
            if (visiblePromptValue != null && visiblePromptValue.length < 1) {
                $(".errors").html("Please enter a prompt.").show();
                return false;
            }

            var visibleChoices = $("[name$='Choices']").filter(":visible");
            var choicesLengths = visibleChoices.map(function() { return $(this).val().length; }).get();
            // .get() returns a "real" JS array that we can use native functions on (e.g. every())
            var allEmpty = choicesLengths.every(function(len) { return len < 1; });
            if (allEmpty && visibleChoices.length > 0) {
            	$(".errors").html("Please enter at least one valid choice.").show();
                return false;
            }
            return true;
        }
        </script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>Create Question for <g:link class="uline" controller="survey" action="show" id="${params.surveyid}">${Survey.get(params.surveyid).title}</g:link></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="errors" style="display:none"></div>
            <g:form controller="survey" action="addQuestion" onsubmit="return validateNewQuestionForm();">
            <g:hiddenField name="surveyid" value="${params.surveyid}"/>
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="question.type.label" default="Question Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'name', 'errors')}">
                                    <select name="type" id="typeDropdown" onchange="updateQuestionType(this);">
                                    <option value="existing">Add existing question</option>
                                    <option value="checkbox">New checkbox question</option>
                                    <option value="multipleChoice">New multiple choice question</option>
                                    <option value="shortResponse">New short text question</option>
                                    <option value="longResponse">New long text question</option>
                                    </select>
                                </td>
                            </tr>
                        
                            <tr class="prop typeRow" id="existingRow">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="question.type.label" default="Select an Existing Question" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'name', 'errors')}">
                                    <g:select name="questionid" from="${existingQuestions}" optionKey="id"/>
                                </td>
                            </tr>
                            
                              <tr class="prop typeRow checkboxRow">
                                <td valign="top" class="name">
                                    <label for="prompt"><g:message code="checkboxQuestion.prompt.label" default="Prompt" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: checkboxQuestionInstance, field: 'prompt', 'errors')}">
                                    <g:textField name="cbPrompt" value="${checkboxQuestionInstance?.prompt}" />
                                </td>
                            </tr>
                            
                            <tr class="prop typeRow checkboxRow">
                                <td valign="top" class="name">
                                    <label for="choices"><g:message code="checkboxQuestion.choices.label" default="Choices" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: checkboxQuestionInstance, field: 'prompt', 'errors')}">
                                    <g:textField name="cbChoices" class="addOnChange" id="choice1" />
                                </td>
                            </tr>
                            
                             <tr class="prop typeRow multipleRow">
                                <td valign="top" class="name">
                                    <label for="prompt"><g:message code="multipleChoiceQuestion.prompt.label" default="Prompt" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: multipleChoiceQuestion, field: 'prompt', 'errors')}">
                                    <g:textField name="mcPrompt" value="${multipleChoiceQuestion?.prompt}" />
                                </td>
                            </tr>
                            
                            <tr class="prop typeRow multipleRow">
                                <td valign="top" class="name">
                                    <label for="choices"><g:message code="multipleChoiceQuestion.choices.label" default="Choices" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: multipleChoiceQuestion, field: 'prompt', 'errors')}">
                                    <g:textField name="mcChoices" class="addOnChange" id="choice1" />
                                </td>
                            </tr>
                            
                             <tr class="prop typeRow shortRow">
                                <td valign="top" class="name">
                                    <label for="prompt"><g:message code="shortTextQuestion.prompt.label" default="Prompt" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shortTextQuestion, field: 'prompt', 'errors')}">
                                    <g:textField name="stPrompt" value="${shortTextQuestion?.prompt}" />
                                </td>
                            </tr>
                            
                             <tr class="prop typeRow longRow">
                                <td valign="top" class="name">
                                    <label for="prompt"><g:message code="longTextQuestion.prompt.label" default="Prompt" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: longTextQuestion, field: 'prompt', 'errors')}">
                                    <g:textField name="ltPrompt" value="${longTextQuestion?.prompt}" />
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
