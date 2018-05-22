package com.github.javadojo;

import static com.github.javadojo.MarsRover.LINE_SEPARATOR;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MarsRoverTest
{
  @Test
  public void driveEast()
  {
    assertThat(new MarsRover("s").path(), equalTo("X*" + LINE_SEPARATOR));
  }

  @Test
  public void driveEastForABitLonger()
  {
    assertThat(new MarsRover("ssss").path(), equalTo("X---*" + LINE_SEPARATOR));
  }

}
