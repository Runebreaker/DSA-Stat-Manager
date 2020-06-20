package GUI_Version;

import FunctionalStuff.ErrorController;
import FunctionalStuff.Manager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI extends Application implements Initializable {
    //FunctionalStuff.Manager
    private Manager manager;
    private static GUI gui = null;
    private static final Object lock = new Object();

    //Properties for change
    private DoubleProperty health = new SimpleDoubleProperty(1.0);
    private DoubleProperty karma = new SimpleDoubleProperty(1.0);
    private DoubleProperty astral = new SimpleDoubleProperty(1.0);

    @FXML
    private
    Button create, save, load, delete;
    @FXML
    private
    MenuItem menuNew;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void setManager(Manager manager)
    {
        this.manager = manager;
    }

    public static GUI waitForGUI() throws InterruptedException {
        synchronized (lock)
        {
            if(gui == null)lock.wait(10000);
            return gui;
        }
    }

    public static void setGUI(GUI gui) {
        synchronized (lock)
        {
            GUI.gui = gui;
            lock.notify();
        }
    }

    private void handleButtonAction(ActionEvent event) {
        try{
            Object currentObject = event.getSource();
            if (currentObject.equals(create) || currentObject.equals(menuNew)) {
                manager.create("sample");
            } else if (currentObject.equals(save)) {
                manager.save();
            } else if (currentObject.equals(load)) {
                manager.load();
            } else if (currentObject.equals(delete)) {
                manager.delete("sample");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public GUI() {
        setGUI(this);
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

    @Override
    public void start(Stage primaryStage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(GUI::showError);

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

        for (Rectangle r : rectangles) {
            group.getChildren().add(r);
        }

        //Import FXML File for GUI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXMLFiles/FileScreen.fxml"));
        loader.setController(this);
        VBox saveroot = loader.load();

        Scene savescene = new Scene(saveroot, 800, 600);

        group.requestFocus();
        savescene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
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
        rectangles[1].setX(savescene.getWidth() / 2 - 100);
        rectangles[1].setY(100);
        rectangles[2].setX(savescene.getWidth() - 200);
        rectangles[2].setY(100);

        primaryStage.setTitle("DSA Stat FunctionalStuff.Manager by Loyal Raven Studios");
        savescene.setFill(Color.STEELBLUE);
        primaryStage.setScene(savescene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private static void showError(Thread t, Throwable e) {
        System.err.println("***Default exception handler***");
        if (Platform.isFxApplicationThread()) {
            showErrorDialog(e);
        } else {
            System.err.println("An unexpected error occurred in "+t);

        }
    }

    private static void showErrorDialog(Throwable e) {
        StringWriter errorMsg = new StringWriter();
        e.printStackTrace(new PrintWriter(errorMsg));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("../FXMLFiles/Error.fxml"));
        try {
            Parent root = loader.load();
            ((ErrorController)loader.getController()).setErrorText(errorMsg.toString());
            dialog.setScene(new Scene(root, 250, 400));
            dialog.show();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
