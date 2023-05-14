package model;

import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seguimiento.seguimiento14.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    public VBox Root;
    @FXML
    public TableColumn<Register, Date> dateColumn;
    @FXML
    public TableColumn<Register, Double> amountColumn;
    @FXML
    public TableColumn<Register, String> descriptionColumn;
    @FXML
    public TableColumn<Register, RegisterType> typeColumn;
    public Button viewExpenses;
    public Button viewExpensesButton;
    public Button viewIncomeButton;
    public Button viewAllRegistersButton;
    public Button updateBalanceButton;
    public Label balanceLabel;
    @FXML
    protected TableView<Register> tableView;
    @FXML
    protected Button addRegisterButton;

    FilteredList<Register> registersList = new FilteredList<>(RegistersList.getInstance().getRegisters());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("ammount"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableView.setItems(registersList);
    }


    public void addRegister(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addRegister.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Añadir registro");
        stage.setScene(scene);
        stage.show();
        Stage thisStage = (Stage) addRegisterButton.getScene().getWindow();
        thisStage.close();
    }


    public void viewExpenses(ActionEvent actionEvent) {
        registersList.setPredicate(Register -> Register.getType() == RegisterType.EXPENSE);
    }

    public void viewIncome(ActionEvent actionEvent) {
        registersList.setPredicate(Register -> Register.getType() == RegisterType.INCOME);
    }

    public void viewAllRegisters(ActionEvent actionEvent) {
        registersList.setPredicate(null);
    }

    public void updateBalance(ActionEvent actionEvent) {
        int balance = 0;
        for(Register register : registersList){
            if(register.getType() == RegisterType.INCOME){
                balance += register.getAmmount();
            } else {
                balance -= register.getAmmount();
            }
        }
        balanceLabel.setText(String.valueOf(balance));
    }
}