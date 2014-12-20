/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Jirka
 */
public class Pesec extends Figurka{
    
    public Pesec(boolean bila, int x, int y)
    {
        this.bila = bila;
        this.x = x;
        this.y = y;
    }
    
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
                    {t,t,t,t,t,b,t,t,t,t,t},
                    {t,t,t,t,b,a,b,t,t,t,t},
                    {t,t,t,b,a,a,a,b,t,t,t},
                    {t,t,b,a,a,a,a,a,b,t,t},
                    {t,t,t,b,a,a,a,b,t,t,t},
                    {t,t,t,t,b,a,b,t,t,t,t},
                    {t,t,t,b,a,a,a,b,t,t,t},
                    {t,t,b,a,a,a,a,a,b,t,t},
                    {t,t,t,b,b,b,b,b,t,t,t},
                    {t,t,t,t,t,t,t,t,t,t,t}};
        k.vykresliFigurku(pole, x, y, gc);
    }
    
    @Override
    public void getPohyb()
    {
        for(int i = 0; i < k.getVelikostHraciPlochy(); i++)
            for(int j = 0; j < k.getVelikostHraciPlochy(); j++)
            {
                //pohyb 45° a 225°
                if(i + j == x + y)
                    pohyb[i][j] = true;
                //pohyb 135° a 315°
                else if(i - j == x - y)
                    pohyb[i][j] = true;
                else
                    pohyb[i][j] = false;
                
                
            }
        k.setMoznostPohybu(pohyb);
    }
}
