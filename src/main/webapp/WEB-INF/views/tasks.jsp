<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
<head>
<title>Tasks</title>
</head>
<body>
<h1>Tasks</h1>

<p>Manage tasks</p>

<h2>Summary</h2>
Total amount of hours estimated until completion of all tasks: ${totalHoursEstimated}<br/>
Total amount of hours burned (finished and unfinished tasks): ${totalHoursBurned}<br/>
<br/>
Total amount of tasks finished : ${tasksFinished}<br/>
Total amount of hours burned for finished tasks : ${totalHoursBurnedFinishedTasks}<br/>

<table cellspacing="0">
	<tr>
		<th></th>
		<th>Description</th>
		<th>Assignee</th>
		<th>Initial ETC</th>
		<th>ETC</th>
		<th>Burned</th>
		<th></th>
	</tr>
	<tr>
		<td colspan="7" style="background-color: green;">Finished tasks</td>
	</tr>
	<c:forEach items="${tasks}" var="task">
		<c:if test="${task.finished}">
			<form action="${pageContext.request.contextPath}/tasks/save.htm" method="POST">
				<tr style="background-color: green;">
					<td align="center">${task.id}</td>
					<td><input type="text" value="${task.description}" name="description" size="80"></td>
					<td><input type="text" value="${task.assignedWorker}" name="assignedWorker" size="25"></td>
					<td><input type="text" value="${task.initialEstimatedHours}" name="initialEstimatedHours" size="10"></td>
					<td><input type="text" value="${task.estimatedHours}" name="estimatedHours" size="10"></td>
					<td><input type="text" value="${task.burnedHours}" name="burnedHours" size="5"></td>
					<td align="right"><input type="submit" name="action" value="Update"></td>
				</tr>
			</form>
		</c:if>
	</c:forEach>
	<tr style="background-color: black;">
		<td colspan="7"/>
	</tr>
	<tr>
		<td colspan="7" style="background-color: orange;">Tasks in progress</td>
	</tr>
	<c:forEach items="${tasks}" var="task">
		<c:if test="${not task.finished and task.inProgress}">
			<form action="${pageContext.request.contextPath}/tasks/save.htm" method="POST">
				<tr style="background-color: orange;">
					<td align="center">${task.id}</td>
					<td><input type="text" value="${task.description}" name="description" size="80"></td>
					<td><input type="text" value="${task.assignedWorker}" name="assignedWorker" size="25"></td>
					<td><input type="text" value="${task.estimatedHours}" name="estimatedHours" size="10"></td>
					<td><input type="text" value="${task.initialEstimatedHours}" name="initialEstimatedHours" size="10" readonly></td>
					<td><input type="text" value="${task.burnedHours}" name="burnedHours" size="5"></td>
					<td align="right"><input type="submit" name="action" value="Update"></td>
				</tr>
			</form>
			<form action="${pageContext.request.contextPath}/tasks/save.htm" method="POST">
				<tr id="subtaskpanel_${task.id}" style="display: none">
					<td></td>
					<td><input type="text" name="description" size="80"></td>
					<td><input type="text" name="assignedWorker" size="25"></td>
					<td><input type="text" name="estimatedHours" size="5"></td>
					<td><input type="text" name="burnedHours" size="5"></td>
					<td align="right"><input type="submit" name="action" value="Save"></td>
				</tr>
			</form>
		</c:if>
	</c:forEach>
	<tr style="background-color: black;">
		<td colspan="7"/>
	</tr>
	<tr>
		<td colspan="7" style="background-color: red;">Tasks open</td>
	</tr>
	<c:forEach items="${tasks}" var="task">
		<c:if test="${not task.finished and not task.inProgress}">
			<form action="${pageContext.request.contextPath}/tasks/save.htm" method="POST">
				<tr style="background-color: red;">
					<td align="center">${task.id}</td>
					<td><input type="text" value="${task.description}" name="description" size="80"></td>
					<td><input type="text" value="${task.assignedWorker}" name="assignedWorker" size="25"></td>
					<td><input type="text" value="${task.estimatedHours}" name="estimatedHours" size="10"></td>
					<td><input type="text" value="${task.initialEstimatedHours}" name="initialEstimatedHours" size="10"></td>					
					<td><input type="text" value="${task.burnedHours}" name="burnedHours" size="5"></td>
					<td align="right"><input type="submit" name="action" value="Update"></td>
				</tr>
			</form>
			<form action="/tasks/save.htm" method="POST">
				<tr id="subtaskpanel_${task.id}" style="display: none">
					<td></td>
					<td><input type="text" name="description" size="80"></td>
					<td><input type="text" name="assignedWorker" size="25"></td>
					<td><input type="text" name="estimatedHours" size="5"></td>
					<td><input type="text" name="burnedHours" size="5"></td>
					<td align="right"><input type="submit" name="action" value="Save"></td>
				</tr>
			</form>
		</c:if>
	</c:forEach>
	<tr style="background-color: black;">
		<td colspan="7" />
	</tr>
	<form action="${pageContext.request.contextPath}/tasks/save.htm" method="POST">
	<tr>
		<td>New Task</td>
		<td><input type="text" name="description" size="80"></td>
		<td><input type="text" name="assignedWorker" size="25"></td>
		<td><input type="text" name="estimatedHours" size="10"></td>
		<td><input type="text" name="initialEstimatedHours" size="10"></td>
		<td><input type="text" name="burnedHours" size="5"></td>
		<td align="right"><input type="submit" name="action" value="Save"></td>
	</tr>
	</form>
</table>

</body>

</html>