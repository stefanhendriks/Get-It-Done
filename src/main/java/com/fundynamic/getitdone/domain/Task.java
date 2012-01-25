package com.fundynamic.getitdone.domain;

import java.util.LinkedList;
import java.util.List;

import com.fundynamic.getitdone.domain.exceptions.MustAssignToSubTaskException;

public class Task {

	private PERTEstimate pertEstimate = new PERTEstimate(1, 5, 3);
	private Worker worker;
	private List<Task> subTasks = new LinkedList<Task>();

	private final String description;

	public Task(String description) {
		this.description = description;
	}

	public Task(String description, PERTEstimate pertEstimate) {
		this(description);
		if (pertEstimate == null)
			throw new IllegalArgumentException("pertEstimate may not be null");
		this.pertEstimate = pertEstimate;
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

	public PERTEstimate getPertEstimate() {
		if (hasSubTasks()) {
			return calculateEstimatesFromSubTasks();
		}
		return pertEstimate;
	}

	private PERTEstimate calculateEstimatesFromSubTasks() {
		PERTEstimate pertestimate = pertEstimate.createEmpty();
		for (Task task : subTasks) {
			PERTEstimate taskPERTEstimate = task.getPertEstimate();
			pertestimate = pertestimate.add(taskPERTEstimate);
		}
		return pertestimate;
	}

	public void setPertEstimate(PERTEstimate pertEstimate) {
		this.pertEstimate = pertEstimate;
	}

	public String toString() {
		final String TAB = "    ";

		String retValue = "";

		retValue = "Task ( pertEstimate = "
				+ this.pertEstimate + TAB + "worker = " + this.worker + TAB
				+ "subTasks = " + this.subTasks + TAB + "description = "
				+ this.description + TAB + " )";

		return retValue;
	}

}
