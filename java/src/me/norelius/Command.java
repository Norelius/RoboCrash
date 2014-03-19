package me.norelius;

/**
 * Command stores a turn action for a robot. This can be a move, back up or turn. Each
 * command has a priority number that decides which command will be executed first,
 * highest first.
 * 
 * @author Jenny Norelius
 * @version 2014-02-05
 */
public class Command implements Comparable<Command> {
  final int registerStrength;
  final Action action;
  
  Command(int registerStrength, Action action){
    this.registerStrength = registerStrength;
    this.action = action;
  }

  @Override
  public int compareTo(Command o) {
    return Integer.compare(registerStrength, o.registerStrength);
  }
}