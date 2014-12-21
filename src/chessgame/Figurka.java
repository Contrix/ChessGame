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
public class Figurka {
    Kresleni k = new Kresleni();
    
    protected boolean bila;
    protected int x;
    protected int y;
    protected Color[][] pole;
    protected Color a = k.getColorBlack();
    protected Color b = k.getColorWhite();
    protected Color t = k.getColorTransparent();
    protected boolean[][] pohyb = new boolean[k.getVelikostHraciPlochy()][k.getVelikostHraciPlochy()];
    
    public void Figurka(boolean bila, int x, int y)
    {
        this.bila=bila;
        //this.bod=bod;
        this.x=x;
        this.y=y;
    }
    
    /** Vykreslí samotnou figurku
    * @param  gc GraphicsContext
    */
    public void vykresli(GraphicsContext gc)
    {
        
    }

    /** Vykreslí možnost pohybu
     * @param figurky pole figurek
    */
    public void getPohyb(Figurka[] figurky)
    {
        
    }
    
    public boolean isVolnyCtverec (Figurka[] figurky, int x, int y)
    {
        for (Figurka f:figurky)
            if (f.isPoziceFigurky(x, y))
                if ((bila && f.getBarvaBila())|| (!bila && !f.getBarvaBila()))
                    return (false);
        return(true);
    }
    
    public boolean getMoznostPohybu(int x, int y)
    {
        return(pohyb[x][y]);
    }
    
    /** Změní pozici figurky
    * @param  x x-ová souřadnice figurky
    * @param  y y-ová souřadnice figurky
    */
    public void setPozice(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public boolean isPoziceFigurky(int x, int y)
    {
        if (this.x == x && this.y == y)
            return (true);
        else
            return (false);
    }
    
    public boolean getBarvaBila()
    {
        return(bila);
    }
}
