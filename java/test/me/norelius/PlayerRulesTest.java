package me.norelius;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for PlayerTestRules.
 * 
 * @author Jenny Norelius
 * @edited Mar 19, 2014
 */
public class PlayerRulesTest {

  @Test
  public void turnTest() {
    // Create a small map of tiles for testing
    List<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, 128, 0, 0)); // 0
    tiles.add(new Tile(2, 1, 128, 0, 0));
    tiles.add(new Tile(1, 2, 128, 0, 0));
    tiles.add(new Tile(2, 2, 128, 0, 0));

    // Test each tile's playerstate
    for (Tile tile : tiles) {
      assertNull(tile.getPlayer());
    }

    // Create players and add to board
    Player player1 = new Player(1);
    tiles.get(0).setPlayer(player1);

    // Navigation tests
    PlayerCommand pcTurnRight = new PlayerCommand(player1, new Command(50, Action.TURNRIGHT));
    PlayerCommand pcTurnLeft = new PlayerCommand(player1, new Command(50, Action.TURNLEFT));
    PlayerCommand pcTurnU = new PlayerCommand(player1, new Command(50, Action.TURNU));

    assertEquals(Direction.NORTH, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnRight);
    assertEquals(Direction.EAST, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnRight);
    assertEquals(Direction.SOUTH, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnRight);
    assertEquals(Direction.WEST, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnRight);
    assertEquals(Direction.NORTH, player1.direction);

    assertEquals(Direction.NORTH, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnLeft);
    assertEquals(Direction.WEST, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnLeft);
    assertEquals(Direction.SOUTH, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnLeft);
    assertEquals(Direction.EAST, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnLeft);
    assertEquals(Direction.NORTH, player1.direction);
    
    assertEquals(Direction.NORTH, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnU);
    assertEquals(Direction.SOUTH, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnU);
    assertEquals(Direction.NORTH, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnRight);
    assertEquals(Direction.EAST, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnU);
    assertEquals(Direction.WEST, player1.direction);
    PlayerRules.processCommand(tiles, pcTurnU);
    assertEquals(Direction.EAST, player1.direction);
  }

  @Test
  public void moveForwardNoWallTest() {
    // Create a small map of tiles for testing
    List<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, 128, 0, 0)); // 0
    tiles.add(new Tile(2, 1, 128, 0, 0));
    tiles.add(new Tile(3, 1, 128, 0, 0));
    tiles.add(new Tile(4, 1, 128, 0, 0));
    tiles.add(new Tile(1, 2, 128, 0, 0)); // 4
    tiles.add(new Tile(2, 2, 128, 0, 0));
    tiles.add(new Tile(3, 2, 128, 0, 0));
    tiles.add(new Tile(4, 2, 128, 0, 0));
    tiles.add(new Tile(1, 3, 128, 0, 0)); // 8
    tiles.add(new Tile(2, 3, 128, 0, 0));
    tiles.add(new Tile(3, 3, 128, 0, 0));
    tiles.add(new Tile(4, 3, 128, 0, 0));
    tiles.add(new Tile(1, 4, 128, 0, 0)); // 12
    tiles.add(new Tile(2, 4, 128, 0, 0));
    tiles.add(new Tile(3, 4, 128, 0, 0));
    tiles.add(new Tile(4, 4, 128, 0, 0));

    // Test each tile's playerstate
    for (Tile tile : tiles) {
      assertNull(tile.getPlayer());
    }

    // Create players and add to board
    Player player1 = new Player(1);
    tiles.get(4).setPlayer(player1);

    // Navigation tests
    PlayerCommand pcForward1 = new PlayerCommand(player1, new Command(50, Action.FORWARD1));
    PlayerCommand pcForward2 = new PlayerCommand(player1, new Command(50, Action.FORWARD2));
    PlayerCommand pcForward3 = new PlayerCommand(player1, new Command(50, Action.FORWARD3));
    PlayerCommand pcTurnRight = new PlayerCommand(player1, new Command(50, Action.TURNRIGHT));
    PlayerCommand pcTurnLeft = new PlayerCommand(player1, new Command(50, Action.TURNLEFT));

    PlayerRules.processCommand(tiles, pcForward1); // 1 north
    assertNull(tiles.get(4).getPlayer());
    assertEquals(player1, tiles.get(0).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward1); // 1 east
    assertNull(tiles.get(0).getPlayer());
    assertEquals(player1, tiles.get(1).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward1); // 1 south
    assertNull(tiles.get(1).getPlayer());
    assertEquals(player1, tiles.get(5).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward1); // 1 west
    assertNull(tiles.get(5).getPlayer());
    assertEquals(player1, tiles.get(4).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcForward2); // 2 south
    assertNull(tiles.get(4).getPlayer());
    assertEquals(player1, tiles.get(12).getPlayer());


    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcForward3); // 3 west
    assertNull(tiles.get(12).getPlayer());
    assertEquals(player1, tiles.get(15).getPlayer());
  }

  @Test
  public void moveBackwardNoWallTest() {
    // Create a small map of tiles for testing
    List<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, 128, 0, 0)); // 0
    tiles.add(new Tile(2, 1, 128, 0, 0));
    tiles.add(new Tile(3, 1, 128, 0, 0));
    tiles.add(new Tile(4, 1, 128, 0, 0));
    tiles.add(new Tile(1, 2, 128, 0, 0)); // 4
    tiles.add(new Tile(2, 2, 128, 0, 0));
    tiles.add(new Tile(3, 2, 128, 0, 0));
    tiles.add(new Tile(4, 2, 128, 0, 0));
    tiles.add(new Tile(1, 3, 128, 0, 0)); // 8
    tiles.add(new Tile(2, 3, 128, 0, 0));
    tiles.add(new Tile(3, 3, 128, 0, 0));
    tiles.add(new Tile(4, 3, 128, 0, 0));
    tiles.add(new Tile(1, 4, 128, 0, 0)); // 12
    tiles.add(new Tile(2, 4, 128, 0, 0));
    tiles.add(new Tile(3, 4, 128, 0, 0));
    tiles.add(new Tile(4, 4, 128, 0, 0));

    // Test each tile's playerstate
    for (Tile tile : tiles) {
      assertNull(tile.getPlayer());
    }

    // Create players and add to board
    Player player1 = new Player(1);
    tiles.get(5).setPlayer(player1);

    // Navigation tests
    PlayerCommand pcBack = new PlayerCommand(player1, new Command(50, Action.BACK));
    PlayerCommand pcTurnLeft = new PlayerCommand(player1, new Command(50, Action.TURNLEFT));

    PlayerRules.processCommand(tiles, pcBack); // 1 south
    assertNull(tiles.get(5).getPlayer());
    assertEquals(player1, tiles.get(9).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcBack); // 1 east
    assertNull(tiles.get(9).getPlayer());
    assertEquals(player1, tiles.get(10).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcBack); // 1 north
    assertNull(tiles.get(10).getPlayer());
    assertEquals(player1, tiles.get(6).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcBack); // 1 west
    assertNull(tiles.get(6).getPlayer());
    assertEquals(player1, tiles.get(5).getPlayer());
  }

  @Test
  public void moveForwardWallTest() {
    // Create a small map of tiles for testing
    List<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, 255, 0, 0)); // 0
    tiles.add(new Tile(2, 1, 255, 0, 0));
    tiles.add(new Tile(3, 1, 255, 0, 0));
    tiles.add(new Tile(1, 2, 255, 0, 0)); // 3
    tiles.add(new Tile(2, 2, 255, 0, 0));
    tiles.add(new Tile(3, 2, 255, 0, 0));
    tiles.add(new Tile(1, 3, 255, 0, 0)); // 6
    tiles.add(new Tile(2, 3, 255, 0, 0));
    tiles.add(new Tile(3, 3, 255, 0, 0));

    // Test each tile's playerstate
    for (Tile tile : tiles) {
      assertNull(tile.getPlayer());
    }

    // Create players and add to board
    Player player1 = new Player(1);
    tiles.get(4).setPlayer(player1);

    // Navigation tests
    PlayerCommand pcForward1 = new PlayerCommand(player1, new Command(50, Action.FORWARD1));
    PlayerCommand pcForward2 = new PlayerCommand(player1, new Command(50, Action.FORWARD2));
    PlayerCommand pcForward3 = new PlayerCommand(player1, new Command(50, Action.FORWARD3));
    PlayerCommand pcTurnRight = new PlayerCommand(player1, new Command(50, Action.TURNRIGHT));

    PlayerRules.processCommand(tiles, pcForward1);
    assertEquals(player1, tiles.get(4).getPlayer());
    assertNull(tiles.get(0).getPlayer());
    assertNull(tiles.get(1).getPlayer());
    assertNull(tiles.get(2).getPlayer());
    assertNull(tiles.get(3).getPlayer());
    assertNull(tiles.get(5).getPlayer());
    assertNull(tiles.get(6).getPlayer());
    assertNull(tiles.get(7).getPlayer());
    assertNull(tiles.get(8).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward1);
    assertEquals(player1, tiles.get(4).getPlayer());
    assertNull(tiles.get(0).getPlayer());
    assertNull(tiles.get(1).getPlayer());
    assertNull(tiles.get(2).getPlayer());
    assertNull(tiles.get(3).getPlayer());
    assertNull(tiles.get(5).getPlayer());
    assertNull(tiles.get(6).getPlayer());
    assertNull(tiles.get(7).getPlayer());
    assertNull(tiles.get(8).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward1);
    assertEquals(player1, tiles.get(4).getPlayer());
    assertNull(tiles.get(0).getPlayer());
    assertNull(tiles.get(1).getPlayer());
    assertNull(tiles.get(2).getPlayer());
    assertNull(tiles.get(3).getPlayer());
    assertNull(tiles.get(5).getPlayer());
    assertNull(tiles.get(6).getPlayer());
    assertNull(tiles.get(7).getPlayer());
    assertNull(tiles.get(8).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward1);
    assertEquals(player1, tiles.get(4).getPlayer());
    assertNull(tiles.get(0).getPlayer());
    assertNull(tiles.get(1).getPlayer());
    assertNull(tiles.get(2).getPlayer());
    assertNull(tiles.get(3).getPlayer());
    assertNull(tiles.get(5).getPlayer());
    assertNull(tiles.get(6).getPlayer());
    assertNull(tiles.get(7).getPlayer());
    assertNull(tiles.get(8).getPlayer());

    PlayerRules.processCommand(tiles, pcForward2);
    assertEquals(player1, tiles.get(4).getPlayer());
    assertNull(tiles.get(0).getPlayer());
    assertNull(tiles.get(1).getPlayer());
    assertNull(tiles.get(2).getPlayer());
    assertNull(tiles.get(3).getPlayer());
    assertNull(tiles.get(5).getPlayer());
    assertNull(tiles.get(6).getPlayer());
    assertNull(tiles.get(7).getPlayer());
    assertNull(tiles.get(8).getPlayer());
    
    PlayerRules.processCommand(tiles, pcForward3);
    assertEquals(player1, tiles.get(4).getPlayer());
    assertNull(tiles.get(0).getPlayer());
    assertNull(tiles.get(1).getPlayer());
    assertNull(tiles.get(2).getPlayer());
    assertNull(tiles.get(3).getPlayer());
    assertNull(tiles.get(5).getPlayer());
    assertNull(tiles.get(6).getPlayer());
    assertNull(tiles.get(7).getPlayer());
    assertNull(tiles.get(8).getPlayer());
  }

  @Test
  public void moveBackwardWallTest() {
    // Create a small map of tiles for testing
    List<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, 255, 0, 0)); // 0
    tiles.add(new Tile(2, 1, 255, 0, 0));
    tiles.add(new Tile(3, 1, 255, 0, 0));
    tiles.add(new Tile(1, 2, 255, 0, 0)); // 3
    tiles.add(new Tile(2, 2, 255, 0, 0));
    tiles.add(new Tile(3, 2, 255, 0, 0));
    tiles.add(new Tile(1, 3, 255, 0, 0)); // 6
    tiles.add(new Tile(2, 3, 255, 0, 0));
    tiles.add(new Tile(3, 3, 255, 0, 0));

    // Test each tile's playerstate
    for (Tile tile : tiles) {
      assertNull(tile.getPlayer());
    }

    // Create players and add to board
    Player player1 = new Player(1);
    tiles.get(4).setPlayer(player1);

    // Navigation tests
    PlayerCommand pcBack = new PlayerCommand(player1, new Command(50, Action.BACK));
    PlayerCommand pcTurnRight = new PlayerCommand(player1, new Command(50, Action.TURNRIGHT));

    PlayerRules.processCommand(tiles, pcBack);
    assertEquals(player1, tiles.get(4).getPlayer());
    assertNull(tiles.get(0).getPlayer());
    assertNull(tiles.get(1).getPlayer());
    assertNull(tiles.get(2).getPlayer());
    assertNull(tiles.get(3).getPlayer());
    assertNull(tiles.get(5).getPlayer());
    assertNull(tiles.get(6).getPlayer());
    assertNull(tiles.get(7).getPlayer());
    assertNull(tiles.get(8).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcBack);
    assertEquals(player1, tiles.get(4).getPlayer());
    assertNull(tiles.get(0).getPlayer());
    assertNull(tiles.get(1).getPlayer());
    assertNull(tiles.get(2).getPlayer());
    assertNull(tiles.get(3).getPlayer());
    assertNull(tiles.get(5).getPlayer());
    assertNull(tiles.get(6).getPlayer());
    assertNull(tiles.get(7).getPlayer());
    assertNull(tiles.get(8).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcBack);
    assertEquals(player1, tiles.get(4).getPlayer());
    assertNull(tiles.get(0).getPlayer());
    assertNull(tiles.get(1).getPlayer());
    assertNull(tiles.get(2).getPlayer());
    assertNull(tiles.get(3).getPlayer());
    assertNull(tiles.get(5).getPlayer());
    assertNull(tiles.get(6).getPlayer());
    assertNull(tiles.get(7).getPlayer());
    assertNull(tiles.get(8).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcBack);
    assertEquals(player1, tiles.get(4).getPlayer());
    assertNull(tiles.get(0).getPlayer());
    assertNull(tiles.get(1).getPlayer());
    assertNull(tiles.get(2).getPlayer());
    assertNull(tiles.get(3).getPlayer());
    assertNull(tiles.get(5).getPlayer());
    assertNull(tiles.get(6).getPlayer());
    assertNull(tiles.get(7).getPlayer());
    assertNull(tiles.get(8).getPlayer());
  }

  @Test
  public void pushForwardNoWallTest() {
    // Create a small map of tiles for testing
    List<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, 128, 0, 0)); // 0
    tiles.add(new Tile(2, 1, 128, 0, 0));
    tiles.add(new Tile(3, 1, 128, 0, 0));
    tiles.add(new Tile(4, 1, 128, 0, 0));
    tiles.add(new Tile(1, 2, 128, 0, 0)); // 4
    tiles.add(new Tile(2, 2, 128, 0, 0));
    tiles.add(new Tile(3, 2, 128, 0, 0));
    tiles.add(new Tile(4, 2, 128, 0, 0));
    tiles.add(new Tile(1, 3, 128, 0, 0)); // 8
    tiles.add(new Tile(2, 3, 128, 0, 0));
    tiles.add(new Tile(3, 3, 128, 0, 0));
    tiles.add(new Tile(4, 3, 128, 0, 0));
    tiles.add(new Tile(1, 4, 128, 0, 0)); // 12
    tiles.add(new Tile(2, 4, 128, 0, 0));
    tiles.add(new Tile(3, 4, 128, 0, 0));
    tiles.add(new Tile(4, 4, 128, 0, 0));

    // Test each tile's playerstate
    for (Tile tile : tiles) {
      assertNull(tile.getPlayer());
    }

    // Create players and add to board
    Player player1 = new Player(1);
    Player player2 = new Player(2);
    Player player3 = new Player(3);
    Player player4 = new Player(4);
    Player player5 = new Player(5);
    tiles.get(0).setPlayer(player1);
    tiles.get(2).setPlayer(player2);
    tiles.get(6).setPlayer(player3);
    tiles.get(10).setPlayer(player4);
    tiles.get(5).setPlayer(player5);

    // Navigation tests
    PlayerCommand pcForward1 = new PlayerCommand(player1, new Command(50, Action.FORWARD1));
    PlayerCommand pcForward2 = new PlayerCommand(player1, new Command(50, Action.FORWARD2));
    PlayerCommand pcTurnRight = new PlayerCommand(player1, new Command(50, Action.TURNRIGHT));
    PlayerCommand pcTurnLeft = new PlayerCommand(player1, new Command(50, Action.TURNLEFT));

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward2); // 2 east
    assertEquals(player1, tiles.get(2).getPlayer());
    assertEquals(player2, tiles.get(3).getPlayer());
    assertEquals(player3, tiles.get(6).getPlayer());
    assertEquals(player4, tiles.get(10).getPlayer());
    assertEquals(player5, tiles.get(5).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward1); // 1 south
    assertEquals(player1, tiles.get(6).getPlayer());
    assertEquals(player2, tiles.get(3).getPlayer());
    assertEquals(player3, tiles.get(10).getPlayer());
    assertEquals(player4, tiles.get(14).getPlayer());
    assertEquals(player5, tiles.get(5).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward1); // 1 west
    assertEquals(player1, tiles.get(5).getPlayer());
    assertEquals(player2, tiles.get(3).getPlayer());
    assertEquals(player3, tiles.get(10).getPlayer());
    assertEquals(player4, tiles.get(14).getPlayer());
    assertEquals(player5, tiles.get(4).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcForward1); // 1 south
    assertEquals(player1, tiles.get(9).getPlayer());
    assertEquals(player2, tiles.get(3).getPlayer());
    assertEquals(player3, tiles.get(10).getPlayer());
    assertEquals(player4, tiles.get(14).getPlayer());
    assertEquals(player5, tiles.get(4).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcForward1); // 1 east
    assertEquals(player1, tiles.get(10).getPlayer());
    assertEquals(player2, tiles.get(3).getPlayer());
    assertEquals(player3, tiles.get(11).getPlayer());
    assertEquals(player4, tiles.get(14).getPlayer());
    assertEquals(player5, tiles.get(4).getPlayer());
  }

  @Test
  public void pushBackwardNoWallTest() {
    // Create a small map of tiles for testing
    List<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, 128, 0, 0)); // 0
    tiles.add(new Tile(2, 1, 128, 0, 0));
    tiles.add(new Tile(3, 1, 128, 0, 0));
    tiles.add(new Tile(4, 1, 128, 0, 0));
    tiles.add(new Tile(1, 2, 128, 0, 0)); // 4
    tiles.add(new Tile(2, 2, 128, 0, 0));
    tiles.add(new Tile(3, 2, 128, 0, 0));
    tiles.add(new Tile(4, 2, 128, 0, 0));
    tiles.add(new Tile(1, 3, 128, 0, 0)); // 8
    tiles.add(new Tile(2, 3, 128, 0, 0));
    tiles.add(new Tile(3, 3, 128, 0, 0));
    tiles.add(new Tile(4, 3, 128, 0, 0));
    tiles.add(new Tile(1, 4, 128, 0, 0)); // 12
    tiles.add(new Tile(2, 4, 128, 0, 0));
    tiles.add(new Tile(3, 4, 128, 0, 0));
    tiles.add(new Tile(4, 4, 128, 0, 0));

    // Test each tile's playerstate
    for (Tile tile : tiles) {
      assertNull(tile.getPlayer());
    }

    // Create players and add to board
    Player player1 = new Player(1);
    Player player2 = new Player(2);
    Player player3 = new Player(3);
    Player player4 = new Player(4);
    Player player5 = new Player(5);
    tiles.get(2).setPlayer(player1);
    tiles.get(6).setPlayer(player2);
    tiles.get(10).setPlayer(player3);
    tiles.get(5).setPlayer(player4);
    tiles.get(9).setPlayer(player5);

    // Navigation tests
    PlayerCommand pcBack = new PlayerCommand(player1, new Command(50, Action.BACK));
    PlayerCommand pcTurnRight = new PlayerCommand(player1, new Command(50, Action.TURNRIGHT));
    PlayerCommand pcTurnLeft = new PlayerCommand(player1, new Command(50, Action.TURNLEFT));

    PlayerRules.processCommand(tiles, pcBack); // 1 south
    assertEquals(player1, tiles.get(6).getPlayer());
    assertEquals(player2, tiles.get(10).getPlayer());
    assertEquals(player3, tiles.get(14).getPlayer());
    assertEquals(player4, tiles.get(5).getPlayer());
    assertEquals(player5, tiles.get(9).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcBack); // 1 west
    assertEquals(player1, tiles.get(5).getPlayer());
    assertEquals(player2, tiles.get(10).getPlayer());
    assertEquals(player3, tiles.get(14).getPlayer());
    assertEquals(player4, tiles.get(4).getPlayer());
    assertEquals(player5, tiles.get(9).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcBack); // 1 south
    assertEquals(player1, tiles.get(9).getPlayer());
    assertEquals(player2, tiles.get(10).getPlayer());
    assertEquals(player3, tiles.get(14).getPlayer());
    assertEquals(player4, tiles.get(4).getPlayer());
    assertEquals(player5, tiles.get(13).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcBack); // 1 east
    assertEquals(player1, tiles.get(10).getPlayer());
    assertEquals(player2, tiles.get(11).getPlayer());
    assertEquals(player3, tiles.get(14).getPlayer());
    assertEquals(player4, tiles.get(4).getPlayer());
    assertEquals(player5, tiles.get(13).getPlayer());
  }

  @Test
  public void pushForwardWallTest() {
    // Create a small map of tiles for testing
    List<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, 130, 0, 0)); // 0
    tiles.add(new Tile(2, 1, 130, 0, 0));
    tiles.add(new Tile(3, 1, 130, 0, 0));
    tiles.add(new Tile(4, 1, 130, 0, 0));
    tiles.add(new Tile(5, 1, 130, 0, 0));
    tiles.add(new Tile(6, 1, 130, 0, 0));
    tiles.add(new Tile(7, 1, 130, 0, 0));
    tiles.add(new Tile(1, 2, 132, 0, 0)); // 7
    tiles.add(new Tile(2, 2, 137, 0, 0));
    tiles.add(new Tile(3, 2, 136, 0, 0));
    tiles.add(new Tile(4, 2, 136, 0, 0));
    tiles.add(new Tile(5, 2, 136, 0, 0));
    tiles.add(new Tile(6, 2, 140, 0, 0));
    tiles.add(new Tile(7, 2, 129, 0, 0));
    tiles.add(new Tile(1, 3, 132, 0, 0)); // 14
    tiles.add(new Tile(2, 3, 129, 0, 0));
    tiles.add(new Tile(3, 3, 128, 0, 0));
    tiles.add(new Tile(4, 3, 128, 0, 0));
    tiles.add(new Tile(5, 3, 128, 0, 0));
    tiles.add(new Tile(6, 3, 132, 0, 0));
    tiles.add(new Tile(7, 3, 129, 0, 0));
    tiles.add(new Tile(1, 4, 132, 0, 0)); // 21
    tiles.add(new Tile(2, 4, 129, 0, 0));
    tiles.add(new Tile(3, 4, 128, 0, 0));
    tiles.add(new Tile(4, 4, 128, 0, 0));
    tiles.add(new Tile(5, 4, 128, 0, 0));
    tiles.add(new Tile(6, 4, 132, 0, 0));
    tiles.add(new Tile(7, 4, 129, 0, 0));
    tiles.add(new Tile(1, 5, 132, 0, 0)); // 28
    tiles.add(new Tile(2, 5, 129, 0, 0));
    tiles.add(new Tile(3, 5, 128, 0, 0));
    tiles.add(new Tile(4, 5, 128, 0, 0));
    tiles.add(new Tile(5, 5, 128, 0, 0));
    tiles.add(new Tile(6, 5, 132, 0, 0));
    tiles.add(new Tile(7, 5, 129, 0, 0));
    tiles.add(new Tile(1, 6, 132, 0, 0)); // 35
    tiles.add(new Tile(2, 6, 131, 0, 0));
    tiles.add(new Tile(3, 6, 130, 0, 0));
    tiles.add(new Tile(4, 6, 130, 0, 0));
    tiles.add(new Tile(5, 6, 130, 0, 0));
    tiles.add(new Tile(6, 6, 134, 0, 0));
    tiles.add(new Tile(7, 6, 129, 0, 0));
    tiles.add(new Tile(1, 7, 128, 0, 0)); // 42
    tiles.add(new Tile(2, 7, 136, 0, 0));
    tiles.add(new Tile(3, 7, 136, 0, 0));
    tiles.add(new Tile(4, 7, 136, 0, 0));
    tiles.add(new Tile(5, 7, 136, 0, 0));
    tiles.add(new Tile(6, 7, 136, 0, 0));
    tiles.add(new Tile(7, 7, 128, 0, 0));

    // Test each tile's playerstate
    for (Tile tile : tiles) {
      assertNull(tile.getPlayer());
    }

    // Create players and add to board
    Player player1 = new Player(1);
    Player player2 = new Player(2);
    Player player3 = new Player(3);
    Player player4 = new Player(4);
    Player player5 = new Player(5);
    Player player6 = new Player(6);
    Player player7 = new Player(7);
    Player player8 = new Player(8);
    tiles.get(9).setPlayer(player1);
    tiles.get(10).setPlayer(player2);
    tiles.get(25).setPlayer(player3);
    tiles.get(32).setPlayer(player4);
    tiles.get(39).setPlayer(player5);
    tiles.get(8).setPlayer(player6);
    tiles.get(36).setPlayer(player7);
    tiles.get(23).setPlayer(player8);

    // Navigation tests
    PlayerCommand pcForward1 = new PlayerCommand(player1, new Command(50, Action.FORWARD1));
    PlayerCommand pcForward2 = new PlayerCommand(player1, new Command(50, Action.FORWARD2));
    PlayerCommand pcForward3 = new PlayerCommand(player1, new Command(50, Action.FORWARD3));
    PlayerCommand pcTurnRight = new PlayerCommand(player1, new Command(50, Action.TURNRIGHT));
    PlayerCommand pcTurnLeft = new PlayerCommand(player1, new Command(50, Action.TURNLEFT));

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward3); // 3 east
    assertEquals(player1, tiles.get(11).getPlayer());
    assertEquals(player2, tiles.get(12).getPlayer());
    assertEquals(player3, tiles.get(25).getPlayer());
    assertEquals(player4, tiles.get(32).getPlayer());
    assertEquals(player5, tiles.get(39).getPlayer());
    assertEquals(player6, tiles.get(8).getPlayer());
    assertEquals(player7, tiles.get(36).getPlayer());
    assertEquals(player8, tiles.get(23).getPlayer());
    
    PlayerRules.processCommand(tiles, pcForward1); // 1 east
    assertEquals(player1, tiles.get(11).getPlayer());
    assertEquals(player2, tiles.get(12).getPlayer());
    assertEquals(player3, tiles.get(25).getPlayer());
    assertEquals(player4, tiles.get(32).getPlayer());
    assertEquals(player5, tiles.get(39).getPlayer());
    assertEquals(player6, tiles.get(8).getPlayer());
    assertEquals(player7, tiles.get(36).getPlayer());
    assertEquals(player8, tiles.get(23).getPlayer());
    
    PlayerRules.processCommand(tiles, pcForward2); // 1 east
    assertEquals(player1, tiles.get(11).getPlayer());
    assertEquals(player2, tiles.get(12).getPlayer());
    assertEquals(player3, tiles.get(25).getPlayer());
    assertEquals(player4, tiles.get(32).getPlayer());
    assertEquals(player5, tiles.get(39).getPlayer());
    assertEquals(player6, tiles.get(8).getPlayer());
    assertEquals(player7, tiles.get(36).getPlayer());
    assertEquals(player8, tiles.get(23).getPlayer());
    
    PlayerRules.processCommand(tiles, pcForward3); // 3 east
    assertEquals(player1, tiles.get(11).getPlayer());
    assertEquals(player2, tiles.get(12).getPlayer());
    assertEquals(player3, tiles.get(25).getPlayer());
    assertEquals(player4, tiles.get(32).getPlayer());
    assertEquals(player5, tiles.get(39).getPlayer());
    assertEquals(player6, tiles.get(8).getPlayer());
    assertEquals(player7, tiles.get(36).getPlayer());
    assertEquals(player8, tiles.get(23).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward2); // 2 south
    assertEquals(player1, tiles.get(18).getPlayer());
    assertEquals(player2, tiles.get(12).getPlayer());
    assertEquals(player3, tiles.get(25).getPlayer());
    assertEquals(player4, tiles.get(32).getPlayer());
    assertEquals(player5, tiles.get(39).getPlayer());
    assertEquals(player6, tiles.get(8).getPlayer());
    assertEquals(player7, tiles.get(36).getPlayer());
    assertEquals(player8, tiles.get(23).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward3); // 3 west
    assertEquals(player1, tiles.get(15).getPlayer());
    assertEquals(player2, tiles.get(12).getPlayer());
    assertEquals(player3, tiles.get(25).getPlayer());
    assertEquals(player4, tiles.get(32).getPlayer());
    assertEquals(player5, tiles.get(39).getPlayer());
    assertEquals(player6, tiles.get(8).getPlayer());
    assertEquals(player7, tiles.get(36).getPlayer());
    assertEquals(player8, tiles.get(23).getPlayer());

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward1); // 1 north
    assertEquals(player1, tiles.get(15).getPlayer());
    assertEquals(player2, tiles.get(12).getPlayer());
    assertEquals(player3, tiles.get(25).getPlayer());
    assertEquals(player4, tiles.get(32).getPlayer());
    assertEquals(player5, tiles.get(39).getPlayer());
    assertEquals(player6, tiles.get(8).getPlayer());
    assertEquals(player7, tiles.get(36).getPlayer());
    assertEquals(player8, tiles.get(23).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcForward3); // 3 south
    assertEquals(player1, tiles.get(29).getPlayer());
    assertEquals(player2, tiles.get(12).getPlayer());
    assertEquals(player3, tiles.get(25).getPlayer());
    assertEquals(player4, tiles.get(32).getPlayer());
    assertEquals(player5, tiles.get(39).getPlayer());
    assertEquals(player6, tiles.get(8).getPlayer());
    assertEquals(player7, tiles.get(36).getPlayer());
    assertEquals(player8, tiles.get(23).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcForward1); // 1 east
    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcForward3); // 3 north
    assertEquals(player1, tiles.get(16).getPlayer());
    assertEquals(player2, tiles.get(12).getPlayer());
    assertEquals(player3, tiles.get(25).getPlayer());
    assertEquals(player4, tiles.get(32).getPlayer());
    assertEquals(player5, tiles.get(39).getPlayer());
    assertEquals(player6, tiles.get(8).getPlayer());
    assertEquals(player7, tiles.get(36).getPlayer());
    assertEquals(player8, tiles.get(9).getPlayer());
  }

  @Test
  public void pushBackwardWallTest() {
    // Create a small map of tiles for testing
    List<Tile> tiles = new ArrayList<>();
    tiles.add(new Tile(1, 1, 130, 0, 0)); // 0
    tiles.add(new Tile(2, 1, 130, 0, 0));
    tiles.add(new Tile(3, 1, 130, 0, 0));
    tiles.add(new Tile(4, 1, 130, 0, 0));
    tiles.add(new Tile(5, 1, 130, 0, 0));
    tiles.add(new Tile(6, 1, 130, 0, 0));
    tiles.add(new Tile(7, 1, 130, 0, 0));
    tiles.add(new Tile(1, 2, 132, 0, 0)); // 7
    tiles.add(new Tile(2, 2, 137, 0, 0));
    tiles.add(new Tile(3, 2, 136, 0, 0));
    tiles.add(new Tile(4, 2, 136, 0, 0));
    tiles.add(new Tile(5, 2, 136, 0, 0));
    tiles.add(new Tile(6, 2, 140, 0, 0));
    tiles.add(new Tile(7, 2, 129, 0, 0));
    tiles.add(new Tile(1, 3, 132, 0, 0)); // 14
    tiles.add(new Tile(2, 3, 129, 0, 0));
    tiles.add(new Tile(3, 3, 128, 0, 0));
    tiles.add(new Tile(4, 3, 128, 0, 0));
    tiles.add(new Tile(5, 3, 128, 0, 0));
    tiles.add(new Tile(6, 3, 132, 0, 0));
    tiles.add(new Tile(7, 3, 129, 0, 0));
    tiles.add(new Tile(1, 4, 132, 0, 0)); // 21
    tiles.add(new Tile(2, 4, 129, 0, 0));
    tiles.add(new Tile(3, 4, 128, 0, 0));
    tiles.add(new Tile(4, 4, 128, 0, 0));
    tiles.add(new Tile(5, 4, 128, 0, 0));
    tiles.add(new Tile(6, 4, 132, 0, 0));
    tiles.add(new Tile(7, 4, 129, 0, 0));
    tiles.add(new Tile(1, 5, 132, 0, 0)); // 28
    tiles.add(new Tile(2, 5, 129, 0, 0));
    tiles.add(new Tile(3, 5, 128, 0, 0));
    tiles.add(new Tile(4, 5, 128, 0, 0));
    tiles.add(new Tile(5, 5, 128, 0, 0));
    tiles.add(new Tile(6, 5, 132, 0, 0));
    tiles.add(new Tile(7, 5, 129, 0, 0));
    tiles.add(new Tile(1, 6, 132, 0, 0)); // 35
    tiles.add(new Tile(2, 6, 131, 0, 0));
    tiles.add(new Tile(3, 6, 130, 0, 0));
    tiles.add(new Tile(4, 6, 130, 0, 0));
    tiles.add(new Tile(5, 6, 130, 0, 0));
    tiles.add(new Tile(6, 6, 134, 0, 0));
    tiles.add(new Tile(7, 6, 129, 0, 0));
    tiles.add(new Tile(1, 7, 128, 0, 0)); // 42
    tiles.add(new Tile(2, 7, 136, 0, 0));
    tiles.add(new Tile(3, 7, 136, 0, 0));
    tiles.add(new Tile(4, 7, 136, 0, 0));
    tiles.add(new Tile(5, 7, 136, 0, 0));
    tiles.add(new Tile(6, 7, 136, 0, 0));
    tiles.add(new Tile(7, 7, 128, 0, 0));

    // Test each tile's playerstate
    for (Tile tile : tiles) {
      assertNull(tile.getPlayer());
    }

    // Create players and add to board
    Player player1 = new Player(1);
    Player player2 = new Player(2);
    Player player3 = new Player(3);
    Player player4 = new Player(4);

    tiles.get(9).setPlayer(player1);
    tiles.get(8).setPlayer(player2);
    tiles.get(11).setPlayer(player3);
    tiles.get(40).setPlayer(player4);

    // Navigation tests
    PlayerCommand pcBack = new PlayerCommand(player1, new Command(50, Action.BACK));
    PlayerCommand pcTurnRight = new PlayerCommand(player1, new Command(50, Action.TURNRIGHT));
    PlayerCommand pcTurnLeft = new PlayerCommand(player1, new Command(50, Action.TURNLEFT));

    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcBack); // west
    PlayerRules.processCommand(tiles, pcBack);
    assertEquals(player1, tiles.get(9).getPlayer());
    assertEquals(player2, tiles.get(8).getPlayer());
    assertEquals(player3, tiles.get(11).getPlayer());
    assertEquals(player4, tiles.get(40).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcBack); // east
    PlayerRules.processCommand(tiles, pcBack);
    PlayerRules.processCommand(tiles, pcBack);
    PlayerRules.processCommand(tiles, pcBack);
    PlayerRules.processCommand(tiles, pcBack);
    PlayerRules.processCommand(tiles, pcBack);
    assertEquals(player1, tiles.get(11).getPlayer());
    assertEquals(player2, tiles.get(8).getPlayer());
    assertEquals(player3, tiles.get(12).getPlayer());
    assertEquals(player4, tiles.get(40).getPlayer());
    
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcBack); // south
    PlayerRules.processCommand(tiles, pcTurnLeft);
    PlayerRules.processCommand(tiles, pcBack); //east
    PlayerRules.processCommand(tiles, pcTurnRight);
    PlayerRules.processCommand(tiles, pcBack); // south
    PlayerRules.processCommand(tiles, pcBack);
    PlayerRules.processCommand(tiles, pcBack);
    PlayerRules.processCommand(tiles, pcBack);
    PlayerRules.processCommand(tiles, pcBack);
    PlayerRules.processCommand(tiles, pcBack);
    assertEquals(player1, tiles.get(33).getPlayer());
    assertEquals(player2, tiles.get(8).getPlayer());
    assertEquals(player3, tiles.get(12).getPlayer());
    assertEquals(player4, tiles.get(40).getPlayer());

  }
}
