package com.example.snakeladderapril;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Tile extends Rectangle{
    // create constructor
    public Tile (int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);
        setFill(Color.AZURE);
        setStroke(Color.BLACK);
    }
}
