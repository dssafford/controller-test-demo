package com.doug.controller;

import com.doug.exception.TodoNotFoundException;
import com.doug.model.Todo;
import com.doug.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private TodoRepository service;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getIndex() {

		return "index";
	}
@RequestMapping(value = "/todo/add", method = RequestMethod.GET)
public String showAddTodoForm(Model model) {

	model.addAttribute("todo", new Todo());

	return "newaddTodo";
}

	@RequestMapping(value = "/todo/add", method = RequestMethod.POST)
	public String addSubmit(@ModelAttribute Todo todo) {

		service.save(todo);
		String hey = "hey";

		return "index";
	}

	@RequestMapping(value = "/todo/delete/{id}", method = RequestMethod.GET)
	public String deleteById(@PathVariable("id") Long id, RedirectAttributes attributes) throws TodoNotFoundException {


		service.delete(id);


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

