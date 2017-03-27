package com.doug.controller;

import com.doug.exception.TodoNotFoundException;
import com.doug.model.VoidMethodClass;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VoidMethodClassTest {
	
	private VoidMethodClass mock;
	
	@Test
	public void testVoidMethodThrowingExcetion() throws TodoNotFoundException {
		mock = Mockito.mock(VoidMethodClass.class);
		Mockito.doThrow(new IllegalArgumentException()).when(mock).voidMethodThrowingException(false);
		mock.voidMethodThrowingException(true);
		Mockito.doThrow(new IllegalArgumentException()).when(mock).voidMethodThrowingException(true);
		try {
			mock.voidMethodThrowingException(true);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			
		}		
		
	}

}
