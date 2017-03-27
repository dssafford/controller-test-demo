package com.doug.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by doug on 3/24/17.
 */

@Entity
public class Todo {
	public static final int MAX_LENGTH_DESCRIPTION = 500;
	public static final int MAX_LENGTH_TITLE = 100;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Length(max = Todo.MAX_LENGTH_DESCRIPTION)
	private String description;

	@Length(max = Todo.MAX_LENGTH_TITLE)
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

