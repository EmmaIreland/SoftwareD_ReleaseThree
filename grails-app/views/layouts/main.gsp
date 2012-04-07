<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Cevin's Cyqulists" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'trinkets.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.png')}" type="image/x-icon" />
        <r:require module="jquery"/>
        <r:require module="jquery-ui"/>
        <r:use module="jquery-validate"/>
        <r:layoutResources/>
        <r:layoutResources disposition="defer"/>
        <g:layoutHead />
        <g:javascript src="trinkets.js" />
    </head>
    <body>
        <div id="sideBarNav">
        	<div><a href="${createLink(uri: '/')}"><img id="appLogo" src="${resource(dir:'images',file:'Cevin_logo.png')}" alt="Grails" border="0" /></a></div>
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