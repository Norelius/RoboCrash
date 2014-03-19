package me.norelius;

/**
 * Representing a player robot.
 * 
 * @author Jenny Norelius
 * @edited Mar 19, 2014
 */
public class Player {
  private int id; //identifier
  private static final int STARTLIVES = 3;
  private int maxHealth;
  private int lives;
  private int health;
  Direction direction = Direction.NORTH;
  
  public Player(int id) {
    this.id = id;
  }

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