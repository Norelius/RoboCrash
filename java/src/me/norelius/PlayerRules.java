package me.norelius;

import java.util.List;

/**
 * Handles all rules for player movement.
 * 
 * @author Jenny Norelius
 * @edited Mar 19, 2014
 * 
 */
public class PlayerRules {
  private static Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH,
      Direction.WEST};

  public static void processCommand(List<Tile> tiles, PlayerCommand pc) {
    Direction playerDirection = pc.player.direction;
    switch (pc.command.action) {
    // Move actions
      case FORWARD1:
      case FORWARD2:
      case FORWARD3:
      case BACK:
        processMove(tiles, pc.player, pc.command.action);
        break;

      // Turn actions
      case TURNLEFT:
        for (int i = 0; i < directions.length; i++) {
          if (playerDirection == directions[i]) pc.player.direction = directions[(i + 3) % 4];
        }
        break;
      case TURNRIGHT:
        for (int i = 0; i < directions.length; i++) {
          if (playerDirection == directions[i]) pc.player.direction = directions[(i + 1) % 4];
        }
        break;
      case TURNU:
        for (int i = 0; i < directions.length; i++) {
          if (playerDirection == directions[i]) pc.player.direction = directions[(i + 2) % 4];
        }
        break;
    }
  }

  /**
   * Generates a move call to moveRobot and recursive calls for multi-moves.
   * 
   * @param tiles List of all the maptiles at their current state.
   * @param player which is moving
   * @param action moving back or forward multiple steps.
   */
  private static void processMove(List<Tile> tiles, Player player, Action action) {
    // Finds and stores the current player tile
    Tile playerTile = null;
    for (Tile tile : tiles) {
      if (tile.getPlayer() == player) 
        playerTile = tile;
    }

    // Finds and stores destination tile
    Point destination = null;
    int add = action == Action.BACK ? -1 : 1;
    if (player.direction == Direction.NORTH) {
      destination = new Point(playerTile.point.getX(), playerTile.point.getY() - add);
    } else if (player.direction == Direction.EAST) {
      destination = new Point(playerTile.point.getX() + add, playerTile.point.getY());
    } else if (player.direction == Direction.SOUTH) {
      destination = new Point(playerTile.point.getX(), playerTile.point.getY() + add);
    } else if (player.direction == Direction.WEST) {
      destination = new Point(playerTile.point.getX() - add, playerTile.point.getY());
    }
    Tile destinationTile = null;
    for (Tile tile : tiles) {
      if (tile.point.equals(destination))
        destinationTile = tile;
    }

    moveRobot(tiles, player, playerTile, destinationTile);

    // add a check for if player has landed in hole
    // Perform next move step for multi-tile move actions
    boolean fellToDeath = false;
    for (Tile tile : tiles) {
      if (tile.getPlayer() == player)
        fellToDeath = tile.isHole;
    }
    if (!fellToDeath) {
      if (action == Action.FORWARD3) processMove(tiles, player, Action.FORWARD2);
      if (action == Action.FORWARD2) processMove(tiles, player, Action.FORWARD1);
    }
  }

  private static void moveRobot(List<Tile> tiles, Player player, Tile playerTile, Tile destination) {
    if (destination.getPlayer() != null) // is tile empty?
      pushPlayer(tiles, player, playerTile, destination);
    if (destination.getPlayer() != null) // couldn't push robot
      return;

    boolean wall = false;
    int xDifference = destination.point.getX() - playerTile.point.getX();
    int yDifference = destination.point.getY() - playerTile.point.getY();
    if (xDifference == 1) {
      wall = playerTile.eastWall;
    } else if (xDifference == -1) {
      wall = playerTile.westWall;
    } else if (yDifference == 1) {
      wall = playerTile.southWall;
    } else if (yDifference == -1) {
      wall = playerTile.northWall;
    }

    // robot is moved if no wall divider
    if (!wall) {
      destination.setPlayer(player);
      playerTile.setPlayer(null);
    }


    // if(tile.spawner)
    // tile.playerSpawn.add(player);

    // if hole

    // if flag
  }

  /**
   * Generates a new move robot command for a robot pushed by another robot.
   * 
   * @param tiles List of all the maptiles at their current state.
   * @param player pushing robot
   * @param playerTile position of pushing robot
   * @param destination position of robot being pushed
   */
  private static void pushPlayer(List<Tile> tiles, Player player, Tile playerTile, Tile destination) {
    int xDifference = destination.point.getX() - playerTile.point.getX();
    int yDifference = destination.point.getY() - playerTile.point.getY();
    Point pushDestination =
        new Point(destination.point.getX() + xDifference, destination.point.getY() + yDifference);

    Tile pushTile = null; // initializes, should remove
    for (Tile tile : tiles) {
      if (tile.point.equals(pushDestination)) //TODO doesn't notice equals
        pushTile = tile;
    }
    moveRobot(tiles, destination.getPlayer(), destination, pushTile);
  }
}
