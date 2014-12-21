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
public class Vez extends Figurka{
    
    public Vez(boolean bila, int x, int y)
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
        k.vykresliFigurku(pole, x, y, gc);
    }
    
    @Override
    public void getPohyb(Figurka[] figurky)
    {
        /*for(int i = 0; i < k.getVelikostHraciPlochy(); i++)
            for(int j = 0; j < k.getVelikostHraciPlochy(); j++)
            {
                //svislý pohyb
                if(x == i && y != j)
                    pohyb[i][j] = true;
                //Vodorovný pohyb
                else if(y == j && x != i)
                    pohyb[i][j] = true;
                else
                    pohyb[i][j] = false;
                
                if(pohyb[i][j])
                {
                    for (Figurka f:figurky)
                        if (f.isPoziceFigurky(i, j))
                            if ((bila && f.getBarvaBila())|| (!bila && !f.getBarvaBila()))
                                pohyb[i][j] = false;
                }
            }*/
        //doleva
        for (int i = 1; x - i >= 0; i++)
        {
            if (isVolnyCtverec(figurky, x-1, y))
                pohyb[x-i][y] = true;
            else
                break;
        }
        //doprava
        for (int i = 1; x + i < k.getVelikostHraciPlochy(); i++)
        {
            if (isVolnyCtverec(figurky, x+1, y))
                pohyb[x+i][y] = true;
            else
                break;
        }
        //nahoru
        for (int i = 1; y - i >= 0; i++)
        {
            if (isVolnyCtverec(figurky, x, y-i))
                pohyb[x][y-i] = true;
            else
                break;
        }
        //dolu
        for (int i = 1; y + i < k.getVelikostHraciPlochy(); i++)
        {
            if (isVolnyCtverec(figurky, x, y+i))
                pohyb[x][y+i] = true;
            else
                break;
        }
            
        
        
        k.setMoznostPohybu(pohyb);
    }
}