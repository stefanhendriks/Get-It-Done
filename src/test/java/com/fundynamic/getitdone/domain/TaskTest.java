package com.fundynamic.getitdone.domain;

import com.fundynamic.getitdone.domain.exceptions.MustAssignToSubTaskException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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


	@Test
	public void mustReturnEstimatedHoursWhenTaskHasNoSubtasks() {
		Task task = new Task("someTask", 10);
		org.junit.Assert.assertEquals(10, task.getEstimatedHours());
	}

	@Test
	public void mustReturnFinishedWhenEstimatedHoursIsZeroAndInitialEstimatedIsHigherAndBurnedIsHigher() {
		task.setEstimatedHours(0);
		task.setBurnedHours(1);
		task.setInitialEstimatedHours(1);

		Assert.assertTrue(task.isFinished());
	}

	@Test
	public void mustAddTagToTask() {
		task.addTag("cursussen");
		List<String> tags = task.getTags();
		Assert.assertTrue(tags.contains("cursussen"));
	}

	@Test
	public void mustDoNothingWhenTagIsEmptyOrNull() {
		task.addTag("");
		task.addTag(null);
		Assert.assertEquals(0, task.getTags().size());
	}

}
