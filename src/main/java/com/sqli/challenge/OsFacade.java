package com.sqli.challenge;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

final class OsFacade
{
  private final Map<String, Process> processes;

  OsFacade()
  {
    processes = new LinkedHashMap<>();
  }

  void createProcess(final String processName, final String instructions)
  {
    processes.put(processName, new Process(instructions.split(";")));
  }

  void run()
  {
    processes.values()
        .forEach(Process::run);
  }

  String getExecutionResult()
  {
    return processes.entrySet()
        .stream()
        .map(entry -> entry.getValue()
            .getExecutionResult(entry.getKey()))
        .collect(Collectors.joining());
  }
}
