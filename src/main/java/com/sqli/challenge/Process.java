package com.sqli.challenge;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Queue;

final class Process
{
  private final String name;
  private final Queue<String> tasks;
  
  Process(final String name, final String[] tasks)
  {
    this.name = name;
    this.tasks = new ArrayDeque<>(Arrays.asList(tasks));
  }
  
  boolean isDone()
  {
    return tasks.isEmpty();
  }
  
  private String reportTask(final String task)
  {
    return String.format("<<%s>>%s", name, task);
  }
  
  String[] run(final Optional<Integer> roundRobin)
  {
    int effectiveRoundRobin = roundRobin
        .map(nonNullRoundRobin -> Math.min(nonNullRoundRobin, tasks.size()))
        .orElseGet(tasks::size);
    
    final Collection<String> tasksReports = new ArrayList<>();

    while (effectiveRoundRobin-- > 0)
    {
      tasksReports.add(reportTask(tasks.remove()));
    }

    return tasksReports.toArray(new String[0]);
  }
}
