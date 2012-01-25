package com.fundynamic.getitdone.repositories;


import com.fundynamic.getitdone.domain.Task;

import java.util.List;

public interface TaskRepository {

	Task getTaskById(long id);

	Task upsertTask(Task task);

	List<Task> getAllTasks();
}
