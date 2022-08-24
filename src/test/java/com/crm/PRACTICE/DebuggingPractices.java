package com.crm.PRACTICE;

import org.testng.annotations.Test;

public class DebuggingPractices {
  @Test
  public void demo()
  {
	  System.out.println("demo");
	  DebuggingPractices d = new DebuggingPractices();
	  d.div(10, 2);
	  
  }
  
  public int div(int a,int b)
  {
	  int c = a/b;
	  return c;
  }
}
