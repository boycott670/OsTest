package com.sqli.challenge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

final class OsFacade
{
  private Integer roundRobin;
  
  private final Map<String, Process> processes;
  
  private final Collection<String> executionResults;

  OsFacade()
  {
    processes = new LinkedHashMap<>();
    
    executionResults = new ArrayList<>();
  }
  
  void useRoundRobin(final int roundRobin)
  {
    this.roundRobin = roundRobin;
  }

  void createProcess(final String processName, final String instructions)
  {
    processes.put(processName, new Process(instructions.split(";")));
  }

  void run()
  {
    while (processes.values().stream().anyMatch(Process::hasRemainingInstructionsToRun))
    {
      processes.values()
          .forEach(process -> process.run(roundRobin));

      final String executionResult = processes.entrySet()
          .stream()
          .map(entry -> entry.getValue()
              .getExecutionResult(entry.getKey()))
          .collect(Collectors.joining());

      executionResults.add(executionResult);
    }
  }

  String getExecutionResult()
  {
    return executionResults.stream()
        .collect(Collectors.joining());
  }
}
