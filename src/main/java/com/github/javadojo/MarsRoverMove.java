package com.github.javadojo;

public enum MarsRoverMove
{
  INITIAL_MOVE
  {
    @Override
    public char draw()
    {
      return 'X';
    }
  },
  FINAL_MOVE
  {
    @Override
    public char draw()
    {
      return '*';
    }
  },
  HORIZONTAL_MOVE
  {
    @Override
    public char draw()
    {
      return '-';
    }
  },
  VERTICAL_MOVE
  {
    @Override
    public char draw()
    {
      return '|';
    }
  },
  TURN_MOVE
  {
    @Override
    public char draw()
    {
      return '+';
    }
  },
  TAKE_SAMPLE_MOVE
  {
    @Override
    public char draw()
    {
      return 'S';
    }
  },
  OVERLAP_MOVE
  {
    @Override
    public char draw()
    {
      return '+';
    }
  };

  public abstract char draw();
}
