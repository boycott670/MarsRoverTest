package com.github.javadojo;

final class ImmutableMarsRoverCoordinates
{
  static ImmutableMarsRoverCoordinates of(int row, int column)
  {
    return new ImmutableMarsRoverCoordinates(row, column);
  }
  
  static ImmutableMarsRoverCoordinates plus(final ImmutableMarsRoverCoordinates first, final ImmutableMarsRoverCoordinates second)
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

  int getRow()
  {
    return row;
  }

  int getColumn()
  {
    return column;
  }
  
  @Override
  public String toString()
  {
    return String.format("(%d,%d)", row, column);
  }
}
