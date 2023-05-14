package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RegistersList {

    private static RegistersList instance = null;

    private ObservableList<Register> registers = FXCollections.observableArrayList();

    public ObservableList<Register> getRegisters() {
        return registers;
    }

    private RegistersList(){}

    public static RegistersList getInstance(){
        if(instance == null){
            instance = new RegistersList();
        }
        return instance;
    }
}
