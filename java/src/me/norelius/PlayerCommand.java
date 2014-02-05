package me.norelius;

/**
 * A PlayerCommand consists of a player and a command to be executed. A player can only
 * have one PlayerCommand per turn and a full register consists of five PlayerCommands.
 * 
 * @author Jenny Norelius
 * @version 2014-02-05
 */
public class PlayerCommand implements Comparable<PlayerCommand> {
  Player player;
  Command command;

  public PlayerCommand(Player player, Command command) {
    this.player = player;
    this.command = command;
  }

  @Override
  public int compareTo(PlayerCommand o) {
    return command.compareTo(o.command);
  }
}
