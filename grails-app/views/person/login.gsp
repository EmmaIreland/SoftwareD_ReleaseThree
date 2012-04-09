<%@ page import="survey.Person"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName"
	value="${message(code: 'person.label', default: 'Person')}" />
<title>Login</title>
</head>

<body>
    <div class="nav">
        <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    </div>
	<div class="body">
		<h1>Login</h1>
		<g:form method="post">
			<div class="dialog">
					<table>
						<tbody>
							<g:if test="${loginStatus == 'failed'}">
								<tr>
									<td colspan="2">
										<span style="color: #f00">The given email and password combination is invalid.</span>
									</td>
								</tr>
							</g:if>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="email"><g:message code="person.email.label" default="Email" /></label>
								</td>
								<td valign="top" class="value">
									<g:textField name="email" value="${enteredEmail}" />
								</td>
							</tr>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="password"><g:message code="person.password.label" default="Password" /></label>
								</td>
								<td valign="top" class="value">
									<g:passwordField name="password" />
								</td>
							</tr>
						</tbody>
					</table>
			</div>
	        <div class="buttons">
	            <span class="button"><g:actionSubmit class="sendLogin" action="sendLogin" value="Login" /></span>
	        </div>
        </g:form>
	</div>
</body>

</html>