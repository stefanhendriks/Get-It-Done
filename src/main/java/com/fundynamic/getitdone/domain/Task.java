package com.fundynamic.getitdone.domain;

import java.util.LinkedList;
import java.util.List;

import com.fundynamic.getitdone.domain.exceptions.MustAssignToSubTaskException;
import org.springframework.util.StringUtils;

public class Task {

	private long id = -1L;

	private int initialEstimatedHours;
	private int estimatedHours;
	private int burnedHours;

	private String assignedWorker;

	private String description;
	private List<String> tags = new LinkedList<String>();

	public Task(String description) {
		this.description = description;
	}

	public Task(String description, int estimatedHours) {
		this(description);
		this.estimatedHours = estimatedHours;
	}

	public void setAssignedWorker(String worker)
			throws MustAssignToSubTaskException {
		this.assignedWorker = worker;
	}

	public String getAssignedWorker() {
		return assignedWorker;
	}

	public static Task createTestInstance() {
		Task task = new Task("Test task");
		return task;
	}

	public int getEstimatedHours() {
		return estimatedHours;
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

	public boolean isFinished() {
		return estimatedHours == 0 && initialEstimatedHours > 0 && burnedHours > 0;
	}

	public boolean isInProgress() {
		return burnedHours > 0 && !isFinished() && isAssignedToWorker();
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

	public boolean isAssignedToWorker() {
		return StringUtils.hasText(assignedWorker);
	}

	public int getInitialEstimatedHours() {
		return initialEstimatedHours;
	}

	public void setInitialEstimatedHours(int initialEstimatedHours) {
		this.initialEstimatedHours = initialEstimatedHours;
	}

	@Override
	public String toString() {
		return "Task{" +
				  "id=" + id +
				  ", initialEstimatedHours=" + initialEstimatedHours +
				  ", estimatedHours=" + estimatedHours +
				  ", burnedHours=" + burnedHours +
				  ", assignedWorker='" + assignedWorker + '\'' +
				  ", description='" + description + '\'' +
				  '}';
	}

	public void addTag(String tagname) {
		if (!StringUtils.hasText(tagname)) return;
		tagname = StringUtils.trimWhitespace(tagname);
		if (tags.contains(tagname)) return;
		tags.add(tagname);
	}

	public List<String> getTags() {
		return tags;
	}
}
