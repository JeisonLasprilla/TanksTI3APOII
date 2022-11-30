package model;

import com.example.ti3.HelloApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Map {
    private Image bg;
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Wall> walls = new ArrayList<>();

    public Map(Canvas canvas, String mapPath) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+ HelloApplication.class.getResource(mapPath).getPath();
        bg = new Image(uri);
    }

    public void draw(){
        gc.save();
        gc.drawImage(bg, 10,10, 560,380);
        gc.restore();
    }
}
