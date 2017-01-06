

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>admin portal</title>
</head>
<body>
	
	<h1>Message : ${msg}</h1>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a href="<c:url value="j_spring_security_logout" />" > Logout</a>
		</h2>
	</c:if>

</body>
</html>