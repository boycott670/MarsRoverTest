package com.github.javadojo.parsers;

import java.util.LinkedHashMap;

import com.github.javadojo.ImmutableMarsRoverCoordinates;
import com.github.javadojo.MarsRoverMove;

public interface MarsRoverPathParser
{
  void setPath(final String path);
  
  LinkedHashMap<ImmutableMarsRoverCoordinates, MarsRoverMove> parsePath();
  
  LinkedHashMap<ImmutableMarsRoverCoordinates, MarsRoverMove> parsePathFrom(final LinkedHashMap<ImmutableMarsRoverCoordinates, MarsRoverMove> currentlyParsedPath);
}
