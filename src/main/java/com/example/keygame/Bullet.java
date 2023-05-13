package com.example.keygame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet
{
    public double bulletx;
    public double bullety;

    double bXvel;
    double bYvel;

    public Bullet(double x,double y,double velX, double velY) {
        this.bulletx = x;
        this.bullety = y;
        this.bXvel=velX;
        this.bYvel=velY;


    }


    public void draw(GraphicsContext gc)
    {
        gc.setFill(Color.BLUE);
        gc.fillRect(bulletx,bullety,10,10);


    }
    public void tick()
    {
        bulletx-=bXvel;
        bullety-=bYvel;




    }
}
