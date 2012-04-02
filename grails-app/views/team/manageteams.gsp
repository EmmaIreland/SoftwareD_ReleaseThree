
<%@ page import="survey.Team" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'team.label', default: 'Team')}" />
        <g:javascript src="utility.js" />
        <g:javascript src="teamscript.js" />
        <title>Manage Groups for ${projectInstance.name}</title>
        <style>
        	li {
        		list-style-type: none;
        	}
	        .draggable {
	        	z-index: 1000;
	        	cursor: move;
	        }
	        .dialog {
	    		width: 360px;
	    		margin-bottom: 10px;
	    	}
	    	.group {
	    		float: left;
	    		background: #ccc;
	        	padding: 6px 8px 0px 8px;
	        	margin: 0px 0px 10px 10px;
	    		position: relative;
	    		border-radius: 6px 0px 6px 6px;
	    	}
	    	.group .deleteButton {
	    		position: absolute;
	    		right: -6px;
	    		top: -6px;
	    		cursor: pointer
	    	}
	    	.group .teamname {
	    		font-size: 12px;
	    		font-weight: bold;
	    		padding-bottom: 4px;
	    	}
	    	.group ul.students {
	    		padding: 2px;
	    		min-height: 20px;
	    		background-color: #eee;
	    		border-radius: 4px;
	    	}
	    	#groupArea {
	    		float: left;
				width: 360px;
	    	}
        </style>
    </head>

	  <body>
	  <div class="nav">
	      <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
	  </div>
	  <div class="body">
	      <h1>Manage Groups for <g:link class="uline" controller="project" action="show" id="${projectInstance.id}"> ${projectInstance.name}</g:link></h1>
	      <g:if test="${flash.message}">
	      <div class="message">${flash.message}</div>
	      </g:if>
	      	<div style="float: left;">
	      		<div class="dialog droppable" id="unassignedStudents">
		      		<h2>Students not assigned to groups</h2>
			 		<div title="Drag students to assign them to groups" id="studentList">
			 			<g:if test="${courseHasStudents}">
		 					<ul class="students" id="g0" style="min-height: 40px">
		 						<g:each in="${unassignedStudents.sort() {it.name}}" var="student">
		 							<li><span class="student draggable" id="s${student.id}">${student.name}</span></li>
			 					</g:each>
			 				</ul>
		 				</g:if>
		 				<g:else>
			 				<g:link controller="enrollment" action="create" params="${['course.id': projectInstance.course.id]}">Add students to ${projectInstance.course.name}</g:link>
		 				</g:else>
		 			</div>
	 			</div>
 				<div class="dialog" id="addgroup" style="clear: left;">
	 				<g:form action="save">
            			<h2>Add new named group to this project:</h2>
                		<div class="dialog">
                    	<table>
                        	<tbody>
                        		<g:hiddenField name="project.id" value="${projectInstance.id}" />
                            	<tr class="prop">
                                	<td valign="top" class="name">
                                    	<label for="name"><g:message code="team.name.label" default="Name" /></label>
                                	</td>
                                	<td valign="top" class="value ${hasErrors(bean: teamInstance, field: 'name', 'errors')}">
                                    	<g:textField name="name" value="${teamInstance?.name}" />
                               		</td>
                            	</tr>                       
                        	</tbody>
                    	</table>
                		</div>
                		<div class="buttons">
                    		<span class="button"><g:submitButton name="create" class="save" value="Add" /></span>
                		</div>
            		</g:form>
	 			</div>
	 			<div class="dialog" id="addmultiplegroups" style="clear: left">
	 				<g:form action="addmany">
	 					<h2>Add multiple generically-named groups:</h2>
	 					<div class="dialog">
	 					<table>
	 						<tbody>
	 							<g:hiddenField name="project.id" value="${projectInstance.id}" />
	 							<tr class="prop">
	 								<td valign="top" class="name">Number of groups:</td>
	 								<td valign="top" class="value">
	 									<g:select from="${1..10}" name="num_groups" />
	 								</td>
	 							</tr>
	 							<tr class="prop">
	 								<td valign="top" class="name">Populate groups randomly:</td>
	 								<td>
	 									<g:checkBox name="random" value="${true}" />
	 								</td>
	 							</tr>
	 						</tbody>
	 					</table>
	 					</div>
	 					<div class="buttons">
                    		<span class="button"><g:submitButton name="create" class="save" value="Add" /></span>
                		</div>
	 				</g:form>
	 			</div>
	 		</div>
	 		<div title="Drag students to change groups" id="groupArea">
	 			<g:if test="${projectHasTeams}">
	 				<g:each in="${projectInstance.teams.sort() {it.name}}" var="team">
	 					<div class="group droppable">
	 						<img class="deleteButton" src="${resource(dir:'images',file:'delete.png')}"  />
	 						
	 						<div class="teamname">
	 							<g:link action="edit" id="${team.id}">${fieldValue(bean: team, field: "name")}</g:link>
	 						</div>
	 						
	 						<ul class="students" id="g${team.id}">
	 							<g:each in="${team.memberships.sort() {it.member.name}}" var="membership">
	 							<li><span class="student draggable" id="s${membership.member.id}">${membership.member.name}</span></li>
	 							</g:each>
	 						</ul>
	 					</div>
	 				</g:each>
	 			</g:if>
	 			<g:else>
	 				<h2>No groups have been created for this project.</h2>
	 			</g:else>
	 		</div>
	 	</div>
	 	</body>
</html>