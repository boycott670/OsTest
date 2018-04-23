package com.sqli.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

final class OsFacade
{
  private final Collection<Process> processes = new ArrayList<>();
  private Integer roundRobin = null;
  private String executionResult = null;

  void createProcess(final String processName, final String processTasks)
  {
    processes.add(CreateProcessParser.parseCreateProcess(processName, processTasks));
  }

  void run()
  {
    final StringBuilder report = new StringBuilder();

    while (!areAllProcessesDone())
    {
      report.append(processes.stream()
          .map(process -> process.run(Optional.ofNullable(roundRobin)
              .orElse(process.remainingTasks())))
          .flatMap(Arrays::stream)
          .collect(Collectors.joining()));
    }

    executionResult = report.toString();
  }

  void useRoundRobin(final int roundRobin)
  {
    this.roundRobin = roundRobin;
  }

  String getExecutionResult()
  {
    return executionResult;
  }

  private boolean areAllProcessesDone()
  {
    return processes.stream()
        .allMatch(Process::isDone);
  }
}
