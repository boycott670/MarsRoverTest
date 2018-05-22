package com.github.javadojo;

final class MarsRoverPathReporter
{
  String report(final ImmutableMarsRoverCoordinates[] coordinates)
  {
    final StringBuffer report = new StringBuffer("X*");
    
    for (int coordinatesIndex = 1; coordinatesIndex < coordinates.length - 1; coordinatesIndex++)
    {
      report.insert(coordinatesIndex, '-');
    }
    
    return report.toString();
  }
}
