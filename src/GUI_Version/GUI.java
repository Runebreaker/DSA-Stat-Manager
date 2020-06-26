package GUI_Version;

import FunctionalStuff.AttributeNames;
import FunctionalStuff.ErrorController;
import FunctionalStuff.Manager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUI extends Application implements Initializable {
    //FunctionalStuff.Manager
    private Manager manager;
    private static GUI gui = null;
    private static final Object lock = new Object();

    //Properties for change
    private BooleanProperty statsLock = new SimpleBooleanProperty(false);

    private DoubleProperty health = new SimpleDoubleProperty(1.0);
    private DoubleProperty karma = new SimpleDoubleProperty(1.0);
    private DoubleProperty astral = new SimpleDoubleProperty(1.0);

    //Listeners
    ChangeListener<Boolean> boolListener = (observable, oldValue, newValue) -> {
        System.out.println("Value changed from " + oldValue + " to " + newValue + "!");
    };

    @FXML
    private Button create, save, load, delete, refresh, rollStat, rollCustom, saveByCharacter;
    @FXML
    private ToggleButton statlock;
    @FXML
    private TextField fileNameField;
    @FXML
    private TextArea statQS, customQS;
    @FXML
    private ImageView avatar;
    @FXML
    private MenuItem menuNew;
    @FXML
    private ListView<File> listView;
    @FXML
    private Spinner<Integer> SpinnerMU, SpinnerKL, SpinnerIN, SpinnerCH, SpinnerFF, SpinnerGE, SpinnerKO, SpinnerKK;
    @FXML
    private ChoiceBox<AttributeNames> FirstRollStat, SecondRollStat, ThirdRollStat;
    @FXML
    private Rectangle healthBar, karmaBar, astralBar;

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
                manager.configure();
                manager.createFile();
            } else if (currentObject.equals(save)) {
                manager.save();
            } else if (currentObject.equals(load)) {
                manager.load();
                showStatScreen();
            } else if (currentObject.equals(delete)) {
                manager.delete();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void showStatScreen() throws IOException {
        Stage statScreen = new Stage();
        statScreen.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(GUI.class.getResource("../FXMLFiles/StatScreen.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        statScreen.setScene(new Scene(root, 800, 600));
        statScreen.show();
    }

    public GUI() {
        setGUI(this);
    }

    public void updateListView(ObservableList<File> list)
    {
        listView.getItems().clear();
        listView.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Button
        create.setOnAction(this::handleButtonAction);
        save.setOnAction(this::handleButtonAction);
        load.setOnAction(this::handleButtonAction);
        delete.setOnAction(this::handleButtonAction);
        refresh.setOnAction(this::handleButtonAction);
        rollStat.setOnAction(this::handleButtonAction);
        rollCustom.setOnAction(this::handleButtonAction);
        saveByCharacter.setOnAction(this::handleButtonAction);

        //Toggle Button
        statsLock.removeListener(boolListener);
        statsLock.addListener(boolListener);
        try{
            statlock.selectedProperty().bindBidirectional(statsLock);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        //Textfield
        fileNameField.setOnAction(event -> {
            manager.changeName(fileNameField.getText());
        });
        manager.refresh();

        //TextArea
        //statQS
        //customQS

        //ListView
        listView.setOnMouseClicked(mouseEvent -> {
            ObservableList<File> temp = listView.getSelectionModel().getSelectedItems();
            if(!temp.isEmpty())
            {
                String currentName = listView.getSelectionModel().getSelectedItems().get(0).getName();
                String[] regex = currentName.split(".");
                if(regex.length > 1)currentName = regex[regex.length - 1];
                manager.changeName(currentName);
                fileNameField.setText(currentName);
                manager.configure();
            }
        });

        //ImageView
        //avatar...

        //MenuItem
        menuNew.setOnAction(this::handleButtonAction);

        //Spinner
        //SpinnerMU...
        //SpinnerKL...
        //SpinnerIN...
        //SpinnerCH...
        //SpinnerFF...
        //SpinnerGE...
        //SpinnerKO...
        //SpinnerKK...

        //ChoiceBox
        //FirstRollStat
        //SecondRollStat
        //ThirdRollStat

        //Rectangle
        //healthBar
        //karmaBar
        //astralBar
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
        //above is currently deprecated, using FXML

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

        primaryStage.setTitle("DSA Stat Manager by Loyal Raven Studios");
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
