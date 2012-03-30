<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="The Quintets" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
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
        		<div id="title">Controllers</div>
        		<div id="list">
	        		<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
	                	<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.logicalPropertyName.capitalize()}</g:link></li>
	                </g:each>
                </div>
        	</div>
        </div>
        <div style="float: left">
        	<g:layoutBody />
        </div>
    </body>
</html>