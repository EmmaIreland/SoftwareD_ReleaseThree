
<%@ page import="survey.Team"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName"
	value="${message(code: 'team.label', default: 'Team')}" />
<title><g:message code="Group List" args="[entityName]" /></title>
</head>
<body>

	<script>
	$(function() {
		$( "#accordion" ).accordion({
			collapsible: true
		});
	});
	</script>

	<div class="nav">
		<span class="menuButton"><a class="home"
			href="${createLink(uri: '/')}"><g:message
					code="default.home.label" /> </a> </span>
	</div>
	<div class="body">
		<div id="accordion">
			<g:each in="${teamInstanceList}" var="m">
				<h3>
					<a href="#">
						${m.name}
					</a>

				</h3>			
				<div>
					<div>
						<div>
							
							<b>Project:</b> 
							<br></br>
								<ul>
									<g:link controller="project" action="show" id="${m.project.id}">${m?.project.name.encodeAsHTML()}</g:link>
								</ul>
						</div>					
					
					<br></br>
						 <span class="button"><g:link class="edit" controller="team" action="edit" id="${m.id}">${'Edit Group'}
							</g:link>
						</span>
					</div>
					<br></br>
					<b>Students in ${m.name}:</b> 
							<br></br>
					<ul>
						<g:each in="${m.memberships}" var="i">
							<ul>
							<li><g:link controller="person" action="show" id="${i.member.id}"> ${i?.encodeAsHTML()}</g:link> </li>
							</ul>
						</g:each>
					</ul>
					<div>
						<br></br> <b>Comments:</b> <br></br> 
							<ul>
								${m.comments}
							</ul>
					</div>
	
				</div>
			</g:each>
		</div>

	</div>
</body>
</html>
