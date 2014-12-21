package chessgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jirka
 */
public class ChessGame extends Application {
    private Kresleni k = new Kresleni();
    private boolean bilyNaTahu = true; 
    private boolean oznacenaFigurka = false;
    private Figurka fig;
    
    private int x;
    private int y;    
    
    //bílí pěšáci
    private final Figurka bp1 = new Pesec(true, 0, 6);
    private final Figurka bp2 = new Pesec(true, 1, 6);
    private final Figurka bp3 = new Pesec(true, 2, 6);
    private final Figurka bp4 = new Pesec(true, 3, 6);
    private final Figurka bp5 = new Pesec(true, 4, 6);
    private final Figurka bp6 = new Pesec(true, 5, 6);
    private final Figurka bp7 = new Pesec(true, 6, 6);
    private final Figurka bp8 = new Pesec(true, 7, 6);
    //bílé věže
    private final Figurka bv1 = new Vez(true, 3, 3);
    private final Figurka bv2 = new Vez(true, 7, 7);
    //bílí jezdci
    private final Figurka bj1 = new Jezdec(true, 1, 7);
    private final Figurka bj2 = new Jezdec(true, 6, 7);
    //bílí střelci
    private final Figurka bs1 = new Strelec(true, 2, 7);
    private final Figurka bs2 = new Strelec(true, 5, 7);
    //bílí panovníci
    private final Figurka bd = new Dama(true, 3, 7);
    private final Figurka bk = new Kral(true, 4, 7);    
    
    //černí pěšáci
    private final Figurka cp1 = new Pesec(false, 0, 1);
    private final Figurka cp2 = new Pesec(false, 1, 1);
    private final Figurka cp3 = new Pesec(false, 2, 1);
    private final Figurka cp4 = new Pesec(false, 3, 1);
    private final Figurka cp5 = new Pesec(false, 4, 1);
    private final Figurka cp6 = new Pesec(false, 5, 1);
    private final Figurka cp7 = new Pesec(false, 6, 1);
    private final Figurka cp8 = new Pesec(false, 7, 1);
    //černé věže
    private final Figurka cv1 = new Vez(false, 0, 0);
    private final Figurka cv2 = new Vez(false, 7, 0);
    //černí jezdci
    private final Figurka cj1 = new Jezdec(false, 1, 0);
    private final Figurka cj2 = new Jezdec(false, 6, 0);
    //černí střelci
    private final Figurka cs1 = new Strelec(false, 2, 0);
    private final Figurka cs2 = new Strelec(false, 5, 0);
    //černí panovníci
    private final Figurka cd = new Dama(false, 3, 0);
    private final Figurka ck = new Kral(false, 4, 0);
    
    public final Figurka[] figurky = {bp1, bp2, bp3, bp4, bp5, bp6, bp7, bp8, bv1, bv2, bj1, bj2, bs1, bs2, bd, bk,
                                       cp1, cp2, cp3, cp4, cp5, cp6, cp7, cp8, cv1, cv2, cj1, cj2, cs1, cs2, cd, ck};
    
    @Override
    public void start(Stage primaryStage) {
        
        //Okno aplikace
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Chess game");
        
        //Vykreslovací plocha
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        vykresli(gc, canvas.getWidth(), canvas.getHeight());

        //klinutí myší
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
        new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent e) 
            {
                x = (int)(e.getX()-150)/k.getVelikostCtverce();
                y = (int)(e.getY()-100)/k.getVelikostCtverce();
                if(oznacenaFigurka)
                {
                    if(fig.getMoznostPohybu(x, y))
                    {
                        fig.setPozice(x, y);
                        bilyNaTahu = !bilyNaTahu;
                    }
                    oznacenaFigurka = false;
                    k.resetMoznostPohybu();
                }
                else
                {
                    for (Figurka f : figurky)
                    {
                        if (f.isPoziceFigurky(x, y))
                        {
                            if(bilyNaTahu && f.getBarvaBila() || !bilyNaTahu && !f.getBarvaBila())
                            {
                                f.getPohyb(figurky);
                                oznacenaFigurka = true;
                                fig = f;
                            }
                        }
                    }
                }
                vykresli(gc, canvas.getWidth(), canvas.getHeight());
            }
        });
        
        /*
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.seconds(1),
        ae -> pokus()));
        timeline.play();*/
        
        
        
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
    
    public void vykresli(GraphicsContext gc, double width, double height)
    {
        k.vykresliPozadi(gc, width, height);
        k.vypisHraceNaTahu(bilyNaTahu, gc);
        k.vykresliSachovnici(true, gc);
        for (Figurka f:figurky)
            f.vykresli(gc);
    }
    
    public void pokus()
    {
        bj1.setPozice(3, 5);
    }
}
