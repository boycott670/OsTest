package com.sqli.challenge;

import java.util.Collection;
import java.util.LinkedHashSet;

final class OsFacade
{
  private final ExecutionVisitor executionVisitor = new DefaultExecutionVisitor();
  
  private final Collection<Process> processes = new LinkedHashSet<>();
  
  private String executionResult;
  
  void createProcess(final String code, final String instructions)
  {
    processes.add(new Process(code, instructions.split(";")));
  }
  
  void run()
  {
    executionVisitor.reset();
    processes.stream().forEach(process -> process.accept(executionVisitor));
    executionResult = executionVisitor.executionResult();
  }
  
  String getExecutionResult()
  {
    return executionResult;
  }
  
  void useRoundRobin(final int roundRobin)
  {
    executionVisitor.setRoundRobin(roundRobin);
  }
}
