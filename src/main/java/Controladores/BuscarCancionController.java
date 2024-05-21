package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


import Modelo.BaseDatos;
import javafx.scene.control.TextField;


public class BuscarCancionController {
    @FXML
    private TextField nombreCancionTextField;

    private void buscarCancion(ActionEvent event) {
        String nombreCancion=nombreCancionTextField.getText();

    }


}
