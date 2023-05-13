package com.example.keygame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Special2 extends Bullet
{
    public Special2(double x,double y,double velX, double velY) {
        super(x,y,velX,velY);

    }

    @Override
    public void draw(GraphicsContext gc)
    {
        gc.setFill(Color.CYAN);
        gc.fillRect(this.bulletx,this.bullety,60,60);
    }

    @Override
    public void tick(){
        bulletx-=Math.cos(Key.frameCount/4.0);
        bullety-=Math.sin(Key.frameCount/4.0);
        bulletx-=bXvel;
        bullety-=bYvel;
    }
}

