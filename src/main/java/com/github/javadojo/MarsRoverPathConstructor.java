package com.github.javadojo;

import java.util.ArrayList;
import java.util.Collection;

final class MarsRoverPathConstructor
{
  private final Collection<ImmutableMarsRoverCoordinates> coordinates;
  
  MarsRoverPathConstructor(final String path)
  {
    coordinates = new ArrayList<>();
    
    final char[] parsedPath = path.toCharArray();
    
    ImmutableMarsRoverCoordinates lastAdded;
    
    coordinates.add(lastAdded = ImmutableMarsRoverCoordinates.of(0, 0));
    
    ImmutableMarsRoverCoordinates increment = ImmutableMarsRoverCoordinates.of(0, 1);
    
    for (final char pathSegment : parsedPath)
    {
      coordinates.add(ImmutableMarsRoverCoordinates.plus(lastAdded, increment));
    }
  }
  
  ImmutableMarsRoverCoordinates[] path()
  {
    return coordinates.toArray(new ImmutableMarsRoverCoordinates [coordinates.size()]);
  }
}
