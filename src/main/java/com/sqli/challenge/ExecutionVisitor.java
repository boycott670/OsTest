package com.sqli.challenge;

interface ExecutionVisitor
{
  void reset();
  
  void visitProcess(final String code, final String[] instructions);
  
  void setRoundRobin(final int roundRobin);
  
  String executionResult();
}
