<g:each in="${question.choices}" var="choice">
<input type="radio" class="required" name="${question.id}" value="${question.choices.indexOf(choice)}" />
${choice}
<br>
</g:each>