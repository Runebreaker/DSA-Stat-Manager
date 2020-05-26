package GUI_Version;

import FunctionalStuff.DiceProp;
import FunctionalStuff.Functionality;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GUI extends Application {
    //Properties for change
    DoubleProperty health = new SimpleDoubleProperty(1.0);
    DoubleProperty karma = new SimpleDoubleProperty(1.0);
    DoubleProperty astral = new SimpleDoubleProperty(1.0);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Initial stuff
        Functionality functionality = new Functionality();
        ArrayList<DiceProp> diceProps = new ArrayList<>();

        //Format: mu, kl, in, ch, ff, ge, ko, kk
        Text[] texts = {
                new Text("MU"),
                new Text("KL"),
                new Text("IN"),
                new Text("CH"),
                new Text("FF"),
                new Text("GE"),
                new Text("KO"),
                new Text("KK")};

        TextField[] textFields = {
                new TextField("0"),
                new TextField("0"),
                new TextField("0"),
                new TextField("0"),
                new TextField("0"),
                new TextField("0"),
                new TextField("0"),
                new TextField("0")};

        //Format: Health, Karma, Astral
        Rectangle[] rectangles = new Rectangle[3];

        rectangles[0] = new Rectangle(200, 50, Color.RED);
        rectangles[1] = new Rectangle(200, 50, Color.ORANGE);
        rectangles[2] = new Rectangle(200, 50, Color.BLUE);

        DoubleBinding bhealth = health.multiply(rectangles[0].getWidth());
        DoubleBinding bkarma = karma.multiply(rectangles[1].getWidth());
        DoubleBinding bastral = astral.multiply(rectangles[2].getWidth());

        rectangles[0].widthProperty().bind(bhealth);
        rectangles[1].widthProperty().bind(bkarma);
        rectangles[2].widthProperty().bind(bastral);

        Group group = new Group();
        for(Text t : texts)
        {
            group.getChildren().add(t);
        }
        for(TextField textField : textFields)
        {
            group.getChildren().add(textField);
        }
        for(Rectangle r : rectangles)
        {
            group.getChildren().add(r);
        }

        Scene scene = new Scene(group, 800, 600);
        scene.setOnKeyTyped(event -> {
            switch (event.getCode()) {
                case LEFT:
                    health.set(health.get() - 0.1);
                    break;
                case RIGHT:
                    health.set(health.get() + 0.1);
            }
        });

            //Bar positions
        rectangles[0].setX(0);
        rectangles[0].setY(0);
        rectangles[1].setX(scene.getWidth()/2 - 100);
        rectangles[1].setY(0);
        rectangles[2].setX(scene.getWidth() - 200);
        rectangles[2].setY(0);

        primaryStage.setTitle("DSA Stat Manager by Loyal Raven Studios");
        scene.setFill(Color.STEELBLUE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
