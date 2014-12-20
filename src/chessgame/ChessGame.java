package chessgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.geom.Point2D;
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
    Kresleni k = new Kresleni();
    boolean bilyNaTahu = true; 
    boolean oznacenaFigurka = false;
    
    private final Figurka bv1 = new Vez(true, 3, 3);
    
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
                //aplikování změn
                bv1.setPozice(2, 2);
                bv1.getPohyb();
                vykresli(gc, canvas.getWidth(), canvas.getHeight());
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
    
    public void vykresli(GraphicsContext gc, double width, double height)
    {
        k.vykresliPozadi(gc, width, height);
        k.vykresliSachovnici(true, gc);
        bv1.vykresli(gc);        
        k.vypisHraceNaTahu(bilyNaTahu, gc);
    }
    
}
