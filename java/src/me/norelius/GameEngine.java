package me.norelius;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Jenny Norelius
 * @version 2014-02-05
 */
public class GameEngine {

  GameMap map;
  List<Player> players;
  private static final int REGISTERS = 5;

  public GameEngine(Builder builder) {
    this.map = builder.map;
    this.players = builder.players;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {

    GameMap map;
    List<Player> players = new ArrayList<>();

    private Builder() {}

    public Builder map(GameMap map) {
      this.map = map;
      return this;
    }


    public Builder registerPlayer(Player player) {
      players.add(player);
      return this;
    }


    public GameEngine build() {
      return new GameEngine(this);
    }

  }

  /**
   * 
   */
  public void executeTurn() {
    Map<Player, Register> register = new HashMap<>();
    for (Player player : players) {
      register.put(player, player.getRegister());
    }
    List<List<PlayerCommand>> playerCommands = new ArrayList<>(REGISTERS);
    for (int i = 0; i < REGISTERS; i++) {
      List<PlayerCommand> registerCommands = new ArrayList<>(players.size());
      playerCommands.add(registerCommands);
      for (Map.Entry<Player, Register> entry : register.entrySet()) {
        registerCommands.add(new PlayerCommand(entry.getKey(), entry.getValue().getCommand(i)));
      }
      Collections.sort(registerCommands);
    }
    map.execute(playerCommands);
  }

  /**
   * @return
   */
  public boolean theGameIsOn() {
    for (Player player : players) {
      if (player.isAlive() && !player.hasWon()) {
        return true;
      }
    }
    return false;
  }

}
