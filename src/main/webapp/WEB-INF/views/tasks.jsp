<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

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
			<tr>
				<td align="center">${task.id}</td>
				<td>${task.description}</td>
				<td align="center">${task.assignedWorker}</td>
				<td align="center">${task.estimatedHours}</td>
				<td align="center">${task.burnedHours}</td>
			</tr>
		</c:forEach>
	<form action="/tasks/save.htm" method="POST">
		<tr>
			<td>New</td>
			<td><input type="text" name="description" size="80"></td>
			<td><input type="text" name="assignedWorker" size="25"></td>
			<td><input type="text" name="estimatedHours" size="5"></td>
			<td><input type="text" name="burnedHours" size="5"></td>
			<td><input type="submit" name="action" value="Save"></td>
		</tr>
	</form>
</table>

</body>

</html>