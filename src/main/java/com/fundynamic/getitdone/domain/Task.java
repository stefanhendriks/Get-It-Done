package com.fundynamic.getitdone.domain;

import java.util.LinkedList;
import java.util.List;

import com.fundynamic.getitdone.domain.exceptions.MustAssignToSubTaskException;

public class Task {

	private Worker worker;
	private List<Task> subTasks = new LinkedList<Task>();
	
	public void setAssignedWorker(Worker worker) throws MustAssignToSubTaskException {
		if (hasSubTasks()) {
			throw new MustAssignToSubTaskException("Cannot assign a worker to a task with sub tasks. Assign to the subtasks instead.");
		}
		this.worker = worker;
	}

	public Worker getAssignedWorker() {
		return worker;
	}

	public void addSubTask(Task subTask) {
		subTasks.add(subTask);
		if (worker != null) {
			worker = null;
		}
	}

	public List<Task> getSubTasks() {
		return subTasks;
	}

	public static Task createTestInstanceWithSubTasks() {
		Task task = new Task();
		task.addSubTask(new Task());
		return task;
	}

	public boolean hasSubTasks() {
		return subTasks.size() > 0;
	}

}
