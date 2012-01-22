package com.fundynamic.getitdone.domain;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.fundynamic.getitdone.domain.Task;
import com.fundynamic.getitdone.domain.Worker;
import com.fundynamic.getitdone.domain.exceptions.MustAssignToSubTaskException;

public class TaskTest {

	private Task task;

	@Before
	public void setUp() {
		task = new Task("Task description");
	}

	@Test
	public void mustAssignToWorkerWhenTaskHasNoSubTasks() {
		Worker worker = new Worker();

		// Act
		task.setAssignedWorker(worker);

		// Assert
		Assert.assertEquals(worker, task.getAssignedWorker());
	}

	@Test(expected = MustAssignToSubTaskException.class)
	public void mustThrowMustAssignToSubTaskExceptionWhenAssigningToTaskWithSubTasks() {
		task = Task.createTestInstanceWithSubTasks();

		// Act
		task.setAssignedWorker(new Worker());
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
		Worker worker = new Worker();
		task.setAssignedWorker(worker);

		Task subTask = new Task("Sub task");

		// Act
		task.addSubTask(subTask);

		// Assert
		Assert.assertNull(task.getAssignedWorker());
	}

	@Test
	public void mustAssignWorkerToSubTask() {
		Worker worker = new Worker();
		task.setAssignedWorker(worker);

		Task subTask = new Task("Sub task");

		// Act
		task.addSubTask(subTask);

		// Assert
		Assert.assertTrue(subTask.getAssignedWorker() == worker);
	}

	@Test
	public void mustReturnTotalEstimatedForSubTasks() {
		task.addSubTask(new Task("Some task", new Estimate(5, 10, 7)));
		task.addSubTask(new Task("Some other task", new Estimate(7, 20, 12)));
		task.addSubTask(new Task("Some other other task", new Estimate(1, 4, 2)));
		
		// Act
		Estimate estimate = task.getEstimate();
		
		// Assert
		Assert.assertEquals(13, estimate.getOptimistic());
		Assert.assertEquals(34, estimate.getPessimistic());
		Assert.assertEquals(21, estimate.getMostLikely());

		System.out.println(task);
	}
	
}
