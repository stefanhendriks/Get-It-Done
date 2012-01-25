package com.fundynamic.getitdone.domain;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class TaskChangerTest {

	private TaskChanger taskChanger;
	private Task task;

	@Before
	public void setUp() throws Exception {
		taskChanger = new TaskChanger();
		task = new Task("test task");
		task.setEstimatedHours(10);
		task.setBurnedHours(0);
	}

	@Test
	public void mustReduceEstimateByFive() {
		TaskChange taskChange = new TaskChange();
		taskChange.setEstimatedHours(-5);

		Task updatedTask = taskChanger.applyTaskChange(task, taskChange);

		Assert.assertEquals(5, updatedTask.getEstimatedHours());
	}

	@Test
	public void mustIncreaseEstimateByThree() {
		TaskChange taskChange = new TaskChange();
		taskChange.setEstimatedHours(3);

		Task updatedTask = taskChanger.applyTaskChange(task, taskChange);

		Assert.assertEquals(13, updatedTask.getEstimatedHours());
	}

	@Test
	public void mustIncreaseBurnedHoursByFive() {
		TaskChange taskChange = new TaskChange();
		taskChange.setBurnedHours(5);

		Task updatedTask = taskChanger.applyTaskChange(task, taskChange);

		Assert.assertEquals(5, updatedTask.getBurnedHours());
	}

	@Test
	public void mustSetNewDescriptionWhenTaskChangeHasNewDescription() {
		TaskChange taskChange = new TaskChange();
		taskChange.setDescription("A new description!");

		Task updatedTask = taskChanger.applyTaskChange(task, taskChange);

		Assert.assertEquals("A new description!", updatedTask.getDescription());
	}

	@Test
	public void mustNotChangeDescriptionWhenTaskChangeHasEmptyDescription() {
		TaskChange taskChange = new TaskChange();
		taskChange.setDescription("");

		Task updatedTask = taskChanger.applyTaskChange(task, taskChange);

		Assert.assertEquals("test task", updatedTask.getDescription());
	}

}
