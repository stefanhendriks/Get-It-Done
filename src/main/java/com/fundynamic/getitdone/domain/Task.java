package com.fundynamic.getitdone.domain;

import java.util.LinkedList;
import java.util.List;

import com.fundynamic.getitdone.domain.exceptions.MustAssignToSubTaskException;

public class Task {

	private Estimate estimate = new Estimate(1, 5, 3);
	private Worker worker;
	private List<Task> subTasks = new LinkedList<Task>();

	private final String description;

	public Task(String description) {
		this.description = description;
	}

	public Task(String description, Estimate estimate) {
		this(description);
		if (estimate == null)
			throw new IllegalArgumentException("Estimate may not be null");
		this.estimate = estimate;
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

	public Estimate getEstimate() {
		if (hasSubTasks()) {
			return calculateEstimatesFromSubTasks();
		}
		return estimate;
	}

	private Estimate calculateEstimatesFromSubTasks() {
		Estimate estimate = Estimate.createEmpty();
		for (Task task : subTasks) {
			Estimate taskEstimate = task.getEstimate();
			estimate = estimate.add(taskEstimate);
		}
		return estimate;
	}

	public void setEstimate(Estimate estimate) {
		this.estimate = estimate;
	}

	public String toString() {
		final String TAB = "    ";

		String retValue = "";

		retValue = "Task ( estimate = "
				+ this.estimate + TAB + "worker = " + this.worker + TAB
				+ "subTasks = " + this.subTasks + TAB + "description = "
				+ this.description + TAB + " )";

		return retValue;
	}

}
