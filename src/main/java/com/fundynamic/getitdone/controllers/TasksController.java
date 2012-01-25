package com.fundynamic.getitdone.controllers;

import com.fundynamic.getitdone.domain.Task;
import com.fundynamic.getitdone.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {

	protected final Logger logger = LoggerFactory.getLogger(TasksController.class);

	@Autowired
	private TaskRepository taskRepository;


	@RequestMapping
	public String getAllTasks(ModelMap modelMap) throws ServletException, IOException {
		List<Task> allTasks = taskRepository.getAllTasks();
		modelMap.addAttribute("tasks", allTasks);

		int size = allTasks.size();
		if (size > 0) {
			modelMap.addAttribute("lastTaskId", allTasks.get((size-1)).getId());
		}
		return "tasks";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "action=Save as new")
	public String saveNewTask(ModelMap modelMap, HttpServletRequest request) throws ServletException, IOException {
		Task newTask = new Task(request.getParameter("description"));
		String estimatedHours = request.getParameter("estimatedHours");
		newTask.setEstimatedHours(Integer.parseInt(estimatedHours));
		taskRepository.upsertTask(newTask);
		return getAllTasks(modelMap);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "action=Save as sub-task")
	public String saveSubTask(ModelMap modelMap, HttpServletRequest request) throws ServletException, IOException {
		String taskId = request.getParameter("taskId");
		Long id = Long.parseLong(taskId);
		Task taskById = taskRepository.getTaskById(id);

		Task newTask = new Task(request.getParameter("description"));
		String estimatedHours = request.getParameter("estimatedHours");
		newTask.setEstimatedHours(Integer.parseInt(estimatedHours));

		taskById.addSubTask(newTask);

		taskRepository.upsertTask(taskById);

		return getAllTasks(modelMap);
	}

	@RequestMapping(value = "/update")
	public String updateTask(ModelMap modelMap, HttpServletRequest request) throws ServletException, IOException {
		return getAllTasks(modelMap);
	}
}