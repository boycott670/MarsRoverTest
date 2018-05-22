package com.github.javadojo.parsers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
  
  
  private boolean isGoingLeftOrRight()
  {
    return Arrays.asList(Heading.LEFT, Heading.RIGHT).contains(currentHeading);
  }
  
  private boolean isGoingToTurn(final int currentPathPointIndex)
  {
    return Arrays.asList('l', 'r').contains(Character.valueOf(path[currentPathPointIndex + 1]));
  }
  
  private boolean isGoingToTakeSample(final int currentPathPointIndex)
  {
    return path[currentPathPointIndex + 1] == 'S';
  }
  
  private ImmutableMarsRoverCoordinates nextCoordinates(final ImmutableMarsRoverCoordinates currentCoordinates)
  {
    return ImmutableMarsRoverCoordinates.plus(currentCoordinates, directions[currentHeading.ordinal()]);
  }
  
  private void incrementToNextMove()
  {
    lastAddedCoordinates = nextCoordinates(lastAddedCoordinates);
  }
  
  private void addMove(MarsRoverMove move)
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
        moves.put(lastAddedCoordinates, MarsRoverMove.OVERLAP_MOVE);
      }
      else
      {
        moves.put(lastAddedCoordinates, move);
      }
    }
  }
  
  private void addMoveWhileIncrementing(MarsRoverMove move)
  {
    incrementToNextMove();
    addMove(move);
  }

  private char[] path;
  
  private Map<ImmutableMarsRoverCoordinates, MarsRoverMove> moves;
  
  private ImmutableMarsRoverCoordinates lastAddedCoordinates;

  @Override
  public void setPath(String path)
  {
    this.path = path.toCharArray();
  }

  @Override
  public Map<ImmutableMarsRoverCoordinates, MarsRoverMove> parsePath()
  {
    moves = new HashMap<>();
    
    lastAddedCoordinates = ImmutableMarsRoverCoordinates.of(0, 0);
    
    addMove(MarsRoverMove.INITIAL_MOVE);
    
    for (int pathPointIndex = 0; pathPointIndex < path.length - 1; pathPointIndex++)
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
          addMoveWhileIncrementing(MarsRoverMove.TURN_MOVE);
        }
        else if (isGoingToTakeSample(pathPointIndex))
        {
          takeSample();
        }
        else
        {
          addMoveWhileIncrementing(isGoingLeftOrRight() ? MarsRoverMove.HORIZONTAL_MOVE : MarsRoverMove.VERTICAL_MOVE);
        }
      }
    }
    
    addMoveWhileIncrementing(MarsRoverMove.FINAL_MOVE);
    
    return moves;
  }

}
