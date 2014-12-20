/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;
import javafx.scene.paint.Color;

/**
 *
 * @author Jirka
 */
public class Figurka {
    Kresleni k = new Kresleni();
    
    protected boolean bila;
    //protected Point bod;
    protected int x;
    protected int y;
    protected Color[][] pole;
    protected Color a = k.getColorBlack();
    protected Color b = k.getColorWhite();
    protected Color t = k.getColorTransparent();
    
    public void Figurka(boolean bila, int x, int y)
    {
        this.bila=bila;
        //this.bod=bod;
        this.x=x;
        this.y=y;
    }
    
    public void vykresli(GraphicsContext gc)
    {
        
    }
    
    public void vykresliPohyb()
    {
        
    }
    
}
