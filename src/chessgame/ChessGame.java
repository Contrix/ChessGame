package chessgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Jirka
 */
public class ChessGame extends Application {
    private final Kresleni k = new Kresleni();
    private boolean bilyNaTahu = true; 
    private boolean oznacenaFigurka = false;
    private Figurka fig;
    private boolean sach = false;
    
    //bílí pěšáci
    private final Figurka bp1 = new Pesec(true, 0, 6, -2, 0);
    private final Figurka bp2 = new Pesec(true, 1, 6, -2, 1);
    private final Figurka bp3 = new Pesec(true, 2, 6, -2, 2);
    private final Figurka bp4 = new Pesec(true, 3, 6, -2, 3);
    private final Figurka bp5 = new Pesec(true, 4, 6, -2, 4);
    private final Figurka bp6 = new Pesec(true, 5, 6, -2, 5);
    private final Figurka bp7 = new Pesec(true, 6, 6, -2, 6);
    private final Figurka bp8 = new Pesec(true, 7, 6, -2, 7);
    //bílé věže
    private final Figurka bv1 = new Vez(true, 0, 7, -3, 0);
    private final Figurka bv2 = new Vez(true, 7, 7, -3, 1);
    //bílí jezdci
    private final Figurka bj1 = new Jezdec(true, 1, 7, -3, 2);
    private final Figurka bj2 = new Jezdec(true, 6, 7, -3, 3);
    //bílí střelci
    private final Figurka bs1 = new Strelec(true, 2, 7, -3, 4);
    private final Figurka bs2 = new Strelec(true, 5, 7, -3, 5);
    //bílí panovníci
    private final Figurka bd = new Dama(true, 3, 7, -3, 6);
    private final Figurka bk = new Kral(true, 4, 7, -3, 7);    
    
    //černí pěšáci
    private final Figurka cp1 = new Pesec(false, 0, 1, 9, 0);
    private final Figurka cp2 = new Pesec(false, 1, 1, 9, 1);
    private final Figurka cp3 = new Pesec(false, 2, 1, 9, 2);
    private final Figurka cp4 = new Pesec(false, 3, 1, 9, 3);
    private final Figurka cp5 = new Pesec(false, 4, 1, 9, 4);
    private final Figurka cp6 = new Pesec(false, 5, 1, 9, 5);
    private final Figurka cp7 = new Pesec(false, 6, 1, 9, 6);
    private final Figurka cp8 = new Pesec(false, 7, 1, 9, 7);
    //černé věže
    private final Figurka cv1 = new Vez(false, 0, 0, 10, 0);
    private final Figurka cv2 = new Vez(false, 7, 0, 10, 1);
    //černí jezdci
    private final Figurka cj1 = new Jezdec(false, 1, 0, 10, 2);
    private final Figurka cj2 = new Jezdec(false, 6, 0, 10, 3);
    //černí střelci
    private final Figurka cs1 = new Strelec(false, 2, 0, 10, 4);
    private final Figurka cs2 = new Strelec(false, 5, 0, 10, 5);
    //černí panovníci
    private final Figurka cd = new Dama(false, 3, 0, 10, 6);
    private final Figurka ck = new Kral(false, 4, 0, 10, 7);
    
    public ArrayList<Figurka> figurky = new ArrayList<>(Arrays.asList(bp1, bp2, bp3, bp4, bp5, bp6, bp7, bp8, bv1, bv2, bj1, bj2, bs1, bs2, bd, bk,
                                       cp1, cp2, cp3, cp4, cp5, cp6, cp7, cp8, cv1, cv2, cj1, cj2, cs1, cs2, cd, ck));
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        //Okno aplikace
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 880, 605);
        primaryStage.setTitle("Chess game");
        
        //Vykreslovací plocha
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        vykresli(gc, canvas.getWidth(), canvas.getHeight());
        
        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());
        
        //nastavení timeru
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                k.setHodiny(bilyNaTahu);
                vykresli(gc, canvas.getWidth(), canvas.getHeight());
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        
        //klinutí myší
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
        new EventHandler<MouseEvent>() 
        {
            int x;
            int y; 
            
            @Override
            public void handle(MouseEvent e) 
            {
                x = (int)(e.getX()-k.getPosunX()-4*k.getVelikostCtverce())/k.getVelikostCtverce();
                y = (int)(e.getY()-k.getPosunY()-2*k.getVelikostCtverce())/k.getVelikostCtverce();
                if (x >= 0 && x < k.getVelikostHraciPlochy() && y >= 0 && y < k.getVelikostHraciPlochy())
                {
                    if(oznacenaFigurka)
                    {
                        if(fig.getMoznostPohybu(x, y) == 't')
                        {
                            if (isSach(fig, x, y, fig.getX(), fig.getY(), false))
                            {
                                setSach(timer);
                            }
                            else
                            {
                                fig.setPozice(x, y);
                                setHracNaTahu();
                                sach = false;
                                if(y == 0 || y == 7)
                                {
                                    povyseni();
                                }
                                if (isSach(fig, x, y, fig.getX(), fig.getY(), false))
                                {
                                    setSach(timer);
                                }
                            }
                        }
                        else if (fig.getMoznostPohybu(x, y) == 'm')
                        {
                            if (isSachM(fig, x, y, fig.getX(), fig.getY(), false, null))
                            {
                                setSach(timer);
                            }
                            else
                            {
                                for (Figurka f : figurky)
                                {
                                    if (f.isPoziceFigurky(x, y))
                                    {
                                        f.setVyhodit();
                                        break;
                                    }
                                }
                                fig.setPozice(x, y);
                                setHracNaTahu();
                                sach = false;
                                if(y == 0 || y == 7)
                                {
                                    povyseni();
                                }
                                if (isSachM(fig, x, y, fig.getX(), fig.getY(), false, null))
                                {
                                    setSach(timer);
                                }
                            }
                        }
                        oznacenaFigurka = false;
                        k.resetMoznostPohybu(sach);
                    }
                    else
                    {
                        for (Figurka f : figurky)
                        {
                            if (f.isPoziceFigurky(x, y))
                            {
                                if(bilyNaTahu && f.getBarvaBila() || !bilyNaTahu && !f.getBarvaBila())
                                {
                                    f.setPohyb(figurky);
                                    oznacenaFigurka = true;
                                    fig = f;
                                    k.setZvyrazneni(false);
                                }
                            }
                        }
                    }
                    vykresli(gc, canvas.getWidth(), canvas.getHeight());
                }
            }
        });

        //vykrslení plochy
        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
    }
    
    private void vykresli(GraphicsContext gc, double width, double height)
    {
        k.setZvyrazneni(sach);
        k.setVelikostPixelu(gc, width, height);
        k.vykresliPozadi(gc, width, height);
        k.vypisHraceNaTahu(gc, width, height);
        k.vykresliSachovnici(true, gc);
        k.vykresliVyhozeniste(gc, true);
        k.vykresliHodiny(gc);
        figurky.stream().forEach((f) -> {
            f.vykresli(gc);
        });
    }
    
    private void povyseni()
    {
        if(fig instanceof Pesec)
        {
            figurky.add(new Dama(fig.getBarvaBila(), fig.getX(), fig.getY(), fig.getXX(), fig.getYY())); 
            figurky.remove(fig);
        }
    }
    
    private void setHracNaTahu()
    {
        bilyNaTahu = !bilyNaTahu;
        if(bilyNaTahu)
        {
            k.setText("Na tahu jsou bílé figurky");
        }
        else
        {
            k.setText("Na tahu jsou černé figurky");
        }
    }
    
    private boolean isSach(Figurka figurka, int setX, int setY, int getX, int getY, boolean sachMat)
    {
        figurka.setPozice(setX, setY);
        for (Figurka f:figurky)
        {
            f.setPohyb(figurky);
            if(bilyNaTahu)
            {
                if (f.getMoznostPohybu(bk.getX(), bk.getY()) == 'm')
                {
                    figurka.setPozice(getX, getY);
                    if (!sachMat)
                        k.setZvyrazneni(f.getX(), f.getY());
                    return (true);
                }
            }
            else
            {
                if (f.getMoznostPohybu(ck.getX(), ck.getY()) == 'm')
                {
                    figurka.setPozice(getX, getY);
                    if (!sachMat)
                        k.setZvyrazneni(f.getX(), f.getY());
                    return (true);
                }
            }            
        }
        figurka.setPozice(getX, getY);
        return(false);
    }
    
    private boolean isSachM(Figurka figurka, int setX, int setY, int getX, int getY, boolean sachMat, Figurka vyhozena)
    {
        for (Figurka f : figurky)
        {
            if (f.isPoziceFigurky(setX, setY))
            {
                f.setPozice(0, 0);
                vyhozena = f;
                break;
            }
        }
        figurka.setPozice(setX, setY);
        for (Figurka f:figurky)
        {
            f.setPohyb(figurky);
            if(bilyNaTahu)
            {
                if (f.getMoznostPohybu(bk.getX(), bk.getY()) == 'm')
                {
                    figurka.setPozice(getX, getY);
                    vyhozena.setPozice(setX, setY);
                    if (!sachMat)
                        k.setZvyrazneni(f.getX(), f.getY());
                    return (true);
                }
            }
            else
            {
                if (f.getMoznostPohybu(ck.getX(), ck.getY()) == 'm')
                {
                    figurka.setPozice(getX, getY);
                    vyhozena.setPozice(setX, setY);
                    if (!sachMat)
                        k.setZvyrazneni(f.getX(), f.getY());
                   return (true);
                }
            }            
        }
        figurka.setPozice(getX, getY);
        vyhozena.setPozice(setX, setY);
        return(false);
    }
    
    private boolean isSachMat()
    {
        for (Figurka f:figurky)
        {
            if ((f.getBarvaBila() && bilyNaTahu) || (!f.getBarvaBila() && !bilyNaTahu))
            {
                f.setPohyb(figurky);
                for (int i = 0; i < k.getVelikostHraciPlochy(); i++)
                {
                    for (int j = 0; j < k.getVelikostHraciPlochy(); j++)
                    {
                        if (f.getMoznostPohybu(i, j) == 't')
                        {
                            if (!isSach(f, i, j, f.getX(), f.getY(), true))
                            {
                                return (false);
                            }
                        }
                        else if (f.getMoznostPohybu(i, j) == 'm')
                        {
                            if (!isSachM(f, i, j, f.getX(), f.getY(), true, null))
                            {
                                return (false);
                            }
                        }
                    }
                }
            }
        }
        return (true);
    }
    
    private void setSach(Timeline timer)
    {
        k.setText("Panovník ohrožen - šach");
        sach = true;
        if (isSachMat())
        {
            setSachMat(timer);
        }
    }
    
    private void setSachMat (Timeline timer)
    {
        k.setText("Šach mat!");
        timer.stop();
    }
}