package com.github.javadojo.reporters;

import java.util.Map;

import com.github.javadojo.ImmutableMarsRoverCoordinates;
import com.github.javadojo.MarsRoverMove;

public interface MarsRoverPathReporter
{
  void setPath(final Map<ImmutableMarsRoverCoordinates, MarsRoverMove> path);

  String reportPath();
}
