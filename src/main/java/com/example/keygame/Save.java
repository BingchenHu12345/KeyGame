package com.example.keygame;

import java.io.*;

public class Save implements Serializable
{
    public int killcount;
    public double speed;
    public Save(int kc, double speed)
    {
        this.killcount = kc;
        this.speed=speed;
    }

    public void save()
    {

        FileOutputStream fos;
        try
        {
            fos=new FileOutputStream("Save.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Save load()
    {
        Save safe;
        FileInputStream fis;
        ObjectInputStream ois;
        try
        {
            fis = new FileInputStream("Save.ser");
            ois = new ObjectInputStream(fis);
            safe = (Save) ois.readObject();
            System.out.println("HJ"+ safe.killcount);
            return safe;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("KL");
            return new Save(0,0.5);

       }







    }


}
