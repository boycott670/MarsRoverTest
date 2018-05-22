package com.github.javadojo;

import java.util.Arrays;
import java.util.Objects;

public final class ImmutableMarsRoverCoordinates
{
  public static ImmutableMarsRoverCoordinates of(int row, int column)
  {
    return new ImmutableMarsRoverCoordinates(row, column);
  }
  
  public static ImmutableMarsRoverCoordinates plus(final ImmutableMarsRoverCoordinates first, final ImmutableMarsRoverCoordinates second)
  {
    return of(first.getRow() + second.getRow(), first.getColumn() + second.getColumn());
  }
  
  private final int row;
  private final int column;
  
  private ImmutableMarsRoverCoordinates(int row, int column)
  {
    this.row = row;
    this.column = column;
  }

  public int getRow()
  {
    return row;
  }

  public int getColumn()
  {
    return column;
  }
  
  @Override
  public String toString()
  {
    return String.format("(%d,%d)", row, column);
  }
  
  @Override
  public boolean equals(Object obj)
  {
    return obj instanceof ImmutableMarsRoverCoordinates ? Objects.equals(row, ((ImmutableMarsRoverCoordinates)obj).getRow()) && Objects.equals(column, ((ImmutableMarsRoverCoordinates)obj).getColumn()) : false;
  }
  
  @Override
  public int hashCode()
  {
    return Arrays.hashCode(new int[] { row, column });
  }
}
