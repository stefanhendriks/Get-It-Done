package com.fundynamic.getitdone.repositories;

import com.fundynamic.getitdone.domain.Task;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Component
public class MemoryTaskRepository implements TaskRepository{

	private HashMap<Long, Task> tasksHashMap = new HashMap<Long, Task>();

	public Task getTaskById(long id) {
		return tasksHashMap.get(id);
	}

	public Task upsertTask(Task task) {
		long id = task.getId();
		if (id < 0) {
			id = getNewId();
			if (id < 0) throw new IllegalStateException("Could not get id to save new task");
			task.setId(id);
		}
		tasksHashMap.put(id, task);
		return task;
	}

	public List<Task> getAllTasks() {
		return new LinkedList<Task>(tasksHashMap.values());
	}

	protected long getNewId() {
		for (long id = 0; id < 2000L; id++) {
			if (!tasksHashMap.containsKey(id)) {
				  return id;
			}
		}
		return -1;
	}
}
