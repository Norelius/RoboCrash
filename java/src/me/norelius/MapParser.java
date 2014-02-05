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
 * up of Tile elements.
 * 
 * @author Jenny Norelius
 * @version 2014-02-05
 */
public class MapParser {

  public List<Tile> loadMap(String map) throws IOException {
    List<Tile> tiles = new ArrayList<>();
    try {
      File mapFile = new File("/Users/Jenny/Documents/git/RoboCrash/java/src/me/norelius/map.png");
      BufferedImage mapImage = ImageIO.read(mapFile);
      for (int x = 0; x < mapImage.getWidth(); x++) {
        for (int y = 0; y < mapImage.getHeight(); y++) {
          Color color = new Color(mapImage.getRGB(x, y));
          int red = color.getRed();
          tiles.add(new Tile(x, y, red));
        }
      }
    } catch (IOException e) {

    }
    return tiles;
  }
}
