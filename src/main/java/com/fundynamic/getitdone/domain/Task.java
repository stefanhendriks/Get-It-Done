package com.fundynamic.getitdone.domain;

import java.util.LinkedList;
import java.util.List;

import com.fundynamic.getitdone.domain.exceptions.MustAssignToSubTaskException;

public class Task {

	private int estimatedHours;
	private int burnedHours;

	private Worker worker;
	private List<Task> subTasks = new LinkedList<Task>();

	private final String description;

	public Task(String description) {
		this.description = description;
	}

	public Task(String description, int estimatedHours) {
		this(description);
		this.estimatedHours = estimatedHours;
	}

	public void setAssignedWorker(Worker worker)
			throws MustAssignToSubTaskException {
		if (hasSubTasks()) {
			throw new MustAssignToSubTaskException(
					"Cannot assign a worker to a task with sub tasks. Assign to the subtasks instead.");
		}
		this.worker = worker;
	}

	public Worker getAssignedWorker() {
		return worker;
	}

	public void addSubTask(Task subTask) {
		subTasks.add(subTask);
		if (worker != null) {
			subTask.setAssignedWorker(worker);
			worker = null;
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

	public int getBurnedHours() {
		return burnedHours;
	}

	public void setBurnedHours(int burnedHours) {
		this.burnedHours = burnedHours;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@Override
	public String toString() {
		return "Task{" +
				  "estimatedHours=" + estimatedHours +
				  ", burnedHours=" + burnedHours +
				  ", worker=" + worker +
				  ", subTasks=" + subTasks +
				  ", description='" + description + '\'' +
				  '}';
	}
}
