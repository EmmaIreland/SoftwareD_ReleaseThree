<g:each in="${question.choices}" var="choice">
<input type="radio" name="${question.id}" value="${question.choices.indexOf(choice)}" />
${choice}
<br>
</g:each>