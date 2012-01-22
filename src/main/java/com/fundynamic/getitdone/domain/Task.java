package com.fundynamic.getitdone.domain;

import java.util.LinkedList;
import java.util.List;

import com.fundynamic.getitdone.TaskTest.Worker;

public class Task {

	private Worker worker;
	private List<Task> subTasks = new LinkedList<Task>();
	
	public void setAssignedWorker(Worker worker) {
		this.worker = worker;
	}

	public Worker getAssignedWorker() {
		return worker;
	}

	public void addSubTask(Task subTask) {
		subTasks.add(subTask);
	}

	public List<Task> getSubTasks() {
		return subTasks;
	}

}
