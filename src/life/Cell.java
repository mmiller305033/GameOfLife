/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

/**
 *
 * @author Mark Miller
 */

import java.awt.*;

public class Cell {
    private int position;
    private boolean isAlive;
    private Color color;
    private int X_COORD;
    private int Y_COORD;
    
    
    public Cell(){
        
        isAlive = false;
        color = Color.BLACK;
        X_COORD = 0;
        Y_COORD = 0;
        
    }
    
    public boolean isAlive(){
        return isAlive;
    }
    
    public void makeAlive(){
        isAlive = true;
        float red = (float)Math.random();
        float green = (float)Math.random();
        float blue = (float)Math.random();
        color = new Color(red,blue,green);
    }
    
    public void makeDead(){
        isAlive = false;
        color = Color.BLACK;
    }
    
    public Color getColor(){
        return color;
    }
    
    public int getX()
    {
        return X_COORD;
    }
    
    public void setX(int x)
    {
        X_COORD = x;
    }
    
    public void setY(int y)
    {
        Y_COORD = y;
    }
    
    public int getY()
    {
        return Y_COORD;
    }
    
}
