package me.norelius;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for Tile
 * 
 * @author Jenny Norelius
 * @edited Mar 19, 2014
 */
public class TileTest {

  @Test
  public void constructorTest1() {
    Tile tile = new Tile(6, 8, 255, 158, 255);
    assertEquals(tile.point.getX(), 6);
    assertEquals(tile.point.getY(), 8);
    assertFalse(tile.isHole);
    assertTrue(tile.hasFlag);
    assertEquals(tile.flagNumber, 4);
    assertTrue(tile.northWall);
    assertTrue(tile.eastWall);
    assertTrue(tile.southWall);
    assertTrue(tile.westWall);
    assertFalse(tile.hasScrewHammer);
    assertFalse(tile.hasHammer);
    assertFalse(tile.isSpawnTile);
    assertEquals(tile.spawnNumber, 0);
    assertTrue(tile.hasConveyerBelt);
    assertTrue(tile.isDoubleBelt);
    assertFalse(tile.isStraightBelt);
    assertFalse(tile.isRightTurn);
    assertEquals(tile.conveyerBeltExit, Direction.WEST);
    assertNull(tile.getPlayer());
  }

  @Test
  public void constructorTest2() {
    Tile tile = new Tile(34, 12, 0, 0, 0);
    assertEquals(34, tile.point.getX());
    assertEquals(12, tile.point.getY());
    assertTrue(tile.isHole);
    assertFalse(tile.hasFlag);
    assertEquals(0, tile.flagNumber);
    assertFalse(tile.northWall);
    assertFalse(tile.eastWall);
    assertFalse(tile.southWall);
    assertFalse(tile.westWall);
    assertTrue(tile.hasScrewHammer);
    assertFalse(tile.hasHammer);
    assertFalse(tile.isSpawnTile);
    assertEquals(0, tile.spawnNumber);
    assertFalse(tile.hasConveyerBelt);
    assertFalse(tile.isDoubleBelt);
    assertFalse(tile.isStraightBelt);
    assertFalse(tile.isRightTurn);
    assertEquals(null, tile.conveyerBeltExit);
    assertNull(tile.getPlayer());
  }

  @Test
  public void playerModifierTest() {
    Tile tile = new Tile(3, 5, 64, 64, 64);
    Player player = new Player(1);
    assertNull(tile.getPlayer());
    tile.setPlayer(player);
    assertEquals(player, tile.getPlayer());
  }
}
