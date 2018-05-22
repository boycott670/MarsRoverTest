package com.github.javadojo;

final class MarsRover
{
  static final String LINE_SEPARATOR = String.format("%n");
  
  private final MarsRoverPathConstructor pathConstructor;
  
  MarsRover(final String path)
  {
    pathConstructor = new MarsRoverPathConstructor(path);
  }
  
  String path()
  {
    return new MarsRoverPathReporter().report(pathConstructor.path()) + LINE_SEPARATOR;
  }
}
