package com.sqli.challenge;

import java.util.Collection;
import java.util.LinkedHashSet;

final class OsFacade
{
  private final ExecutionVisitor executionVisitor = new DefaultExecutionVisitor();
  
  private final Collection<Process> processes = new LinkedHashSet<>();
  
  void createProcess(final String code, final String instructions)
  {
    processes.add(new Process(code, instructions.split(";")));
  }
  
  void run()
  {
    processes.stream().forEach(process -> process.accept(executionVisitor));
  }
  
  String getExecutionResult()
  {
    final String result = executionVisitor.executionResult();
    executionVisitor.reset();
    return result;
  }
  
  void useRoundRobin(final int roundRobin)
  {
    executionVisitor.setRoundRobin(roundRobin);
  }
}
