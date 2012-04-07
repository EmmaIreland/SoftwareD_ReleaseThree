<label for="${question.id}" class="error" style="display:none;">Please select one option.<br></label>
<g:each in="${question.choices}" var="choice">
<input type="radio" class="required" name="${question.id}" value="${question.choices.indexOf(choice)}" />
${choice}
<br>
</g:each>
