package com.sqli.challenge;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Queue;

final class Process
{
  static Process createProcess(final String name, final String... tasks)
  {
    return new Process(name, tasks);
  }

  private final String name;
  private final Queue<String> tasks;

  private Process(final String name, final String[] tasks)
  {
    this.name = name;
    this.tasks = new ArrayDeque<>(Arrays.asList(tasks));
  }

  String[] run(int roundRobin)
  {
    final Collection<String> tasksReports = new ArrayList<>();
    String currentTask = null;

    while (roundRobin-- > 0 && (currentTask = tasks.poll()) != null)
    {
      tasksReports.add(reportTask(currentTask));
    }

    return tasksReports.toArray(new String[tasksReports.size()]);
  }

  private String reportTask(final String task)
  {
    return String.format("<<%s>>%s", name, task);
  }

  boolean isDone()
  {
    return tasks.isEmpty();
  }

  int remainingTasks()
  {
    return tasks.size();
  }
}
