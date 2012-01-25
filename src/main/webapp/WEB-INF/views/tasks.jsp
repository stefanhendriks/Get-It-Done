<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<html>
<head>
	<title>Tasks</title>
</head>
<body>
<h1>Tasks</h1>

<p>Bla.</p>

<table width="100%">

		<tr>
			<td>#</td>
			<td>Description</td>
			<td>ETC</td>
			<td>Burned</td>
			<td>Action</td>
		</tr>

		<c:forEach items="${tasks}" var="task">
			<tr>
				<td>#${task.id}</td>
				<td>${task.description}</td>
				<td>${task.estimatedHours}</td>
				<td>${task.burnedHours}</td>
				<td>
					<form action="/tasks/update.htm" method="POST">
						<input type="hidden" value="${task.id}"/>
						<input type="submit" value="Update">
					</form>
				</td>
			</tr>
			<c:forEach items="${task.subTasks}" var="subTask">
				<tr>
					<td>CHILD</td>
					<td>${subTask.description}</td>
					<td>${subTask.estimatedHours}</td>
					<td>${subTask.burnedHours}</td>
					<td>
						<form action="/tasks/update.htm" method="POST">
							<input type="hidden" value="${task.id}"/>
							<input type="submit" value="Update">
						</form>
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
	<form action="/tasks/save.htm" method="POST">
		<input type="hidden" name="taskId" value="${lastTaskId}"/>
		<tr>
			<td>&nbsp;</td>
			<td><input type="text" name="description" size="150"></td>
			<td><input type="text" name="estimatedHours" size="5"></td>
			<td><input type="submit" name="action" value="Save as new"></td>
			<td><input type="submit" name="action" value="Save as sub-task"></td>
		</tr>
	</form>
</table>

</body>

</html>