package com.github.javadojo;

import java.util.LinkedHashMap;

import com.github.javadojo.parsers.DefaultMarsRoverPathParser;
import com.github.javadojo.parsers.MarsRoverPathParser;
import com.github.javadojo.reporters.DefaultMarsRoverPathReporter;
import com.github.javadojo.reporters.MarsRoverPathReporter;

public final class MarsRover
{
  public static final String LINE_SEPARATOR = String.format("%n");

  private final StringBuffer path;

  private LinkedHashMap<ImmutableMarsRoverCoordinates, MarsRoverMove> currentlyParsedPath;

  private final MarsRoverPathParser pathParser;

  private final MarsRoverPathReporter pathReporter;
  
  MarsRover(final String path)
  {
    this.path = new StringBuffer(path);
    
    currentlyParsedPath = new LinkedHashMap<>();
    
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
    
    path.delete(0, path.length());
    
    currentlyParsedPath = pathParser.parsePathFrom(currentlyParsedPath);
    
    pathReporter.setPath(currentlyParsedPath);
    
    return pathReporter.reportPath();
  }
}
