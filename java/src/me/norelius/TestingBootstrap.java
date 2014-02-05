package me.norelius;

/**
 * 
 * @author Jenny Norelius
 * @version 2014-02-05
 */
public class TestingBootstrap {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Player player = null;
    GameEngine engine =
        GameEngine.newBuilder().map(GameMap.load(args[0])).registerPlayer(player).build();

    while (engine.theGameIsOn()) {
      engine.executeTurn();
    }

  }

}
