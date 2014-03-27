package me.norelius;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all rules for conveyer belt logic. Player movement is delegated to PlayerRules.
 * 
 * @author Jenny Norelius
 * @edited Mar 27, 2014
 */
public class ConveyerRules {

  /**
   * Activates conveyer belts with players.
   * 
   * @param tiles
   */
  public static void activate(List<Tile> tiles) {
    // fetch double conveyers tiles with players and activate
    List<Tile> doubleConveyerTiles = new ArrayList<>();
    for (Tile tile : tiles) {
      if (tile.getPlayer() != null && tile.hasConveyerBelt && tile.isDoubleBelt)
        doubleConveyerTiles.add(tile);
    }
    activateConveyerBelts(tiles, doubleConveyerTiles);

    // fetch every conveyer tile with players and activate
    List<Tile> conveyerTiles = new ArrayList<>();
    for (Tile tile : tiles) {
      if (tile.getPlayer() != null && tile.hasConveyerBelt) conveyerTiles.add(tile);
    }
    activateConveyerBelts(tiles, conveyerTiles);
  }

  /**
   * Finds destination tile for a robot on a conveyer belt. Forwards player move request
   * to PlayerRules.
   * 
   * @param tiles
   * @param doubleConveyerTiles
   */
  private static void activateConveyerBelts(List<Tile> tiles, List<Tile> cTiles) {
    for (int i = 0; i < cTiles.size(); i++) {
      Point destinationPoint = null;
      if (cTiles.get(i).conveyerBeltExit == Direction.NORTH) {
        destinationPoint = new Point(cTiles.get(i).point.getX(), cTiles.get(i).point.getY() - 1);
      } else if (cTiles.get(i).conveyerBeltExit == Direction.EAST) {
        destinationPoint = new Point(cTiles.get(i).point.getX(), cTiles.get(i).point.getY() + 1);
      } else if (cTiles.get(i).conveyerBeltExit == Direction.SOUTH) {
        destinationPoint = new Point(cTiles.get(i).point.getX(), cTiles.get(i).point.getY() + 1);
      } else if (cTiles.get(i).conveyerBeltExit == Direction.WEST) {
        destinationPoint = new Point(cTiles.get(i).point.getX(), cTiles.get(i).point.getY() - 1);
      }
      Tile destinationTile = null;
      for (Tile tile : cTiles) {
        if (tile.point == destinationPoint) destinationTile = tile;
      }
      // Delegates player movement to PlayerRules
      PlayerRules.conveyPlayer(tiles, cTiles.get(i), destinationTile);
    }
  }
}
