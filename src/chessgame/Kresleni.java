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
public class Kresleni {
    private final int pixel = 5;
    private final int velikostCtverce = 11;
    
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
                gc.fillRect(i*velikostCtverce*pixel, j*velikostCtverce*pixel, velikostCtverce*pixel, velikostCtverce*pixel);
            }
            bila=!bila;
        }
        //gc.fillRect(0*velikostCtverce, 0*velikostCtverce, velikostCtverce*pixel, velikostCtverce*pixel);
    }
}
