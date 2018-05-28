package com.sqli.challenge;

import java.util.Optional;

final class OsFacade
{
  private final Os os;
  
  private Optional<Integer> roundRobin;
  
  private String[] executionResult;
  
  OsFacade()
  {
    os = new Os();
    
    roundRobin = Optional.empty();
  }
  
  void createProcess(final String processName, final String processTasks)
  {
    os.createProcess(processName, processTasks.split(";"));
  }
  
  void run()
  {
    executionResult = roundRobin.map(os::run).orElseGet(os::run);
  }
  
  void useRoundRobin(final int roundRobin)
  {
    this.roundRobin = Optional.of(roundRobin);
  }
  
  String getExecutionResult()
  {
    return String.join("", executionResult);
  }
}
