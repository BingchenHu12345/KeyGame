package com.example.keygame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy
{
    double enemyX;

    double enemyY;

    int hp;

    public Enemy(double x, double y,int hp)
    {
       this.enemyX=x;
       this.enemyY=y;
       this.hp=hp;


    }

    public void draw(GraphicsContext gc)
    {
        gc.setFill(Color.RED);
        gc.fillRect(enemyX,enemyY,20,20);


    }

    public void tick()
    {
        //TODO: if enemy is within 1 coordinate in the irection we wish to travel, do not travel there.



        if(enemyX>Key.playerx)
        {
            enemyX-=1;
        }
        if(enemyX<Key.playerx)
        {
            enemyX+=1;

        }
        if(enemyY<Key.playery)
        {
            enemyY+=1;
        }
        if(enemyY>Key.playery)
        {
            enemyY-=1;
        }



    }








}
