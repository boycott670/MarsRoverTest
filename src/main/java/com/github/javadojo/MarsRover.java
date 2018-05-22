package com.github.javadojo;

import com.github.javadojo.parsers.DefaultMarsRoverPathParser;
import com.github.javadojo.parsers.MarsRoverPathParser;
import com.github.javadojo.reporters.DefaultMarsRoverPathReporter;
import com.github.javadojo.reporters.MarsRoverPathReporter;

public final class MarsRover
{
  public static final String LINE_SEPARATOR = String.format("%n");
  
  private final MarsRoverPathParser pathParser;
  
  private final MarsRoverPathReporter pathReporter;
  
  MarsRover(final String path)
  {
    pathParser = new DefaultMarsRoverPathParser();
    
    pathReporter = new DefaultMarsRoverPathReporter();
    
    pathParser.setPath(path);
  }
  
  String path()
  {
    pathReporter.setPath(pathParser.parsePath());
    
    return pathReporter.reportPath();
  }
}
