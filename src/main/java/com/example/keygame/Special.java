package com.example.keygame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Special
{
    double specialx;
    double specialy;

    double sXvel;
    double sYvel;

    public Special(double x,double y,double velX, double velY) {
        this.specialx = x;
        this.specialy = y;
        this.sXvel=velX;
        this.sYvel=velY;


    }
    public void draw(GraphicsContext gc)
    {
        gc.setFill(Color.CYAN);
        gc.fillRect(specialx,specialy,25,25);


    }
    public void tick()
    {
        specialx-=sXvel;
        specialy-=sYvel;




    }


}
