package chessgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Jirka
 */
public class ChessGame extends Application {
    Kresleni k = new Kresleni();
    boolean bilyNaTahu = true; 
    boolean oznacenaFigurka = false;
    
    @Override
    public void start(Stage primaryStage) {
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 250);
        */
        
        //Okno aplikace
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Chess game");        
        
        //Vykreslovací plocha
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        final GraphicsContext gc = canvas.getGraphicsContext2D();
            //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());  ???

        gc.setFill(Color.BLANCHEDALMOND);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        k.vykresliSachovnici(true, gc);
        
        Figurka bv1 = new Vez(true,3,3);
        
        bv1.vykresli(gc);
        
        
        
        k.vypisHraceNaTahu(bilyNaTahu, gc);
        
        /*
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
        new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent e) 
            {
                //gc.clearRect(e.getX() - 2, e.getY() - 2, 5, 5);
            }
       });*/
        
        
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
    
}
