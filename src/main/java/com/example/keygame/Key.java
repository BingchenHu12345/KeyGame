package com.example.keygame;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// want W to move toward the arrow
public class Key extends Application
{

    public static double playerx=50.0;
    public static double playery=50.0;


    public static long frameCount=0;



    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Bullet> enemyBullets = new ArrayList<>();
    public static ArrayList<Bullet> Specials= new ArrayList<>();

    public static ArrayList<Item> items =new ArrayList<>();
    String firstI = "press 1 to buy Hp +20, it costs 10 money/kills";
    String secondI = "press 2 to buy ultimate sped, it costs 100 money/kills";
    double speed=0.5;
    int moveSpeed = 2;
    int Hp=300;
    int killCount=0;
    boolean move = false;
    boolean shop = false;
    boolean shoot=false;
    boolean ult=false;
    long lastUlt=frameCount;

    double pBulletD=10;
    public static double playerAngle=0;

    public static boolean help=false;

    @Override
    public void start(Stage stage) throws IOException
    {
       Canvas canvas = new Canvas(500,500);
       GraphicsContext gc= canvas.getGraphicsContext2D();
       Timeline tl=new Timeline(new KeyFrame(Duration.millis(16.0 + (2.0 / 3.0)), e ->run(gc)));
       tl.setCycleCount(Animation.INDEFINITE);
       canvas.setFocusTraversable(true);

       File saveFile=new File("Save.ser");
       if(!saveFile.exists())
       {
           help=true;

       }

       canvas.setOnKeyPressed(e->
       {
           if (e.getCode()== KeyCode.D)
           {
               //playerx+=moveSpeed;
           }
           if (e.getCode()== KeyCode.A)
           {
               //playerx-=moveSpeed;
           }
           if (e.getCode()== KeyCode.W)
           {
               move=true;
//               double angle = Math.toRadians(playerAngle);
//               double changeX=Math.cos(angle)*moveSpeed;
//               double changeY=Math.sin(angle)*moveSpeed;
//               playerx-=changeX;
//               playery-=changeY;



               //playery-=moveSpeed;
           }
           if(e.getCode()==KeyCode.P){
               help=!help;
           }
           if (e.getCode()== KeyCode.S)
           {

               //playery+=moveSpeed;
           }
           if(e.getCode()==KeyCode.COMMAND && killCount>=1)
           {
               shop=!shop;
           }

           if(e.getCode()==KeyCode.ENTER)
           {
               var s = Save.load();


               killCount=s.killcount;
               speed=s.speed;
           }
           if(e.getCode()==KeyCode.BACK_SPACE)
           {
               Save save = new Save(killCount, speed);
               System.out.println(save);
               save.save();
           }
           if(e.getCode()==KeyCode.DIGIT1&&shop&&killCount>=10)
           {
               killCount-=10;
               firstI="YOU BOUGHT +20 hp";
               Hp+=20;
               //TODO:make damage a var
           }
           if(e.getCode()==KeyCode.DIGIT2&&shop&&killCount>=100)
           {
               killCount-=100;
               secondI="YOU BOUGHT ultymite sped";
               speed=0.1;
               pBulletD=5;

           }
           if (e.getCode()== KeyCode.SPACE)
           {
               shoot=true;


           }
           if (e.getCode()==KeyCode.Z)
           {
               ult=true;
           }
           if (e.getCode()== KeyCode.Q)
           {
               moveSpeed+=2;
           }
           if(e.getCode()==KeyCode.L)
           {
               enemies.add(new BOss(50,50,300));
           }
           if(moveSpeed>7)
           {
               moveSpeed=10;
           }
           if(playerx>500 || playery>500 || playery<0 ||playerx<0){
               System.exit(0);
           }



       });

       canvas.setOnKeyReleased(e->{
           if(e.getCode()==KeyCode.W)
               move=false;

           if(e.getCode()==KeyCode.SPACE){
               shoot=false;
           }
           if(e.getCode()==KeyCode.Z){
               ult=false;
           }

       });

       canvas.addEventFilter(MouseEvent.MOUSE_MOVED, e->
       {
           // mouse Info
           double x = e.getX();
           double y = e.getY();
           playerAngle=Math.toDegrees(Math.atan2(playery-y,playerx-x));

       });


       stage.setTitle("BOB");
       stage.setScene(new Scene(new StackPane(canvas)));
       stage.show();
       tl.play();


    }

    public static  double random(double min, double max)
    {
        Random random= new Random();
        return random.nextDouble(max - min) + min;
    }
    public void run(GraphicsContext gc) {

        if(help){
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, 3000, 3000);
            gc.setFill(Color.WHITE);
            gc.fillText("Hold space to shoot", 50,50);
            gc.fillText("Press Z for special (Loads every 10 seconds)", 75,65);
            gc.fillText("Command too go too0 shop, you need more than 1 kill", 100,80);
            gc.fillText("delete too save", 125,95);
            gc.fillText("return to load ur file", 150,110);
            gc.fillText("Q too sprint", 175,125);
            gc.fillText("press P too get out", 200,140);



            return;
        }

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 3000, 3000);
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, Hp * 5, 10);
        gc.fillText("kill count "+killCount,0,500);


        if(!shop) {
            if (move == true) {
                double angle = Math.toRadians(playerAngle);
                double changeX = Math.cos(angle) * moveSpeed;
                double changeY = Math.sin(angle) * moveSpeed;
                playerx -= changeX;
                playery -= changeY;
            }
            if (shoot == true) {
                if (frameCount % (60 * 0.5) == 0) {
                    double rad = Math.toRadians(playerAngle);
                    double xvel = Math.cos(rad);
                    double yvel = Math.sin(rad);
                    xvel *= 4;
                    yvel *= 4;
                    bullets.add(new Bullet(playerx, playery, xvel, yvel));
                }
            }
            if (ult == true) {
                if (frameCount - lastUlt >= Settings.ultCooldown) {
                    lastUlt = frameCount;
                    double rad = Math.toRadians(playerAngle);
                    double xvel = Math.cos(rad);
                    double yvel = Math.sin(rad);
                    xvel *= 2;
                    yvel *= 2;
                    bullets.add(new Special2(playerx, playery, xvel, yvel));

                }
            }


            if (shoot == true) {
                if (frameCount % (60 * speed) == 0) {
                    double rad = Math.toRadians(playerAngle);
                    double xvel = Math.cos(rad);
                    double yvel = Math.sin(rad);
                    xvel *= 4;
                    yvel *= 4;


                    bullets.add(new Bullet(playerx, playery, xvel, yvel));
                }

            }

            if (frameCount % 60 * 15 == 0) {
                enemies.add(new Enemy(random(0, 500), random(0, 500), 1));
            }
            if (frameCount % (60 * 30) == 0) {
                enemies.add(new BOss(random(0, 500), random(0, 500), 300));
            }

            if (Hp <= 0) {
                System.exit(0);
            }


            for (int i = 0; i < enemies.size(); i++) {

                Enemy enemyT = enemies.get(i);
                enemyT.draw(gc);

                enemyT.tick();
                if (getD(enemyT.enemyX, playerx, enemyT.enemyY, playery) < 20) {
                    Hp -= 10;
                    enemyT.hp -= 1;
                    if (enemyT.hp <= 0) {

                        enemies.remove(enemyT);

                    }

                    break;
                }


            }

            for (int i = 0; i < enemyBullets.size(); i++) {
                Bullet bullet1 = enemyBullets.get(i);
                bullet1.draw(gc);

                bullet1.tick();
                if (getD(playerx, bullet1.bulletx, playery, bullet1.bullety) <= 20) {
                    enemyBullets.remove(bullet1);
                    Hp -= 5;
                }


            }

            for (int k = 0; k < bullets.size(); k++) {

                Bullet bullet1 = bullets.get(k);
                bullet1.draw(gc);

                bullet1.tick();
                if (bullet1.bulletx > 500 || bullet1.bullety > 500 || bullet1.bullety < 0 || bullet1.bulletx < 0) {
                    bullets.remove(k);
//                    break;
                }
                for (int j = 0; j < enemies.size(); j++) {
                    Enemy enemy1 = enemies.get(j);

                    double bulletSize = 20;
                    double enemySize = 20;

                    if (bullet1 instanceof Special2) {
                        bulletSize = 60;
                    }
                    if (enemy1 instanceof BOss) {
                        enemySize = 100;
                    }

                    if (getD(bullet1.bulletx, enemy1.enemyX, bullet1.bullety, enemy1.enemyY) < bulletSize ||
                            getD(bullet1.bulletx, enemy1.enemyX + enemySize / 2, bullet1.bullety, enemy1.enemyY + enemySize / 2) <= enemySize / 2) {
                        if (enemy1 instanceof BOss) {

                            bullets.remove(k);
                        }
                        enemy1.hp -= pBulletD;
                        if (enemy1.hp <= 0) {
                            enemies.remove(j);
                            System.out.println(killCount);
                            killCount += 1;
                        }


                        if (killCount % 5 == 0) {

                            items.add(new Item(enemy1.enemyX, enemy1.enemyY));

                        }
                        break;
                    }


                }

            }

            for (int n = 0; n < items.size(); n++) {
                Item item = items.get(n);
                item.draw(gc);
                if (getD(playerx, item.itemx, playery, item.itemy) < 30) {
                    Hp += 20;
                    items.remove(n);
                    break;
                }
            }


            gc.setFill(Color.WHITE);
            // 25 = width and height / 2
            gc.transform(new Affine(new Rotate(playerAngle, playerx + 25, playery + 25)));
            gc.fillRect(playerx, playery, 50, 50);
            gc.transform(new Affine(new Rotate(-playerAngle, playerx + 25, playery + 25)));

//        gc.rotate(-45.0);
            gc.restore();
            frameCount++;
        }
        else
        {
            gc.setFont(new Font(15));
            gc.fillText("Ur money / kills is "+killCount,25,25);
            gc.fillText("U can buy different things here",45,45);
            gc.fillText(firstI,65,65);
            gc.fillText(secondI,80,80);




        }

        }


    public static void main(String[] args)
    {
        launch();
    }
    public static double getD(double x1, double x2, double y1, double y2){
        double d=Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
        return d;
    }
}