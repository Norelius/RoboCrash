package me.norelius;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * MapParser reads png files representing game maps and makes them into a gameboard made
 * up of Tile elements. Tile state is dependent on pixel placement and RBG
 * value as specified by RoboCrash RBG sheet.
 * 
 * @author Jenny Norelius
 * @edited Mar 19, 2014
 */
public class MapParser {

  public List<Tile> loadMap(String map) throws IOException {
    List<Tile> tiles = new ArrayList<>();
    try {
      // TODO
      // use String map for filepath
      File mapFile = new File("/Users/Jenny/Documents/git/RoboCrash/java/src/me/norelius/map.png");
      BufferedImage mapImage = ImageIO.read(mapFile);
      for (int x = 0; x < mapImage.getWidth(); x++) {
        for (int y = 0; y < mapImage.getHeight(); y++) {
          Color color = new Color(mapImage.getRGB(x, y));
          int red = color.getRed();
          tiles.add(new Tile(x, y, color.getRed(), color.getGreen(), color.getBlue()));
        }
      }
    } catch (IOException e) {

    }
    return tiles;
  }
}