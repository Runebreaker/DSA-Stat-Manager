package FunctionalStuff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable
{
    @FXML
    Button create;
    @FXML
    Button save;
    @FXML
    Button load;
    @FXML
    Button delete;

    private void handleButtonAction(ActionEvent event) {
        Object currentObject = event.getSource();
        if(currentObject.equals(create))
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
    }
}
