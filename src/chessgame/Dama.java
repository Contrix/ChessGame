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
public class Dama extends Figurka{
    
    /** Vykreslí samotnou figurku
    * @param  bila Barva figurky T = bílá, F = černá
     * @param x x-ová počáteční souřadnice figurky (spawn)
     * @param y y-ová počáteční souřadnice figurky (spawn)
     * @param xx x-ová souřadnice vyhozené figurky
     * @param yy y-ová souřadnice vyhozené figurky
    */
    public Dama(boolean bila, int x, int y, int xx, int yy)
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
                    {t,t,b,t,t,t,t,t,b,t,t},
                    {t,b,a,b,t,b,t,b,a,b,t},
                    {t,b,a,a,b,a,b,a,a,b,t},
                    {t,t,b,a,a,a,a,a,b,t,t},
                    {t,t,t,b,a,a,a,b,t,t,t},
                    {t,t,b,a,a,a,a,a,b,t,t},
                    {t,b,a,a,a,a,a,a,a,b,t},
                    {t,b,a,a,a,a,a,a,a,b,t},
                    {t,t,b,b,b,b,b,b,b,t,t},
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
            //doleva 
            for (int i = 1; x - i >= 0; i++)
            {
                pohyb[x-i][y] = isVolnyCtverec(figurky, x-i, y);
                if (pohyb[x-i][y] != 't')
                    break;
            }
            //doprava
            for (int i = 1; x + i < k.getVelikostHraciPlochy(); i++)
            {
                pohyb[x+i][y] = isVolnyCtverec(figurky, x+i, y);
                if (pohyb[x+i][y] != 't')
                    break;
            }
            //nahoru
            for (int i = 1; y - i >= 0; i++)
            {
                pohyb[x][y-i] = isVolnyCtverec(figurky, x, y-i);
                if (pohyb[x][y-i] != 't')
                    break;
            }
            //dolu
            for (int i = 1; y + i < k.getVelikostHraciPlochy(); i++)
            {
                pohyb[x][y+i] = isVolnyCtverec(figurky, x, y+i);
                if (pohyb[x][y+i] != 't')
                    break;
            }
             //45°
            for (int i = 1; (x + i) < k.getVelikostHraciPlochy() && (y - i) >= 0;i++)
            {
                pohyb[x + i][y - i] = isVolnyCtverec(figurky, x + i, y - i);
                if (pohyb[x + i][y - i] != 't')
                    break;
            }
            //135°
            for (int i = 1; (x + i) < k.getVelikostHraciPlochy() && (y + i) < k.getVelikostHraciPlochy();i++)
            {
                pohyb[x + i][y + i] = isVolnyCtverec(figurky, x + i, y + i);
                if (pohyb[x + i][y + i] != 't')
                    break;
            }
            //225°
            for (int i = 1; (x - i) >= 0 && (y + i) < k.getVelikostHraciPlochy();i++)
            {
                pohyb[x - i][y + i] = isVolnyCtverec(figurky, x - i, y + i);
                if (pohyb[x - i][y + i] != 't')
                    break;
            }
            //315°
            for (int i = 1; (x - i) >= 0 && (y - i) >= 0;i++)
            {
                pohyb[x - i][y - i] = isVolnyCtverec(figurky, x - i, y - i);
                if (pohyb[x - i][y - i] != 't')
                    break;
            }
            pohyb[x][y]='s';
            k.setMoznostPohybu(pohyb);
        }
    }
}
