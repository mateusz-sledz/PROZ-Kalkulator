package calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import calc.model.Model;

import java.net.URL;
import java.util.ResourceBundle;


/**Klasa Controller obsługuje zdarzenia*/

public class Controller implements Initializable {


    Model calc;

    @FXML
    private Button multiply;
    @FXML
    private Button divide;
    @FXML
    private Button plus;
    @FXML
    private Button minus;
    @FXML
    private Button factorion;
    @FXML
    private Button dot;
    @FXML
    private Button erase;
    @FXML
    private Button equals;
    @FXML
    TextArea result_out;
    private void error_alert(String text) {
        Alert alert = new Alert(AlertType.WARNING);

        alert.setTitle("ERROR");
        alert.setContentText("Click OK to continue");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	
        calc = new Model();			/** Tworzymy nowy moduł obliczeń */

        Button[] operations = {multiply, divide, dot};
        
        /** Przy pomocy wyrażeń lambda definiujemy co ma się dziać po naciśnięciu 
         * przycisków operacji*/
        
        for (Button i : operations) {
            i.setOnAction(e -> {
                result_out.setText(result_out.getText() + i.getText());
            });
        }

        operations = null;
        operations = new Button[] {plus, minus};

        for(Button i : operations){
            i.setOnAction(e-> {
                if(result_out.getText().equals("0")){
                    result_out.setText("");
                }
                result_out.setText(result_out.getText() + i.getText());
            });
        }

        erase.setOnAction(e -> {
            result_out.setText("0");
        });

        equals.setOnAction(e -> {
            EqualsButton();
        });

        factorion.setOnAction(e -> {
            try {
                result_out.setText(calc.factorion(result_out.getText()));
            }
            catch(Model.NumberFormatException e1){
                error_alert(e1.getMessage());
                result_out.setText("0");
            }
            catch(Exception e2){
                error_alert(e2.toString() + "\nZły format silni" );
                result_out.setText("0");
            }
        });
    }

    /** Metoda wywoływana po naciśnięciu przycisków numerycznych */
    
    public void numericButton(ActionEvent event) {
        if (result_out.getText().equals("0"))
            result_out.setText(((Button)event.getSource()).getText());
        else
            result_out.setText(result_out.getText() + ((Button) event.getSource()).getText());
    }

    /**Obsługuje przycisk "=" */
    @FXML
    public void EqualsButton() {
        String result;
        if (!result_out.getText().contains(".") && result_out.getText().contains("/"))
            result_out.setText(result_out.getText() + ".0");
        try {
            result = calc.calculate(result_out.getText());
        } catch (Exception e) {
            error_alert(e.getMessage());
            result = "0";
        }

        if (result.length() >= 3) {
            if ((result.indexOf(".0") == (result.length() - 2))) {
                result = result.substring(0, result.length() - 2);
            }
        }

        result_out.setText(result);
    }
}