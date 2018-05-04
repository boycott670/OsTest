package com.sqli.challenge;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Queue;

final class DefaultExecutionVisitor implements ExecutionVisitor
{

  private final Map<String, Queue<String>> processes = new LinkedHashMap<>();
  
  private Integer roundRobin = null;
  
  @Override
  public void reset()
  {
    processes.clear();
  }

  @Override
  public void visitProcess(String code, String[] instructions)
  {
    processes.put(code, new ArrayDeque<>(Arrays.asList(instructions)));
  }

  @Override
  public void setRoundRobin(int roundRobin)
  {
    this.roundRobin = roundRobin;
  }

  @Override
  public String executionResult()
  {
    final StringBuilder report = new StringBuilder();
    
    while(!processes.isEmpty())
    {
      for(final Iterator<Entry<String, Queue<String>>> iterator = processes.entrySet().iterator() ; iterator.hasNext() ;)
      {
        final Entry<String, Queue<String>> process = iterator.next();
        
        int roundRobin = Optional.ofNullable(this.roundRobin).orElse(process.getValue().size());
        
        while(roundRobin-- > 0)
        {
          report.append(String.format("<<%s>>%s", process.getKey(), process.getValue().remove()));
          
          if (process.getValue().isEmpty())
          {
            iterator.remove();
            break;
          }
        }
      }
    }
    
    return report.toString();
  }

}
