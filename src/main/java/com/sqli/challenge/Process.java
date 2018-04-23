package com.sqli.challenge;

import java.util.stream.IntStream;

final class Process
{
  static Process createProcess(final String name, final String... options)
  {
    return new Process(name, options);
  }

  private final String name;
  private final String[] options;

  private int currentOptionIndex = 0;

  private Process(String name, String[] options)
  {
    this.name = name;
    this.options = options;
  }

  String[] run(final int roundRobin)
  {
    final String[] runnedOptions = IntStream.range(currentOptionIndex, currentOptionIndex + roundRobin)
        .mapToObj(index -> options[index])
        .limit(countOfRemainingOptions())
        .toArray(String[]::new);

    currentOptionIndex += roundRobin;

    return runnedOptions;
  }

  int countOfRemainingOptions()
  {
    return Math.max(0, options.length - currentOptionIndex);
  }

  String getName()
  {
    return name;
  }
}
