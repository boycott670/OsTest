package com.sqli.challenge;

final class CreateProcessParser
{
  private CreateProcessParser()
  {

  }

  static Process parseCreateProcess(final String processName, final String processTasks)
  {
    return Process.createProcess(processName, processTasks.split(";"));
  }
}
