/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Jirka
 */
public class Kresleni {
    private final int pixel = 5;
    private final int velikostCtverce = 11;
    private final int velikostHraciPlochy = 8;
    private static boolean[][] moznostPohybu = new boolean[8][8];
    
    
    public void vykresliPozadi(GraphicsContext gc, double width, double height)
    {
        gc.setFill(Color.BLANCHEDALMOND);
        gc.fillRect(0, 0, width, height);
    }
    
    public void vykresliSachovnici(boolean bila, GraphicsContext gc)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if(bila)
                    gc.setFill(Color.WHITE);
                else
                    gc.setFill(Color.BLACK);
                bila=!bila;
                gc.fillRect(i*velikostCtverce*pixel+150, j*velikostCtverce*pixel+100, velikostCtverce*pixel, velikostCtverce*pixel);
                if (moznostPohybu[i][j])
                {
                    gc.setFill(Color.color(0, .7, 0, 0.5));
                    gc.fillRect(i*velikostCtverce*pixel+150, j*velikostCtverce*pixel+100, velikostCtverce*pixel, velikostCtverce*pixel);
                }
            }
            bila=!bila;
        }
    }
    
    public void vykresliFigurku(Color[][] pole, int x, int y, GraphicsContext gc)
    {
        for (int i = 0; i < velikostCtverce; i++)
        {
            for (int j = 0; j < velikostCtverce; j++)
            {
                gc.setFill(pole[j][i]);
                gc.fillRect(x*velikostCtverce*pixel+i*pixel+150, y*velikostCtverce*pixel+j*pixel+100,pixel,pixel);
            }
        }
    }
    
    public void setMoznostPohybu(boolean[][] pohyb)
    {
        Kresleni.moznostPohybu=pohyb;
    }
    
    public void vypisHraceNaTahu(boolean hracNaTahu, GraphicsContext gc)
    {
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        if(hracNaTahu)
            gc.fillText("Na tahu jsou bílé figurky", 15, 50);
        else
            gc.fillText("Na tahu jsou černé figurky", 15, 50);
    }
    
    public Color getColorWhite()
    {
        return(Color.WHITE);
    }
    
    public Color getColorBlack()
    {
        return(Color.BLACK);
    }
    
    public Color getColorTransparent()
    {
        return(Color.TRANSPARENT);
    }
    
    public int getVelikostHraciPlochy()
    {
        return(velikostHraciPlochy);
    }
}

