<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h1>STUDENT ADMISSION FORM</h1>

	<form action="submitAdmissionForm3" method="post">
		<p>
			Student's Name : <input type="text" name="studentName" value="${student1.studentName}" />
		</p>
		<p>
			Student's Hobby : <input type="text" name="studentHobby" value="${student1.studentHobby}"/>
		</p>

		<input type="submit" value="Submit" />
	</form>

</body>
</html>

