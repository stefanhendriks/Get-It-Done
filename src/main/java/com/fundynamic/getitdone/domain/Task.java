package com.fundynamic.getitdone.domain;

import java.util.LinkedList;
import java.util.List;

import com.fundynamic.getitdone.domain.exceptions.MustAssignToSubTaskException;

public class Task {

	private long id = -1L;

	private int estimatedHours;
	private int burnedHours;

	private String assignedWorker;
	private List<Task> subTasks = new LinkedList<Task>();

	private String description;

	public Task(String description) {
		this.description = description;
	}

	public Task(String description, int estimatedHours) {
		this(description);
		this.estimatedHours = estimatedHours;
	}

	public void setAssignedWorker(String worker)
			throws MustAssignToSubTaskException {
		if (hasSubTasks()) {
			throw new MustAssignToSubTaskException(
					"Cannot assign a assignedWorker to a task with sub tasks. Assign to the subtasks instead.");
		}
		this.assignedWorker = worker;
	}

	public String getAssignedWorker() {
		return assignedWorker;
	}

	public void addSubTask(Task subTask) {
		subTasks.add(subTask);
		if (assignedWorker != null) {
			subTask.setAssignedWorker(assignedWorker);
			assignedWorker = null;
		}
	}

	public List<Task> getSubTasks() {
		return subTasks;
	}

	public static Task createTestInstanceWithSubTasks() {
		Task task = new Task("Test task");
		task.addSubTask(new Task("Test sub task"));
		return task;
	}

	public boolean hasSubTasks() {
		return subTasks.size() > 0;
	}

	public int getEstimatedHours() {
		if (hasSubTasks()) {
			return calculateEstimatesFromSubTasks();
		}
		return estimatedHours;
	}

	private int calculateEstimatesFromSubTasks() {
		int hours = 0;
		for (Task task : subTasks) {
			hours += task.getEstimatedHours();
		}
		return hours;
	}

	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public int getBurnedHours() {
		return burnedHours;
	}

	public void setBurnedHours(int burnedHours) {
		this.burnedHours = burnedHours;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}



	@Override
	public String toString() {
		return "Task{" +
				  "estimatedHours=" + estimatedHours +
				  ", burnedHours=" + burnedHours +
				  ", assignedWorker=" + assignedWorker +
				  ", subTasks=" + subTasks +
				  ", description='" + description + '\'' +
				  '}';
	}

	public boolean isFinished() {
		return estimatedHours == 0;
	}

	public float getVelocity() {
		return estimatedHours / burnedHours;
	}

	public static Task copyFrom(Task taskToCopy) {
		Task task = new Task(taskToCopy.getDescription());
		task.setEstimatedHours(taskToCopy.getEstimatedHours());
		task.setAssignedWorker(taskToCopy.getAssignedWorker());
		task.setBurnedHours(taskToCopy.getBurnedHours());
		task.setId(taskToCopy.getId());
		return task;
	}
}
