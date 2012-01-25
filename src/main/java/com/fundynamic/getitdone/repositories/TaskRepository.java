package com.fundynamic.getitdone.repositories;


import com.fundynamic.getitdone.domain.Task;

public interface TaskRepository {

	Task getTaskById(long id);

	Task upsertTask(Task task);

}
