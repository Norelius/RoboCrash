package me.norelius;

import java.io.IOException;
import java.util.List;

/**
 * GameMap stores all the map tile information and calls rules.
 * 
 * @author Jenny Norelius
 * @edited Mar 19, 2014
 */
public class GameMap {
  private List<Tile> tiles;
  PlayerRules playerRules = new PlayerRules();

  /**
   * @param string
   * @return
   */
  public static GameMap load(String string) {
    MapParser mapParser = new MapParser();
    GameMap map = new GameMap();
    try {
      map.tiles = mapParser.loadMap(string);
    } catch (IOException e) {

    }
    return map;
  }

  /**
   * Executes a turn. Each turn has 5 registers. Order of execution per register is player
   * command, conveyers, gears, lasers, end of register tags. At the end of a whole turn a
   * end of turn tag is also done.
   * 
   * @param playerCommands
   */
  public void execute(List<List<PlayerCommand>> playerCommands) {
    for (int i = 0; i < playerCommands.size(); i++) { // register i + 1
      for (int j = 0; j < playerCommands.get(i).size(); j++)
        playerRules.processCommand(tiles, playerCommands.get(i).get(j));
      for (Tile tile : tiles) {

      }
      // TODO
      // conveyor belts
      // gears
      // lasers
    }
  }
}