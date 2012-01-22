<html>
	<body>
		<h2>Example webapplication</h2>
	</body>
	<h2>Context Path</h2>
	<font color="red">The Context Path is:${pageContext.request.contextPath}</font>

	<h1>Change language</h1>
	<a href="${pageContext.request.contextPath}/hello.htm?lang=nl">Dutch</a><br/>
	<a href="${pageContext.request.contextPath}/hello.htm?lang=en">English</a><br/>
	<h1>Choose your destination</h1>
	<a href="${pageContext.request.contextPath}/hello.htm">Show hello world with Spring</a><br/>
	<a href="${pageContext.request.contextPath}/registration.htm">Show registration form page (with validation)</a><br/>
</html>
