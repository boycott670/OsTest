package com.sqli.challenge;

final class CreateProcessParser
{
  private CreateProcessParser()
  {

  }

  static Process parseCreateProcess(final String processName, final String processOptions)
  {
    return Process.createProcess(processName, processOptions.split(";"));
  }
}
