

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>admin portal</title>
</head>
<body>

	<h1>Message : ${msg}</h1>

	<c:if test="${pageContext.request.userPrincipal.name != null}">

		<h1>HTTP Status 403 - Access is denied</h1>

	</c:if>

</body>
</html>