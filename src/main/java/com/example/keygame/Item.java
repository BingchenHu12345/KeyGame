package com.example.keygame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Item
{
    double itemx;
    double itemy;
    double r,g,b;
    public Item(double x,double y){
        this.itemx = x;
        this.itemy = y;
        this.r=Key.random(50,255);
        this.g=Key.random(50,255);
        this.b=Key.random(50,255);



    }
    public void draw(GraphicsContext gc)
    {
        gc.setFill(Color.rgb((int) r, (int) g, (int) b));
        gc.fillRect(itemx,itemy,10,10);


    }
}

