<span style="font-weight: bold">${question.prompt}</span>
<br/>
<g:render template="${question.getTemplateName()}" bean="${question}" var="question" />
<br/>