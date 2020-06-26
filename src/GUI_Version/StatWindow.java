package GUI_Version;

import FunctionalStuff.AttributeNames;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class StatWindow implements Initializable {
    //Properties for change
    private BooleanProperty statsLock = new SimpleBooleanProperty(false);

    //Listeners
    ChangeListener<Boolean> boolListener = (observable, oldValue, newValue) -> {
        System.out.println("Value changed from " + oldValue + " to " + newValue + "!");
    };

    @FXML
    private Button rollStat, rollCustom, saveByCharacter;
    @FXML
    private ToggleButton statlock;
    @FXML
    private TextField characterName;
    @FXML
    private TextArea statQS, customQS;
    @FXML
    private ImageView avatar;
    @FXML
    private MenuItem menuNew;
    @FXML
    private Spinner<Integer> SpinnerMU, SpinnerKL, SpinnerIN, SpinnerCH, SpinnerFF, SpinnerGE, SpinnerKO, SpinnerKK;
    @FXML
    private ChoiceBox<AttributeNames> FirstRollStat, SecondRollStat, ThirdRollStat;
    @FXML
    private Rectangle healthBar, karmaBar, astralBar;

    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Object currentObject = event.getSource();
            if(currentObject.equals(rollStat))
            {

            }
            else if(currentObject.equals((rollCustom)))
            {

            }
            else if(currentObject.equals(saveByCharacter))
            {

            }
            else System.out.println("Object not found, make sure to add it to 'handleButtonAction'");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void handleToggleButtonAction(ActionEvent event)
    {
        try
        {
            Object currentObject = event.getSource();
            if(currentObject.equals(statlock))
            {

            }
            else System.out.println("Object not found, make sure to add it to 'handleToggleButtonAction'");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Button
        rollStat.setOnAction(this::handleButtonAction);
        rollCustom.setOnAction(this::handleButtonAction);
        saveByCharacter.setOnAction(this::handleButtonAction);

        //Toggle Button
        statsLock.removeListener(boolListener);
        statsLock.addListener(boolListener);
        statlock.selectedProperty().bindBidirectional(statsLock);

        //TextArea
        //statQS
        //customQS

        //MenuItem

        //ImageView
        //avatar...

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
}
