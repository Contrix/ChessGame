/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Jirka
 */
public class Kral extends Figurka{
    
    /** Vykreslí samotnou figurku
    * @param  bila Barva figurky T = bílá, F = černá
     * @param x x-ová počáteční souřadnice figurky (spawn)
     * @param y y-ová počáteční souřadnice figurky (spawn)
     * @param xx x-ová souřadnice vyhozené figurky
     * @param yy y-ová souřadnice vyhozené figurky
    */
    public Kral(boolean bila, int x, int y, int xx, int yy)
    {
        this.bila = bila;
        this.x = x;
        this.y = y;
        this.xx=xx;
        this.yy=yy;
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
                    {t,t,t,b,b,a,b,b,t,t,t},
                    {t,t,b,a,a,a,a,a,b,t,t},
                    {t,b,a,a,a,a,a,a,a,b,t},
                    {t,b,a,a,a,a,a,a,a,b,t},
                    {t,t,b,a,a,a,a,a,b,t,t},
                    {t,t,t,b,b,b,b,b,t,t,t},
                    {t,t,t,t,t,t,t,t,t,t,t}};
        k.vykresliFigurku(pole, x, y, gc);
    }
    
    @Override
    public void setPohyb(ArrayList<Figurka> figurky)
    {
        if(x >= 0 && x < k.getVelikostHraciPlochy())
        {
            for(int i = 0; i < k.getVelikostHraciPlochy(); i++)
                for(int j = 0; j < k.getVelikostHraciPlochy(); j++)
                    pohyb[i][j] = 'f';
            //nahoru
            if (y - 1 >= 0)
                pohyb[x][y - 1] = isVolnyCtverec(figurky, x, y - 1);
            //dolu
            if (y + 1 < k.getVelikostHraciPlochy())
                pohyb[x][y + 1] = isVolnyCtverec(figurky, x, y + 1);
            //doleva
            if (x - 1 >= 0)
                pohyb[x - 1][y] = isVolnyCtverec(figurky, x - 1, y);
            //doprava
            if (x + 1 < k.getVelikostHraciPlochy())
                pohyb[x + 1][y] = isVolnyCtverec(figurky, x + 1, y);
            //45
            if (x + 1 < k.getVelikostHraciPlochy() && y - 1 >= 0)
                pohyb[x + 1][y - 1] = isVolnyCtverec(figurky, x + 1, y - 1);
            //135
            if (x + 1 < k.getVelikostHraciPlochy() && y + 1 < k.getVelikostHraciPlochy())
                pohyb[x + 1][y + 1] = isVolnyCtverec(figurky, x + 1, y + 1);
            //225
            if (x - 1 >= 0 && y + 1 < k.getVelikostHraciPlochy())
                pohyb[x - 1][y + 1] = isVolnyCtverec(figurky, x - 1, y + 1);
            //315
            if (x - 1 >= 0 && y - 1 >= 0)
                pohyb[x - 1][y - 1] = isVolnyCtverec(figurky, x - 1, y - 1);
            pohyb[x][y]='s';
            k.setMoznostPohybu(pohyb);
        }
    }
}
