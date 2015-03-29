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
public class Jezdec extends Figurka{
    
    /** Vykreslí samotnou figurku
    * @param  bila Barva figurky T = bílá, F = černá
     * @param x x-ová počáteční souřadnice figurky (spawn)
     * @param y y-ová počáteční souřadnice figurky (spawn)
     * @param xx x-ová souřadnice vyhozené figurky
     * @param yy y-ová souřadnice vyhozené figurky
    */
    public Jezdec(boolean bila, int x, int y, int xx, int yy)
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
                    {t,t,t,t,t,b,b,t,t,t,t},
                    {t,t,t,t,b,a,a,b,t,t,t},
                    {t,t,t,b,a,a,b,a,b,t,t},
                    {t,t,b,a,a,a,a,a,b,t,t},
                    {t,b,a,t,b,a,a,a,b,t,t},
                    {t,t,t,b,a,a,a,b,t,t,t},
                    {t,t,b,a,a,a,a,a,b,t,t},
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
                {
                    //pohyb horní, dolní
                    if((i == x - 1 || i == x + 1) && (j == y - 2 || j == y + 2))
                        pohyb[i][j] = isVolnyCtverec(figurky, i, j);
                    //pohyb levý, pravý
                    else if((i == x - 2 || i == x + 2) && (j == y - 1 || j == y + 1))
                        pohyb[i][j] = isVolnyCtverec(figurky, i, j);
                    else
                        pohyb[i][j] = 'f';
                }
            pohyb[x][y]='s';
            k.setMoznostPohybu(pohyb);
        }
    }
}
