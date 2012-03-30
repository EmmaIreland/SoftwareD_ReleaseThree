${question.prompt}
<br>
<g:each in="${question.choices}" var="choice">
<input type="radio" name="${question.id}" />
${choice}
<br>
</g:each>
<br>