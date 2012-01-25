package com.fundynamic.getitdone.repositories;


import com.fundynamic.getitdone.domain.Task;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class MemoryTaskRepositoryTest {

	private MemoryTaskRepository memoryTaskRepository;

	@Before
	public void setUp() throws Exception {
		memoryTaskRepository = new MemoryTaskRepository();
	}

	@Test
	public void mustSaveNewTask() {
		Task newTask = new Task("Some task");

		Task savedTask = memoryTaskRepository.upsertTask(newTask);

		Assert.assertTrue(newTask == savedTask);
		Assert.assertEquals(0L, savedTask.getId());
	}

	@Test
	public void mustUpdateExistingTask() {
		Task newTask = new Task("Some task");
		Task savedTask = memoryTaskRepository.upsertTask(newTask);

		savedTask.setDescription("New description!");
		Task updatedTask = memoryTaskRepository.upsertTask(savedTask);

		Assert.assertTrue(updatedTask == savedTask);
		Assert.assertEquals(0L, updatedTask.getId());
		Assert.assertEquals("New description!", updatedTask.getDescription());
	}

	@Test
	public void mustReturnSavedTaskById() {
		memoryTaskRepository.upsertTask(new Task("Some task #0"));
		memoryTaskRepository.upsertTask(new Task("Some task #1"));
		memoryTaskRepository.upsertTask(new Task("Some task #2"));

		// Act
		Task taskZero = memoryTaskRepository.getTaskById(0L);
		Assert.assertEquals("Some task #0", taskZero.getDescription());

		Task taskOne = memoryTaskRepository.getTaskById(1L);
		Assert.assertEquals("Some task #1", taskOne.getDescription());
	}

	@Test
	public void mustReturnIdOneWhenIdZeroIsTaken() {
		memoryTaskRepository.upsertTask(new Task("")); // will occupy id 0

		long newId = memoryTaskRepository.getNewId();
		Assert.assertEquals(1L, newId);
	}
}
