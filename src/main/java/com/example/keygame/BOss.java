package com.example.keygame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class BOss extends Enemy{


    long lastShot=0;
    double r,g,b;

    public BOss(double x, double y,int hp)
    {
        super(x,y,hp);
        this.r=Key.random(50,255);
        this.g=Key.random(50,255);
        this.b=Key.random(50,255);


    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(Color.rgb((int) r, (int) g, (int) b));
        gc.fillRect(enemyX,enemyY,100,100);



    }

    @Override
    public void tick() {
//        super.tick();

        if(Key.frameCount-lastShot>=30){

            double playerAngle=Math.toDegrees(Math.atan2(this.enemyY-Key.playery,this.enemyX-Key.playerx));
            double rad = Math.toRadians(playerAngle);
            double xvel = Math.cos(rad);
            double yvel = Math.sin(rad);
            xvel *= 4;
            yvel *= 4;
            lastShot=Key.frameCount;
            Key.enemyBullets.add(new Bullet(this.enemyX,this.enemyY,xvel,yvel));
        }

    }


}
