package com.github.javadojo.parsers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedHashMap;

import com.github.javadojo.ImmutableMarsRoverCoordinates;
import com.github.javadojo.MarsRoverMove;

public final class DefaultMarsRoverPathParser implements MarsRoverPathParser
{
  
  private static enum Heading
  {
    RIGHT,
    UP,
    LEFT,
    BOTTOM;
  }
  
  private final ImmutableMarsRoverCoordinates[] directions = new ImmutableMarsRoverCoordinates[]
  {
      ImmutableMarsRoverCoordinates.of(0, 1),
      ImmutableMarsRoverCoordinates.of(1, 0),
      ImmutableMarsRoverCoordinates.of(0, -1),
      ImmutableMarsRoverCoordinates.of(-1, 0)
  };
  
  private Heading currentHeading = Heading.RIGHT;
  
  private boolean isTakingSample = false;
  
  private void turnLeft()
  {
    final int nextHeadingOrdinal = currentHeading.ordinal() - 1;
    
    currentHeading = Heading.values()[nextHeadingOrdinal < 0 ? 4 - -nextHeadingOrdinal : nextHeadingOrdinal];
  }
  
  private void turnRight()
  {
    currentHeading = Heading.values()[(currentHeading.ordinal() + 1) % 4];
  }
  
  private void takeSample()
  {
    isTakingSample = true;
  }
  
  private boolean isCurrentlyGoingLeftOrRight()
  {
    return Arrays.asList(Heading.LEFT, Heading.RIGHT).contains(currentHeading);
  }
  
  private boolean isValidNextPathPointIndex(final int currentPathPointIndex)
  {
    return currentPathPointIndex + 1 < path.length;
  }
  
  private boolean isGoingToTurn(final int currentPathPointIndex)
  {
    return isValidNextPathPointIndex(currentPathPointIndex) && Arrays.asList('l', 'r').contains(path[currentPathPointIndex + 1]);
  }
  
  private boolean isGoingToTakeSample(final int currentPathPointIndex)
  {
    return isValidNextPathPointIndex(currentPathPointIndex) && path[currentPathPointIndex + 1] == 'S';
  }
  
  private ImmutableMarsRoverCoordinates nextCoordinates(final ImmutableMarsRoverCoordinates currentCoordinates)
  {
    return ImmutableMarsRoverCoordinates.plus(currentCoordinates, directions[currentHeading.ordinal()]);
  }
  
  private void incrementToNextMove()
  {
    lastAddedCoordinates = nextCoordinates(lastAddedCoordinates);
  }
  
  private void addMove(MarsRoverMove move, boolean isLastPathPointIndex)
  {
    if (isTakingSample)
    {
      moves.put(lastAddedCoordinates, MarsRoverMove.TAKE_SAMPLE_MOVE);
      
      isTakingSample = false;
    }
    else
    {
      if (moves.containsKey(lastAddedCoordinates))
      {
        if (isLastPathPointIndex || moves.get(lastAddedCoordinates).canBeOverridenOnSecondPass())
        {
          moves.remove(lastAddedCoordinates);
          
          moves.put(lastAddedCoordinates, isLastPathPointIndex ? move : MarsRoverMove.OVERLAP_MOVE);
        }
      }
      else
      {
        moves.put(lastAddedCoordinates, move);
      }
    }
  }
  
  private void addMoveWhileIncrementing(MarsRoverMove move, boolean isLastPathPointIndex)
  {
    incrementToNextMove();
    addMove(move, isLastPathPointIndex);
  }

  private char[] path;
  
  private LinkedHashMap<ImmutableMarsRoverCoordinates, MarsRoverMove> moves;
  
  private ImmutableMarsRoverCoordinates lastAddedCoordinates;

  @Override
  public void setPath(String path)
  {
    this.path = path.toCharArray();
  }

  @Override
  public LinkedHashMap<ImmutableMarsRoverCoordinates, MarsRoverMove> parsePath()
  {
    return parsePathFrom(new LinkedHashMap<>());
  }
  
  @Override
  public LinkedHashMap<ImmutableMarsRoverCoordinates, MarsRoverMove> parsePathFrom(LinkedHashMap<ImmutableMarsRoverCoordinates, MarsRoverMove> currentlyParsedPath)
  {
    moves = currentlyParsedPath;
    
    if (moves.isEmpty())
    {
      lastAddedCoordinates = ImmutableMarsRoverCoordinates.of(0, 0);
      addMove(MarsRoverMove.INITIAL_MOVE, false);
    }
    else
    {
      lastAddedCoordinates = new ArrayDeque<>(moves.keySet()).pollLast();
    }
    
    for (int pathPointIndex = 0; pathPointIndex < path.length; pathPointIndex++)
    {
      if (path[pathPointIndex] == 'l')
      {
        turnLeft();
      }
      else if (path[pathPointIndex] == 'r')
      {
        turnRight();
      }
      else
      {
        if (isGoingToTurn(pathPointIndex))
        {
          addMoveWhileIncrementing(MarsRoverMove.TURN_MOVE, false);
        }
        else if (isGoingToTakeSample(pathPointIndex))
        {
          takeSample();
        }
        else
        {
          addMoveWhileIncrementing(isCurrentlyGoingLeftOrRight() ? MarsRoverMove.HORIZONTAL_MOVE : MarsRoverMove.VERTICAL_MOVE, pathPointIndex == path.length - 1);
        }
      }
    }
    
    return moves;
  }

}
