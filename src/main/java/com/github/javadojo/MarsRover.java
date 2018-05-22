package com.github.javadojo;

import com.github.javadojo.parsers.DefaultMarsRoverPathParser;
import com.github.javadojo.parsers.MarsRoverPathParser;
import com.github.javadojo.reporters.DefaultMarsRoverPathReporter;
import com.github.javadojo.reporters.MarsRoverPathReporter;

public final class MarsRover
{
  public static final String LINE_SEPARATOR = String.format("%n");
  
  private final StringBuffer path;
  
  private final MarsRoverPathParser pathParser;
  
  private final MarsRoverPathReporter pathReporter;
  
  MarsRover(final String path)
  {
    this.path = new StringBuffer(path);
    
    pathParser = new DefaultMarsRoverPathParser();
    
    pathReporter = new DefaultMarsRoverPathReporter();
  }
  
  MarsRover turnLeft()
  {
    path.append('l');
    
    return this;
  }
  
  MarsRover turnRight()
  {
    path.append('r');
    
    return this;
  }
  
  MarsRover moveForward()
  {
    path.append('s');
    
    return this;
  }
  
  String path()
  {
    pathParser.setPath(path.toString());
    
    pathReporter.setPath(pathParser.parsePath());
    
    return pathReporter.reportPath();
  }
}
