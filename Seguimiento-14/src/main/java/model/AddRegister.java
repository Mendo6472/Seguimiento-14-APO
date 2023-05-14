package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seguimiento.seguimiento14.HelloApplication;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class AddRegister implements Initializable {

    public VBox RegisterRoot;
    public TextField ammountInput;
    public TextField descriptionInput;
    public TextField dateInput;
    public Button addRegisterButton;
    public ComboBox<String> registerTypeComboBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> typeOptions = FXCollections.observableArrayList("Ingreso", "Gasto");
        registerTypeComboBox.setItems(typeOptions);
    }

    public void registerButton(ActionEvent actionEvent) {
        try{
            RegisterType registerType;
            String type = registerTypeComboBox.getValue();
            if(type == null) throw new Exception("Debes seleccionar un tipo de registro");
            if(type.equals("Ingreso")){
                registerType = RegisterType.INCOME;
            } else {
                registerType = RegisterType.EXPENSE;
            }
            double amount;
            try{
                amount = Double.parseDouble(ammountInput.getText());
            } catch (NumberFormatException e){
                throw new Exception("Debes poner un monto adecuado");
            }
            Date date;
            try{
                date = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(dateInput.getText());
            } catch (ParseException e){
                throw new Exception("Debes poner una fecha adecuada siguiendo el formato dd-MM-yyyy hh:mm:ss");
            }
            String description = descriptionInput.getText();
            Register register = new Register(amount, description, registerType, date);
            RegistersList.getInstance().getRegisters().add(register);
            RegistersList.getInstance().getRegisters().sort(Register :: compareTo);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tableView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Añadir registro");
            stage.setScene(scene);
            stage.show();
            Stage thisStage = (Stage) addRegisterButton.getScene().getWindow();
            thisStage.close();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Vuelve a intentarlo");
            alert.showAndWait();
        }
    }
}
