package com.sqli.challenge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

final class OsFacade
{
  private final Collection<Process> processes = new ArrayList<>();

  private String executionResult;
  private Integer roundRobin;

  void createProcess(final String processName, final String processOptions)
  {
    processes.add(CreateProcessParser.parseCreateProcess(processName, processOptions));
  }

  void run()
  {
    final StringBuilder report = new StringBuilder();

    StringBuilder reportSingleRun = new StringBuilder();

    do
    {
      report.append(reportSingleRun);

      reportSingleRun = new StringBuilder();

      for (final Process process : processes)
      {
        reportSingleRun.append(
            ExecutionResultReporter.reportExecutionResult(process.getName(), process.run(Optional.ofNullable(roundRobin)
                .orElse(process.countOfRemainingOptions()))));
      }
    }
    while (!reportSingleRun.toString().isEmpty());

    executionResult = report.toString();
  }

  String getExecutionResult()
  {
    return executionResult;
  }

  void useRoundRobin(final int roundRobin)
  {
    this.roundRobin = roundRobin;
  }
}
