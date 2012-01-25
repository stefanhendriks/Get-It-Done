<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
<head>
<title>Tasks</title>
</head>
<body>
<h1>Tasks</h1>

<p>Manage tasks</p>

<table cellspacing="0">
	<tr>
		<th></th>
		<th>Description</th>
		<th>Assignee</th>
		<th>ETC</th>
		<th>Burned</th>
		<th></th>
	</tr>

	<c:forEach items="${tasks}" var="task">
		<form action="${pageContext.request.contextPath}/tasks/save.htm" method="POST">
		<tr>
			<td align="center">${task.id}</td>
			<td><input type="text" value="${task.description}"
				name="description" size="80"></td>
			<td><input type="text" value="${task.assignedWorker}"
				name="assignedWorker" size="25"></td>
			<td><input type="text" value="${task.estimatedHours}"
				name="estimatedHours" size="5"></td>
			<td><input type="text" value="${task.burnedHours}"
				name="burnedHours" size="5"></td>
			<td align="right"><input type="submit" name="action"
				value="Update"></td>
		</tr>
		</form>
		<form action="/tasks/save.htm" method="POST">
		<tr id="subtaskpanel_${task.id}" style="display: none">
			<td></td>
			<td><input type="text" name="description" size="80"></td>
			<td><input type="text" name="assignedWorker" size="25"></td>
			<td><input type="text" name="estimatedHours" size="5"></td>
			<td><input type="text" name="burnedHours" size="5"></td>
			<td align="right"><input type="submit" name="action"
				value="Save"></td>
		</tr>
		</form>
	</c:forEach>
	<tr style="background-color: black;">
		<td colspan="6" />
	</tr>
	<form action="${pageContext.request.contextPath}/tasks/save.htm" method="POST">
	<tr>
		<td>New Task</td>
		<td><input type="text" name="description" size="80"></td>
		<td><input type="text" name="assignedWorker" size="25"></td>
		<td><input type="text" name="estimatedHours" size="5"></td>
		<td><input type="text" name="burnedHours" size="5"></td>
		<td align="right"><input type="submit" name="action" value="Save"></td>
	</tr>
	</form>
</table>

</body>

</html>