<g:each in="${question.choices}" var="choice">
	<input type="checkbox" name="${question.id}" value="${question.choices.indexOf(choice)}" />
	${choice}
	<br>
</g:each>