package com.github.javadojo.parsers;

import java.util.Map;

import com.github.javadojo.ImmutableMarsRoverCoordinates;
import com.github.javadojo.MarsRoverMove;

public interface MarsRoverPathParser
{
  void setPath(final String path);
  
  Map<ImmutableMarsRoverCoordinates, MarsRoverMove> parsePath();
}
