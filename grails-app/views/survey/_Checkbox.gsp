${question.prompt}
<br>
<g:each in="${question.choices}" var="choice">
	<input type="checkbox" name="${question.id}" value="${choice.encodeAsHTML()}" />
	${choice}
	<br>
</g:each>
<br>