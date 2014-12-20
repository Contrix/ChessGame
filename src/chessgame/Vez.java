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
public class Vez extends Figurka{
    
    public Vez(boolean bila, int x, int y)
    {
        this.bila=bila;
        //this.bod=bod;
        this.x=x;
        this.y=y;
    }
    
    /**
     * Vykreslí figurku
     //* @param g
     */
    @Override
    public void vykresli(GraphicsContext gc)
    {
        if(bila)
        {
            this.a = k.getColorWhite();
            this.b = k.getColorBlack();
        }
        pole = new Color[][]{
                    {t,t,t,t,t,t,t,t,t,t,t}, 
                    {t,t,b,t,b,t,b,t,b,t,t}, 
                    {t,b,a,b,a,b,a,b,a,b,t}, 
                    {t,b,a,a,a,a,a,a,a,b,t}, 
                    {t,t,b,a,a,a,a,a,b,t,t}, 
                    {t,t,t,b,a,a,a,b,t,t,t}, 
                    {t,t,t,b,a,a,a,b,t,t,t}, 
                    {t,t,b,a,a,a,a,a,b,t,t}, 
                    {t,b,a,a,a,a,a,a,a,b,t}, 
                    {t,b,b,b,b,b,b,b,b,b,t}, 
                    {t,t,t,t,t,t,t,t,t,t,t}};
        //kresleni.vykresliFigurku(a, b, pole, g, bod);
        k.vykresliFigurku(pole, x,y, gc);
    }
    
   /*
    @Override
    public void getPohyb()
    {
        for(int i=0;i<konstanty.getVelikostPole();i++)
            for(int j=0;j<konstanty.getVelikostPole();j++)
            {
                //svislý pohyb
                if(bod.x==i && bod.y!=j)
                    pohyb[i][j]=true;
                //Vodorovný pohyb
                if(bod.y==j && bod.x!=i)
                    pohyb[i][j]=true;
            }
        kresleni.setPohyb(pohyb);
    }*/
}