<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="The Quintets" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'trinkets.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.png')}" type="image/x-icon" />
        <r:require module="jquery"/>
        <r:require module="jquery-ui"/>
        <r:layoutResources/>
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <div id="sideBarNav">
        	<a href="${createLink(uri: '/')}"><img id="appLogo" src="${resource(dir:'images',file:'quintets_logo.png')}" alt="Grails" border="0" /></a>
        	<br>
        	<div id="appName"><a href="${createLink(uri: '/')}">Cevin's Cyqulists</a></div>
        	<div id="controllers">
        		<div id="title">Manage</div>
        		<div id="list">
        			<ul>
	                    <li class="controller"><g:link controller="course">Courses</g:link></li>
	                    <li class="controller"><g:link controller="project">Projects</g:link></li>
	                    <li class="controller"><g:link controller="team">Groups</g:link></li>
	                    <li class="controller"><g:link controller="person">People</g:link></li>
	                    <li class="controller"><g:link controller="survey">Surveys</g:link></li>
	                    <li class="controller"><g:link controller="question">Questions</g:link></li>
                    </ul>
                </div>
        	</div>
        </div>
        <div style="float: left">
        	<g:layoutBody />
        </div>
    </body>
</html>