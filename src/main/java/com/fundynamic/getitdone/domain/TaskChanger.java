package com.fundynamic.getitdone.domain;

import org.springframework.util.StringUtils;

public class TaskChanger {

	public Task applyTaskChange(Task taskToChange, TaskChange taskChange) {
		Task task = Task.copyFrom(taskToChange);
		int estimatedHours = task.getEstimatedHours();
		task.setEstimatedHours((estimatedHours + taskChange.getEstimatedHours()));

		int burnedHours = task.getBurnedHours();
		task.setBurnedHours((burnedHours + taskChange.getBurnedHours()));

		task.setAssignedWorker(taskChange.getAssignee());

		if (StringUtils.hasText(taskChange.getDescription())) {
			task.setDescription(taskChange.getDescription());
		}

		return task;
	}
}
