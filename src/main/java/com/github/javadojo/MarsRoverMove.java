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
    
    @Override
    public boolean canBeOverridenOnSecondPass()
    {
      return false;
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
    
    @Override
    public boolean canBeOverridenOnSecondPass()
    {
      return false;
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
  
  public boolean canBeOverridenOnSecondPass()
  {
    return true;
  }
}
