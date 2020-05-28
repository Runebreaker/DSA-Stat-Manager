package GUI_Version;

import FunctionalStuff.Functionality;
import FunctionalStuff.SaveManager;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI extends Application implements Initializable {
    //Properties for change
    private DoubleProperty health = new SimpleDoubleProperty(1.0);
    private DoubleProperty karma = new SimpleDoubleProperty(1.0);
    private DoubleProperty astral = new SimpleDoubleProperty(1.0);

    private Functionality functionality;
    private SaveManager saveManager;

    @FXML
    private
    Button create, save, load, delete;
    @FXML
    private
    MenuItem menuNew;

    private void handleButtonAction(ActionEvent event) {
        Object currentObject = event.getSource();
        if(currentObject.equals(create) || currentObject.equals(menuNew))
        {
            System.out.println("NEW");
        }
        else if(currentObject.equals(save))
        {
            System.out.println("SAVE");
        }
        else if(currentObject.equals(load))
        {
            System.out.println("LOAD");
        }
        else if(currentObject.equals(delete))
        {
            System.out.println("DELETE");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create.setOnAction(this::handleButtonAction);
        save.setOnAction(this::handleButtonAction);
        load.setOnAction(this::handleButtonAction);
        delete.setOnAction(this::handleButtonAction);
        menuNew.setOnAction(this::handleButtonAction);
        menuNew.setOnAction(this::handleButtonAction);
    }

    public GUI() throws IOException {
        saveManager = new SaveManager();
        functionality = new Functionality();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
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

        //Import FXML File for GUI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FileScreen.fxml"));
        loader.setController(this);
        VBox saveroot = loader.load();

        Scene savescene = new Scene(saveroot, 800, 600);

        group.requestFocus();
        savescene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode())
            {
                case LEFT:
                    health.set(health.get() - 0.1);
                    break;
                case RIGHT:
                    health.set(health.get() + 0.1);
                    break;
            }
        });

        //Bar positions
        rectangles[0].setX(0);
        rectangles[0].setY(100);
        rectangles[1].setX(savescene.getWidth()/2 - 100);
        rectangles[1].setY(100);
        rectangles[2].setX(savescene.getWidth() - 200);
        rectangles[2].setY(100);

        primaryStage.setTitle("DSA Stat Manager by Loyal Raven Studios");
        savescene.setFill(Color.STEELBLUE);
        primaryStage.setScene(savescene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
