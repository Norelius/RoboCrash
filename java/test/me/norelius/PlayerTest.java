package me.norelius;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for Player.
 * 
 * @author Jenny Norelius
 * @edited Mar 19, 2014
 */
public class PlayerTest {

  @Test
  public void equalsTest() {
    Player player1 = new Player(1);
    Player player2 = new Player(2);
    
    assertEquals(player1, player1);
    assertFalse(player1 == player2);
    assertTrue(player1 != player2);
  }
}