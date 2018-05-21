package com.sqli.challenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OsTest
{
  @Test
  public void testExecuteProcess()
  {
    OsFacade osFacade = new OsFacade();
    osFacade.createProcess("process1", "op1;op2;op2;op1");
    osFacade.run();
    assertEquals("<<process1>>op1<<process1>>op2<<process1>>op2<<process1>>op1", osFacade.getExecutionResult());
  }

  @Test
  public void testExecuteTwoProcess()
  {
    OsFacade osFacade = new OsFacade();
    osFacade.createProcess("process1", "op1;op2;op2;op1");
    osFacade.createProcess("process2", "op1;op1");
    osFacade.run();
    assertEquals("<<process1>>op1<<process1>>op2<<process1>>op2<<process1>>op1<<process2>>op1<<process2>>op1",
        osFacade.getExecutionResult());
  }
}
