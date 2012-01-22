<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
<head>
<title>Hello :: Spring Application</title>
</head>
<body>
<h1>Hello - Spring Application</h1>
<p>Greetings.</p>

<table>
	<tr>
		<td>Title</td>
		<td>Writer</td>
	</tr>

	<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.title}</td>
			<td>${book.writer}</td>
		</tr>
	</c:forEach>
</table>

</body>

</html>