package com.doug.model;

import com.doug.exception.TodoNotFoundException;

/**
 * Created by doug on 3/26/17.
 */
public class Doug {
//	private String title;
//	private String description;
//
//	public Doug(String title, String description) {
//		this.title = title;
//		this.description = description;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	public void voidMethodThrowingException(boolean check) throws TodoNotFoundException {
		if (check) {
			throw new TodoNotFoundException("hey");
		}
	}
}
