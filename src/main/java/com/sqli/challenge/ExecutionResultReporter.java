package com.sqli.challenge;

import java.util.Arrays;
import java.util.stream.Collectors;

final class ExecutionResultReporter
{
  private ExecutionResultReporter()
  {

  }

  static String reportExecutionResult(final String processName, final String[] processOptions)
  {
    return Arrays.stream(processOptions)
        .map(option -> String.format("<<%s>>%s", processName, option))
        .collect(Collectors.joining());
  }
}
