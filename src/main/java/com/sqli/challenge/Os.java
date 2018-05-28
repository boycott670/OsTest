package com.sqli.challenge;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Stream;

final class Os
{
  private final Queue<Process> processes;
  
  Os()
  {
    processes = new ArrayDeque<>();
  }
  
  void createProcess(final String processName, final String... processTasks)
  {
    processes.add(new Process(processName, processTasks));
  }
  
  String[] run()
  {
    return run(Optional.empty());
  }
  
  String[] run(final int roundRobin)
  {
    return run(Optional.ofNullable(roundRobin));
  }
  
  private String[] run(final Optional<Integer> roundRobin)
  {
    Stream<String> results = Stream.empty();
    
    while(!processes.isEmpty())
    {
      final Process processToRun = processes.remove();
      
      results = Stream.concat(results, Arrays.stream(processToRun.run(roundRobin)));
      
      if (!processToRun.isDone())
      {
        processes.add(processToRun);
      }
    }
    
    return results.toArray(String[]::new);
  }
}
