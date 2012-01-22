package com.fundynamic.getitdone.domain;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.fundynamic.getitdone.domain.Task;
import com.fundynamic.getitdone.domain.Worker;
import com.fundynamic.getitdone.domain.exceptions.MustAssignToSubTaskException;

public class TaskTest {

	@Test
	public void mustAssignToWorkerWhenTaskHasNoSubTasks() {
		Task task = new Task();
		Worker worker = new Worker();
		
		// Act
		task.setAssignedWorker(worker);
		
		// Assert
		Assert.assertEquals(worker, task.getAssignedWorker());
	}
	
	@Test (expected = MustAssignToSubTaskException.class)
	public void mustThrowMustAssignToSubTaskExceptionWhenAssigningToTaskWithSubTasks() {
		Task task = Task.createTestInstanceWithSubTasks();
		
		// Act
		task.setAssignedWorker(new Worker());
	}
	
	@Test
	public void mustReturnTrueWhenTaskHasSubTasks() {
		Task task = Task.createTestInstanceWithSubTasks();
		Assert.assertTrue(task.hasSubTasks());
	}
	
	@Test
	public void mustAddSubTask() {
		Task task = new Task();
		
		// Act
		Task subTask = new Task();
		task.addSubTask(subTask);
		
		// Assert
		List<Task> subTasks = task.getSubTasks();
		Assert.assertEquals(1, subTasks.size());
		Task subTaskFromTask = subTasks.get(0);
		Assert.assertTrue(subTaskFromTask == subTask);
	}

	@Test
	public void mustUnAssignWorkerWhenTaskGetsSubTask() {
		Task task = new Task();
		
		Worker worker = new Worker();
		task.setAssignedWorker(worker);
		
		Task subTask = new Task();
		
		// Act
		task.addSubTask(subTask);
		
		// Assert
		Assert.assertNull(task.getAssignedWorker());
	}
	
	@Test
	public void mustAssignWorkerToSubTask() {
		Task task = new Task();

		Worker worker = new Worker();
		task.setAssignedWorker(worker);
		
		Task subTask = new Task();
		
		// Act
		task.addSubTask(subTask);
		
		// Assert
		Assert.assertTrue(subTask.getAssignedWorker() == worker);
	}
	
}
