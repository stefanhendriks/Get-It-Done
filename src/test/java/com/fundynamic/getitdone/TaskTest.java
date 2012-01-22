package com.fundynamic.getitdone;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.fundynamic.getitdone.domain.Task;

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
	
	@Test
	public void mustAddSubTask() {
		Task task = new Task();
		
		// Act
		Task subTask = new Task();
		task.addSubTask(subTask);
		
		// Assert
		List<Task> subTasks = task.getSubTasks();
		Assert.assertEquals(1, subTasks.size());
	}
	
	public class Worker {
		
	}
}
