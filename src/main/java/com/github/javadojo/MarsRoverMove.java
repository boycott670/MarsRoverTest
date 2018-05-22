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
    
    @Override
    public MarsRoverMove overlappingMove()
    {
      return this;
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
    public boolean canBeOverlappedBy(MarsRoverMove overlappingMove)
    {
      if (overlappingMove != FINAL_MOVE)
      {
        return false;
      }
      
      return super.canBeOverlappedBy(overlappingMove);
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
  
  public boolean canBeOverlappedBy(final MarsRoverMove overlappingMove)
  {
    return true;
  }
  
  public MarsRoverMove overlappingMove()
  {
    return MarsRoverMove.OVERLAP_MOVE;
  }
}
