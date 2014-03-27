package me.norelius;

import java.util.List;

/**
 * Handles all rules for gears.
 * 
 * @author Jenny Norelius
 * @edited Mar 27, 2014
 */
public class GearRules {
  private static Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH,
      Direction.WEST};

  /**
   * Rotates players standing on a gear.
   * @param tiles
   */
  public static void activate(List<Tile> tiles) {
    for (Tile tile : tiles) {
      if (tile.isGear) {
        int add = tile.rightGear ? 1 : 3;
        Direction playerDirection = tile.getPlayer().direction;
        for (int i = 0; i < directions.length; i++) {
          if (playerDirection == directions[i])
            tile.getPlayer().direction = directions[(i + add) % 4];
        }
      }
    }
  }
}
