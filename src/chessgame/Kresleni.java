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
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Jirka
 */
public class Kresleni {
    private static int pixel = 5;
    private static final int dilkuVeCtverci = 11;
    private static final int velikostHraciPlochy = 8;
    private static char[][] moznostPohybu = new char[8][8];
    private static final boolean[][] zvyrazneni =  new boolean[8][8]; 
    
    private final int casBily[] = {0,0,0};
    private final int casCerny[] = {0,0,0};
    
    private static int posunX = 0;
    private static int posunY = 0;
    
    private String text = "Na tahu jsou bílé figurky";
    
    public void vykresliPozadi(GraphicsContext gc, double width, double height)
    {
        gc.setFill(Color.TAN);
        gc.fillRect(0, 0, width, height);
        
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 2 * pixel));
        gc.fillText("© Jiří Hanák", width - pixel * 15, height - 2 * pixel);
        gc.fillText("v 1.0", 2 * pixel, height - 2 * pixel);
    }
    
    public void vykresliSachovnici(boolean bila, GraphicsContext gc)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if(bila)
                {
                    gc.setFill(Color.WHITE);
                }
                else
                {
                    gc.setFill(Color.BLACK);
                }
                bila =! bila;
                gc.fillRect(i * getVelikostCtverce() + 4 * getVelikostCtverce() + posunX, j * getVelikostCtverce() + 2 * getVelikostCtverce() + posunY, getVelikostCtverce(), getVelikostCtverce());
                if (moznostPohybu[i][j] != 'f')
                {
                    if (moznostPohybu[i][j] == 't')
                    {
                        gc.setFill(Color.color(0, .7, 0, 0.5));
                    }
                    else if (moznostPohybu[i][j] == 'm')
                    {
                        gc.setFill(Color.color(.95, .3, .08, 0.7));
                    }
                    else if (moznostPohybu[i][j] == 's')
                    {
                        gc.setFill(Color.color(1, .8, 0, 0.7));
                    }
                    gc.fillRect(i * getVelikostCtverce() + 4 * getVelikostCtverce() + posunX, j * getVelikostCtverce() + 2 * getVelikostCtverce() + posunY, getVelikostCtverce(), getVelikostCtverce());
                }
                if(zvyrazneni[i][j])
                {
                    gc.setFill(Color.color(0, .62, .9, 0.7));
                    gc.fillRect(i * getVelikostCtverce() + 4 * getVelikostCtverce() + posunX, j * getVelikostCtverce() + 2 * getVelikostCtverce() + posunY, getVelikostCtverce(), getVelikostCtverce());
                }
            }
            bila=!bila;
        }
    }
    
    public void setHodiny (boolean bilyNaTahu)
    {
        if(bilyNaTahu)
        {
            casBily[0]++;
        }
        else
        {
            casCerny[0]++;
        }
    }
    
    public void vykresliHodiny (GraphicsContext gc)
    {        
        if(casBily[0]==60)
        {
            casBily[0]=0;
            casBily[1]++;
        }
        if(casBily[1]==60)
        {
            casBily[1]=0;
            casBily[2]++;
        }        
        if(casCerny[0]==60)
        {
            casCerny[0]=0;
            casCerny[1]++;
        }
        if(casCerny[1]==60)
        {
            casCerny[1]=0;
            casCerny[2]++;
        }        
        
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 4 * pixel));
        gc.fillText(String.format("%d : %2d : %2d", casBily[2], casBily[1], casBily[0]), getVelikostCtverce() + posunX, getVelikostCtverce() + posunY);
        gc.fillText(String.format("%d : %2d : %2d", casCerny[2], casCerny[1], casCerny[0]), 13 * getVelikostCtverce() + posunX, getVelikostCtverce() + posunY);
    }
    
    public void vykresliVyhozeniste(GraphicsContext gc, boolean bila)
    {
        gc.setFill(Color.ORANGERED);
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < velikostHraciPlochy; j++)
            {
                if(bila)
                {
                    gc.setFill(Color.LIGHTGRAY);
                }
                else
                {
                    gc.setFill(Color.GREY);
                }
                bila=!bila;
                gc.fillRect(i * getVelikostCtverce() + 4 * getVelikostCtverce() - 3 * getVelikostCtverce() + posunX, j * getVelikostCtverce() + 2 * getVelikostCtverce() + posunY, getVelikostCtverce(), getVelikostCtverce());
                gc.fillRect(i * getVelikostCtverce() + 4 * getVelikostCtverce() + 9 * getVelikostCtverce() + posunX, j * getVelikostCtverce() + 2 * getVelikostCtverce() + posunY, getVelikostCtverce(), getVelikostCtverce());
            }
            bila=!bila;
        }
    }
    
    public void vykresliFigurku(Color[][] pole, int x, int y, GraphicsContext gc)
    {
        for (int i = 0; i < dilkuVeCtverci; i++)
        {
            for (int j = 0; j < dilkuVeCtverci; j++)
            {
                gc.setFill(pole[j][i]);
                gc.fillRect(x * getVelikostCtverce() + i * pixel + 4 * getVelikostCtverce() + posunX, y * getVelikostCtverce() + j * pixel + 2 * getVelikostCtverce() + posunY, pixel, pixel);
            }
        }
    }
    
    public void setMoznostPohybu(char[][] pohyb)
    {
        Kresleni.moznostPohybu = pohyb;
    }
    
    public void resetMoznostPohybu(boolean sach)
    {
        for (int i = 0; i < velikostHraciPlochy; i++)
        {
            for (int j = 0; j < velikostHraciPlochy; j++)
            {
                if(sach && moznostPohybu[i][j]=='z')
                {
                    moznostPohybu[i][j] = 'z';
                }
                else
                {
                    moznostPohybu[i][j] = 'f';
                }
            }
        }
    }
    
    public void setZvyrazneni(int x, int y)
    {
        zvyrazneni[x][y] = true;
    }
    
    public void setZvyrazneni(boolean zrusit)
    {
        if(zrusit == false)
        {
            for (int i = 0; i < velikostHraciPlochy; i++)
            {
                for (int j = 0; j < velikostHraciPlochy; j++)
                {
                    zvyrazneni[i][j] = false;
                }
            }
        }
    }
    
    public void vypisHraceNaTahu(GraphicsContext gc, double weight, double height)
    {
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 6 * pixel));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(text, weight / 2, getVelikostCtverce() + posunY);

    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public void setVelikostPixelu (GraphicsContext gc, double width, double height)
    {
        while (height > 11 * getVelikostCtverce())
            pixel++;
        while (height < 10.5 * getVelikostCtverce())
            pixel--;
        setPosun(width, height);
    }
    
    public void setPosun (double width, double height)
    {
        if(width > 16 * getVelikostCtverce())
            posunX = ((int)width - 16 * getVelikostCtverce())/2;
        else
            posunX = 0;
        if(height > 11 * getVelikostCtverce())
            posunY = ((int)height - 11 * getVelikostCtverce())/2;
        else
            posunY = 0;
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
    
    public int getVelikostCtverce()
    {
        return(pixel * dilkuVeCtverci);
    }
    
    public int getPosunX()
    {
        return(posunX);
    }
    
    public int getPosunY()
    {
        return(posunY);
    }
}
