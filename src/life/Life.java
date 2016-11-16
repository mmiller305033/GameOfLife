/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Mark Miller
 */
public class Life {
 public static int speed = 100;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException 
    { 
        
        boolean g = true;
     MyPanel panel = new MyPanel();
    JFrame frame = new JFrame("JFrame Color Example");
    frame.setSize(1017,791);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setVisible(true);
    
    
    
    
    
    
    
    panel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                
                if(speed >= 10){
                    
                speed -= e.getWheelRotation() * 10;
                }
                else{
                    speed = 10;
                }
            }
});
    
    
    
    
    
    
    panel.addMouseListener(new MouseAdapter() {
     @Override
     public void mousePressed(MouseEvent e) {
         switch (e.getButton()) {
             case MouseEvent.BUTTON1:
                 {
                     Point b = e.getPoint();
                     int x = (int) b.getX();
                     int y = (int) b.getY();
                     panel.g.temp[y / 10][x / 10].makeAlive();
                     panel.g.grid[y / 10][x / 10].makeAlive();
                     frame.repaint();
                     break;
                 }
             case MouseEvent.BUTTON2:
                 speed = 100;
                 panel.g.clearGrid();
                 frame.repaint();
                 break;
             case MouseEvent.BUTTON3:
                 {
                     Point b = e.getPoint();
                     int x = (int) b.getX();
                     int y = (int) b.getY();
                     panel.g.temp[y / 10][x / 10].makeDead();
                     panel.g.grid[y / 10][x / 10].makeDead();
                     frame.repaint();
                     break;
                 }
             default:
                 break;
         }
     }
  });
    
    while(g){
        if(panel.getPause()){
        frame.repaint();
        panel.g.updateGrid();
        TimeUnit.MILLISECONDS.sleep(speed);
        }
    }
    


    }
  
       }



class MyPanel extends JPanel {
     public Grid g = new Grid(75,100);
     public boolean pause = false;
     int h = 2;
     public MyPanel()
     {
         for(int i = 0; i < 75; i++)
         {
             for(int j = 0; j < 100; j++)
             {
                 Random rand = new Random();
                 g.makeAlive((int)(rand.nextInt(75) ), (int)(rand.nextInt(100)));
             }
         }
         InputMap im = getInputMap(WHEN_FOCUSED);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "onEscape");
        am.put("onEscape", new AbstractAction() {
            @Override
             public void actionPerformed(ActionEvent e) {
              
               System.out.println("hello");
                
            }
        });
        
     }
     
     
     
     public boolean getPause(){
        InputMap im = getInputMap(WHEN_FOCUSED);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "onEnter");
        am.put("onEnter", new AbstractAction() {
            @Override
             public void actionPerformed(ActionEvent e) {
              
               if(h == 1){
                   pause = false;
                   h = 2;
                   
               }
               else if(h == 2){
                   pause = true;
                   h = 1;
                   
               }
                
            }
        });
         return pause;
     }
     public void clear(){
         InputMap im = getInputMap(WHEN_FOCUSED);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "onC");
        am.put("onC", new AbstractAction() {
            @Override
             public void actionPerformed(ActionEvent e) {
              
              g.clearGrid();
                
            }
        });
     }
     @Override
  public void paint(Graphics gr) 
  {    
    for(int i = 0; i < 75; i++ )
    {
        for(int j = 0; j < 100; j++)
        {
            gr.setColor(g.getCell(i, j).getColor());
            gr.fillRect(g.getCell(i, j).getX(), g.getCell(i, j).getY(), 10, 10);
//            if(!getPause()){
//            repaint();
//            gr.setColor(Color.darkGray);
//            gr.drawRect(g.getCell(i, j).getX(), g.getCell(i, j).getY(), 10, 10);
//            }
        }
    }
    
   }
  }

        
    
    

