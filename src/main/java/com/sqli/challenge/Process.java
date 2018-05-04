package com.sqli.challenge;

final class Process
{
  private final String code;
  private final String[] instructions;
  
  Process(String code, String[] instructions)
  {
    this.code = code;
    this.instructions = instructions;
  }
  
  void accept(final ExecutionVisitor executionVisitor)
  {
    executionVisitor.visitProcess(code, instructions);
  }
}
