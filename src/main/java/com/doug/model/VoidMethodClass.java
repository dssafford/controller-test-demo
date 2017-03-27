package com.doug.model;

import com.doug.exception.TodoNotFoundException;

public class VoidMethodClass {
	
	public void voidMethodThrowingException(boolean check) throws TodoNotFoundException{
		if (check) {
			throw new TodoNotFoundException("hey");
		}
	}

}
