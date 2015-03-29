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
public class Figurka {
    Kresleni k = new Kresleni();
    protected boolean bila;
    protected int x;
    protected int y;
    protected int xx;
    protected int yy;
    protected Color[][] pole;
    protected Color a = k.getColorBlack();
    protected Color b = k.getColorWhite();
    protected Color t = k.getColorTransparent();
    protected char[][] pohyb = new char[k.getVelikostHraciPlochy()][k.getVelikostHraciPlochy()];
    
    /** Vykreslí samotnou figurku
    * @param  bila Barva figurky T = bílá, F = černá
     * @param x x-ová počáteční souřadnice figurky (spawn)
     * @param y y-ová počáteční souřadnice figurky (spawn)
     * @param xx x-ová souřadnice vyhozené figurky
     * @param yy y-ová souřadnice vyhozené figurky
    */
    public void Figurka(boolean bila, int x, int y, int xx, int yy)
    {
        this.bila = bila;
        this.x = x;
        this.y = y;
        this.xx = xx;
        this.yy = yy;
    }
    
    /** Vykreslí samotnou figurku
    * @param  gc GraphicsContext
    */
    public void vykresli(GraphicsContext gc){
    }

    /** Vykreslí možnost pohybu
     * @param figurky pole figurek
    */
    public void setPohyb(ArrayList<Figurka> figurky){
    }
    
    /** Vykreslí možnost pohybu
     * @param figurky pole figurek
     * @param  x x-ová souřadnice figurky
     * @param  y y-ová souřadnice figurky
     * @return f  = false, t = true, m = match
    */
    public char isVolnyCtverec (ArrayList<Figurka> figurky, int x, int y)
    {
        for (Figurka f:figurky)
            if (f.isPoziceFigurky(x, y))
            {
                if ((bila && f.getBarvaBila())|| (!bila && !f.getBarvaBila()))
                    return ('f');
                else
                    return ('m');
            }
        return('t');
    }
    
    public char getMoznostPohybu(int x, int y)
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
    
    public void setVyhodit()
    {
        setPozice(xx, yy);
    }
    
    public boolean getBarvaBila()
    {
        return(bila);
    }
    
    public int getX()
    {
        return(x);
    }
    
    public int getY()
    {
        return(y);
    }
    
    public int getXX()
    {
        return(xx);
    }
    
    public int getYY()
    {
        return(yy);
    }
}