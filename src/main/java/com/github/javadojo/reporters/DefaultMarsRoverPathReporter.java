package com.github.javadojo.reporters;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.javadojo.ImmutableMarsRoverCoordinates;
import com.github.javadojo.MarsRover;
import com.github.javadojo.MarsRoverMove;

public final class DefaultMarsRoverPathReporter implements MarsRoverPathReporter
{
  
  private Map<ImmutableMarsRoverCoordinates, MarsRoverMove> path;
  
  private int edgePathPointCoordinates(final Comparator<ImmutableMarsRoverCoordinates> comparator,
      final ToIntFunction<ImmutableMarsRoverCoordinates> metricResolver)
  {
    return metricResolver.applyAsInt(path.keySet()
        .stream()
        .min(comparator)
        .get());
  }

  private int minRow()
  {
    return edgePathPointCoordinates(Comparator.comparingInt(ImmutableMarsRoverCoordinates::getRow),
        ImmutableMarsRoverCoordinates::getRow);
  }

  private int maxRow()
  {
    return edgePathPointCoordinates(Comparator.comparingInt(ImmutableMarsRoverCoordinates::getRow)
        .reversed(), ImmutableMarsRoverCoordinates::getRow);
  }

  private int minColumn()
  {
    return edgePathPointCoordinates(Comparator.comparingInt(ImmutableMarsRoverCoordinates::getColumn),
        ImmutableMarsRoverCoordinates::getColumn);
  }

  private int maxColumn()
  {
    return edgePathPointCoordinates(Comparator.comparingInt(ImmutableMarsRoverCoordinates::getColumn)
        .reversed(), ImmutableMarsRoverCoordinates::getColumn);
  }

  private ImmutableMarsRoverCoordinates[][] pathSpace()
  {
    return IntStream.rangeClosed(minRow(), maxRow())
        .mapToObj(row -> IntStream.rangeClosed(minColumn(), maxColumn())
            .mapToObj(column -> ImmutableMarsRoverCoordinates.of(row, column))
            .toArray(ImmutableMarsRoverCoordinates[]::new))
        .toArray(ImmutableMarsRoverCoordinates[][]::new);
  }

  @Override
  public void setPath(Map<ImmutableMarsRoverCoordinates, MarsRoverMove> path)
  {
    this.path = path;
  }

  @Override
  public String reportPath()
  {
    return Arrays.stream(pathSpace())
        .map(pathSpaceRow -> Arrays.stream(pathSpaceRow)
            .map(pathPoint -> String.valueOf(Optional.ofNullable(path.get(pathPoint))
                .map(MarsRoverMove::draw)
                .orElse(' ')))
            .collect(Collectors.joining()))
        .collect(Collectors.joining(MarsRover.LINE_SEPARATOR)) + MarsRover.LINE_SEPARATOR;
  }

}
