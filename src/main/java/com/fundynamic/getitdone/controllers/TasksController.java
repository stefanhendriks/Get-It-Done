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

		modelMap.addAttribute("taskAmount", size);
		int totalHoursEstimated = getTotalHoursEstimated(allTasks);
		modelMap.addAttribute("totalHoursEstimated", totalHoursEstimated);

		int totalHoursBurned = getTotalHoursBurned(allTasks);
		modelMap.addAttribute("totalHoursBurned", totalHoursBurned);

		int totalHoursBurnedFinishedTasks = getTotalHoursBurnedFinishedTasks(allTasks);
		modelMap.addAttribute("totalHoursBurnedFinishedTasks", totalHoursBurnedFinishedTasks);

		int totalAmountOfTasksFinished = getTotalAmountOfTasksFinished(allTasks);
		modelMap.addAttribute("tasksFinished", totalAmountOfTasksFinished);
		return "tasks";
	}

	private int getTotalAmountOfTasksFinished(List<Task> allTasks) {
		int tasksFinished = 0;
		for (Task task : allTasks) {
			if (task.isFinished()) {
			 tasksFinished++;
			}
		}
		return tasksFinished;
	}

	private int getTotalHoursBurnedFinishedTasks(List<Task> allTasks) {
		int burnedHours = 0;
		for (Task task : allTasks) {
			if (task.isFinished()) {
				burnedHours += task.getBurnedHours();
			}
		}
		return burnedHours;
	}

	// TODO: use LambdaJ for this stuff!
	private int getTotalHoursBurned(List<Task> allTasks) {
		int burnedHours = 0;
		for (Task task : allTasks) {
			burnedHours += task.getBurnedHours();
		}
		return burnedHours;
	}

	private int getTotalHoursEstimated(List<Task> allTasks) {
		int hoursEstimated = 0;
		for (Task task : allTasks) {
			hoursEstimated += task.getEstimatedHours();
		}
		return hoursEstimated;
	}


	@RequestMapping(value = "/save")
	public String saveNewTask(ModelMap modelMap, HttpServletRequest request) throws ServletException, IOException {
		Task newTask = new Task(request.getParameter("description"));
		newTask.setEstimatedHours(getHoursForValue(request.getParameter("estimatedHours")));
		newTask.setBurnedHours(getHoursForValue(request.getParameter("burnedHours")));
		String assignedWorker = request.getParameter("assignedWorker");
		newTask.setAssignedWorker(assignedWorker);
		taskRepository.upsertTask(newTask);
		return getAllTasks(modelMap);
	}

//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "action=Save as sub-task")
//	public String saveSubTask(ModelMap modelMap, HttpServletRequest request) throws ServletException, IOException {
//		String taskId = request.getParameter("taskId");
//		Long id = Long.parseLong(taskId);
//		Task taskById = taskRepository.getTaskById(id);
//
//		Task newTask = new Task(request.getParameter("description"));
//		String estimatedHours = request.getParameter("estimatedHours");
//		newTask.setEstimatedHours(Integer.parseInt(estimatedHours));
//
//
//		taskById.addSubTask(newTask);
//
//		taskRepository.upsertTask(taskById);
//
//		return getAllTasks(modelMap);
//	}

	@RequestMapping(value = "/update")
	public String updateTask(ModelMap modelMap, HttpServletRequest request) throws ServletException, IOException {
		return getAllTasks(modelMap);
	}

	private int getHoursForValue(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

}