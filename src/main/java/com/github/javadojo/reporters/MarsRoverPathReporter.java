package com.github.javadojo.reporters;

import java.util.LinkedHashMap;

import com.github.javadojo.ImmutableMarsRoverCoordinates;
import com.github.javadojo.MarsRoverMove;

public interface MarsRoverPathReporter
{
  void setPath(final LinkedHashMap<ImmutableMarsRoverCoordinates, MarsRoverMove> path);

  String reportPath();
}
