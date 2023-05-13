package com.example.keygame;

import com.example.keygame.Bullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bomb extends Bullet
{

    int boom;


    public Bomb(double x, double y) {
        super(x, y, 0, 0);
        boom=300;
    }


    @Override
    public void draw(GraphicsContext gc) {
//        super.draw(gc);
        if(boom%10<=2){
        gc.setFill(Color.RED);
        }
        else{gc.setFill(Color.WHITE);}

        gc.fillOval(bulletx,bullety,50,50);
    }

    @Override
    public void tick() {
        boom-=1;
    }
}
