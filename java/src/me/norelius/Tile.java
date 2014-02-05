package me.norelius;

/**
 * Represent a map tile in RoboCrash. Tiles can be pitfalls, have a wall in every
 * direction and contain a flag. Not yet implemented tile elements are laserbeams,
 * conveyer belts, repair stations and pushers.
 * 
 * @author Jenny Norelius
 * @version 2014-02-05
 */
public class Tile {
  int x;
  int y;
  // If hole is true the tile is a pit and robots entering it die.
  boolean hole;
  boolean hasFlag = false;
  int flagNumber;
  // Wall values. If true, the tile has a wall in that orientation that blocks lasers and
  // robot movement.
  boolean northWall = false;
  boolean eastWall = false;
  boolean southWall = false;
  boolean westWall = false;

  /**
   * Tiles are read from a map and it's variables can be read from the RGB values of the
   * map tile. So far only the variables stored in the 8-bit value of red have been
   * implemented.
   * 
   * @param x The map x coordinate of the tile
   * @param y The map y coordinate of the tile
   * @param red Value of 0-255. Map colour of the tile which stores it's variables.
   */
  public Tile(int x, int y, int red) {
    this.x = x;
    this.y = y;
    hole = true;
    if (red > 127) {
      hole = false;
      red -= 128;
    }
    if (red > 63) {
      hasFlag = true;
      flagNumber = 1;
      red -= 64;
    }
    if (red > 31) {
      flagNumber += 1;
      red -= 32;
    }
    if (red > 15) {
      flagNumber += 2;
      red -= 16;
    }
    if (red > 7) {
      northWall = true;
      red -= 8;
    }
    if (red > 3) {
      eastWall = true;
      red -= 4;
    }
    if (red > 1) {
      southWall = true;
      red -= 2;
    }
    if (red > 0) {
      westWall = true;
    }
  }
}
