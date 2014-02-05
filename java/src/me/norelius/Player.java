package me.norelius;

/**
 * 
 * @author Jenny Norelius
 * @version 2014-02-05
 */
public class Player {
  private static final int STARTLIVES = 3;
  private int maxHealth;
  private int lives;
  private int health;

  /**
   * @return
   */
  public boolean isAlive() {
    return lives > 0 ? true : false;
  }

  /**
   * @return
   */
  public boolean hasWon() {
    return false;
  }

  /**
   * 
   */
  public Register getRegister() {
    return  null;
  }
}