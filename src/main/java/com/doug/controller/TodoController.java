package com.doug.controller;

import com.doug.exception.TodoNotFoundException;
import com.doug.model.Todo;
import com.doug.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by doug on 3/24/17.
 */
@Controller
public class TodoController {


	private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

	protected static final String FEEDBACK_MESSAGE_KEY_TODO_ADDED = "feedback.message.todo.added";
	protected static final String FEEDBACK_MESSAGE_KEY_TODO_UPDATED = "feedback.message.todo.updated";
	protected static final String FEEDBACK_MESSAGE_KEY_TODO_DELETED = "feedback.message.todo.deleted";

	protected static final String FLASH_MESSAGE_KEY_ERROR = "errorMessage";
	protected static final String FLASH_MESSAGE_KEY_FEEDBACK = "feedbackMessage";

	protected static final String MODEL_ATTRIBUTE_TODO = "todo";
	protected static final String MODEL_ATTRIBUTE_TODO_LIST = "todos";

	protected static final String PARAMETER_TODO_ID = "id";

	protected static final String REQUEST_MAPPING_TODO_LIST = "/";
//	protected static final String REQUEST_MAPPING_TODO_VIEW = "/todo/{id}";
	protected static final String REQUEST_MAPPING_TODO_VIEW = "index";
	protected static final String PATH_TODO_ADD = "/todo/add";
	protected static final String VIEW_TODO_ADD = "newaddtodo";

	protected static final String VIEW_TODO_LIST = "todo/list";
	protected static final String VIEW_TODO_UPDATE = "todo/update";
	protected static final String VIEW_TODO_VIEW = "todo/view";
	private static final String FEEDBACK_MESSAGE = "feedbackMessage";
	private static final String FIELD_DESCRIPTION = "description";
	private static final String FIELD_TITLE = "title";

	@Autowired
	private TodoRepository service;

	@Autowired
	private MessageSource messageSource;

//	@Autowired
//	private Validator validator;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getIndex() {

		return "index";
	}
@RequestMapping(value = "VIEW_TODO_ADD", method = RequestMethod.GET)
public String showAddTodoForm(Model model) {

	model.addAttribute("todo", new Todo());

	return "newaddtodo";
}
	public String doException() throws TodoNotFoundException {
		return "shit";
	}

	@RequestMapping(value = "VIEW_TODO_ADD", method = RequestMethod.POST)
	public String addSubmit(@ModelAttribute Todo todo) {

		service.save(todo);

		return "index";
	}

	@RequestMapping(value = "/todo/delete/{id}", method = RequestMethod.GET)
	public String deleteById(@PathVariable("id") Long id) throws TodoNotFoundException {

		Todo todo = service.findOne(id);
		service.delete(todo);


		return "index";
	}

	@RequestMapping(value = "/todo/list", method = RequestMethod.GET)
	public String findAll(Model model) {


		List<Todo> mytodos = service.findAll();
		model.addAttribute("todos", mytodos);

		return "todoList";
	}

	@RequestMapping(value = "/todo/show/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable("id") Long id, Model model) throws TodoNotFoundException {


		Todo todo = service.findOne(id);

		model.addAttribute("todo", todo);

		return "showTodo";
	}

	@RequestMapping(value = "/todo/update/{id}", method = RequestMethod.GET)
	public String showUpdateTodoForm(@PathVariable("id") Long id, Model model) throws TodoNotFoundException {

		Todo updated = service.findOne(id);

		model.addAttribute("todo", updated);

		return "todoUpdate";

	}

	@RequestMapping(value = "/todo/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute Todo todo, BindingResult result, RedirectAttributes attributes) throws TodoNotFoundException {

		Todo updated = service.findOne(todo.getId());

		updated.setDescription(todo.getDescription());
		updated.setTitle(todo.getTitle());

		service.save(todo);


		return "redirect:/todo/list";
	}

	private Todo constructFormObjectForUpdateForm(Todo updated) {
		Todo dto = new Todo();

//		dto.setId(updated.getId());
//		dto.setDescription(updated.getDescription());
//		dto.setTitle(updated.getTitle());

		return dto;
	}
}

