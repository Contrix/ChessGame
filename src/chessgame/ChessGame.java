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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Jirka
 */
public class ChessGame extends Application {
    Kresleni k = new Kresleni();
    
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
        Canvas canvas = new Canvas(800, 600);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
            //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());  ???

        /*gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        gc.fillText("hello   world!", 15, 50);

        gc.setLineWidth(5);
        gc.setStroke(Color.PURPLE);

        gc.strokeOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.strokeRect(30, 100, 40, 40);*/
        k.vykresliSachovnici(true, gc);
        
        
        
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
