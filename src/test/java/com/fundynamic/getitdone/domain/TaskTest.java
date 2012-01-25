package com.fundynamic.getitdone.domain;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.fundynamic.getitdone.domain.exceptions.MustAssignToSubTaskException;

public class TaskTest {

	private Task task;

	@Before
	public void setUp() {
		task = new Task("Task description");
	}

	@Test
	public void mustAssignToWorkerWhenTaskHasNoSubTasks() {
		// Act
		task.setAssignedWorker("Stefan");

		// Assert
		Assert.assertEquals("Stefan", task.getAssignedWorker());
	}

	@Test(expected = MustAssignToSubTaskException.class)
	public void mustThrowMustAssignToSubTaskExceptionWhenAssigningToTaskWithSubTasks() {
		task = Task.createTestInstanceWithSubTasks();

		// Act
		task.setAssignedWorker("Stefan");
	}

	@Test
	public void mustReturnTrueWhenTaskHasSubTasks() {
		task = Task.createTestInstanceWithSubTasks();
		Assert.assertTrue(task.hasSubTasks());
	}

	@Test
	public void mustAddSubTask() {
		// Act
		Task subTask = new Task("Sub task");
		task.addSubTask(subTask);

		// Assert
		List<Task> subTasks = task.getSubTasks();
		Assert.assertEquals(1, subTasks.size());
		Task subTaskFromTask = subTasks.get(0);
		Assert.assertTrue(subTaskFromTask == subTask);
	}

	@Test
	public void mustUnAssignWorkerWhenTaskGetsSubTask() {
		task.setAssignedWorker("Stefan");

		Task subTask = new Task("Sub task");

		// Act
		task.addSubTask(subTask);

		// Assert
		Assert.assertNull(task.getAssignedWorker());
	}

	@Test
	public void mustAssignWorkerToSubTask() {
		task.setAssignedWorker("Stefan");

		Task subTask = new Task("Sub task");

		// Act
		task.addSubTask(subTask);

		// Assert
		Assert.assertTrue("Stefan".equals(subTask.getAssignedWorker()));
	}

	@Test
	public void mustReturnTotalEstimatedForSubTasks() {
		task.addSubTask(new Task("Some task", 5));
		task.addSubTask(new Task("Some other task", 10));
		task.addSubTask(new Task("Some other other task", 3));
		
		// Act
		int estimatedHours = task.getEstimatedHours();

		// Assert
		Assert.assertEquals(18, estimatedHours);
	}

	@Test
	public void mustReturnEstimatedHoursWhenTaskHasNoSubtasks() {
		Task task = new Task("someTask", 10);
		org.junit.Assert.assertEquals(10, task.getEstimatedHours());
	}
	
}
