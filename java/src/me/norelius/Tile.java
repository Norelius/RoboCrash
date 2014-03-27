package me.norelius;

import java.util.List;

/**
 * Represent a map tile in RoboCrash. Tiles can be pitfalls, have a wall in every
 * direction, contain a flag, conveyer belts, gears and repair stations. Not yet implemented tile
 * elements are laserbeams and pushers. Tile state is dependent on pixel placement and RBG
 * value as specified by RoboCrash RBG sheet.
 * 
 * @author Jenny Norelius
 * @edited Mar 27, 2014
 */
public class Tile {
  Point point; // stores the x, y coordinates

  // If hole is true the tile is a pit and robots entering it die.
  boolean isHole;
  boolean hasFlag = false;
  int flagNumber = 0;
  // Wall values. If true, the tile has a wall in that orientation that blocks lasers and
  // robot movement.
  boolean northWall = false;
  boolean eastWall = false;
  boolean southWall = false;
  boolean westWall = false;

  boolean hasScrewHammer = false;
  boolean hasHammer = false;
  boolean isSpawnTile = false;
  int spawnNumber = 0;

  // Conveyer belt information
  boolean hasConveyerBelt = false;
  boolean isDoubleBelt = false; // single if false
  boolean isStraightBelt = false; // is turn if false
  boolean isRightTurn = false; // if false == leftTurn
  Direction conveyerBeltExit = null;

  // Gear information
  boolean isGear = false;
  boolean rightGear = false; // if false == leftGear



  // a Tile can hold none or one player
  private Player player = null;
  // a spawn Tile can hold any number of playerSpawns.
  private List<Player> playerSpawns;



  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Tiles are read from a map and it's variables can be read from the RGB values of the
   * map tile. So far only the variables stored in the 8-bit value of red have been
   * implemented.
   * 
   * @param x The map x coordinate of the tile
   * @param y The map y coordinate of the tile
   * @param red Value of 0-255. Map colour of the tile which stores it's variables.
   */
  public Tile(int x, int y, int red, int green, int blue) {
    point = new Point(x, y);

    // Assign variables based on the red value
    interpretRedValue(red);
    // Assign variables based on the green value
    interpretGreenValue(green);
    // Assign variables based on the blue value
    interpretBlueValue(blue);
  }

  /**
   * @param blue
   */
  private void interpretBlueValue(int blue) {
    if (blue > 127) {

      blue -= 128;
    }
    if (blue > 63) {

      blue -= 64;
    }
    if (blue > 31) {

      blue -= 32;
    }
    if (blue > 15) {

      blue -= 16;
    }
    if (blue > 7) {

      blue -= 8;
    }
    if (blue > 3) {

      blue -= 4;
    }
    if (blue > 1) {

      blue -= 2;
    }
    if (blue > 0) {

    }
  }

  private void interpretGreenValue(int green) {
    int greenSpec = 0;
    int greenFacing = 0;
    if (green > 127) {
      greenSpec = 1;
      green -= 128;
    }
    if (green > 63) {
      greenSpec += 1;
      green -= 64;
    }
    if (green > 31) {
      greenSpec += 2;
      green -= 32;
    }
    if (green > 15) {
      greenSpec += 4;
      green -= 16;
    }
    isDoubleBelt = false;
    if (green > 7) {
      isDoubleBelt = true;
      green -= 8;
    }
    if (green > 3) {
      greenFacing = 1;
      green -= 4;
    }
    if (green > 1) {
      greenFacing += 2;
      green -= 2;
    }
    if (green > 0) {
      greenFacing += 4;
    }
    switch (greenSpec) {
      case 0:
        hasScrewHammer = true;
        break;
      case 1:
        hasHammer = true;
        break;
      case 2:
        isSpawnTile = true;
        spawnNumber = greenFacing;
        break;
      case 3:
        hasConveyerBelt = true;
        isStraightBelt = true;
        setConveyerBeltExit(greenFacing);
        break;
      case 4:
        hasConveyerBelt = true;
        isRightTurn = true;
        setConveyerBeltExit(greenFacing);
        break;
      case 5:
        hasConveyerBelt = true;
        isRightTurn = false;
        setConveyerBeltExit(greenFacing);
        break;
      // empty variables
      case 6:
        isGear = true;
        setGearDirection(greenFacing);
      case 7:
        break;
    }
  }

  /**
   * @param red
   */
  private void interpretRedValue(int red) {
    isHole = true;
    if (red > 127) {
      isHole = false;
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

  private void setConveyerBeltExit(int facing) {
    switch (facing) {
      case 0:
        conveyerBeltExit = Direction.NORTH;
        break;
      case 1:
        conveyerBeltExit = Direction.EAST;
        break;
      case 2:
        conveyerBeltExit = Direction.SOUTH;
        break;
      case 3:
        conveyerBeltExit = Direction.WEST;
        break;
    }
  }

  /**
   * Sets the gears rotations direction.
   * 
   * @param greenFacing
   */
  private void setGearDirection(int greenFacing) {
    if (greenFacing == 0) {
      rightGear = false;
    } else if (greenFacing == 1) {
      rightGear = true;
    }
  }
}
