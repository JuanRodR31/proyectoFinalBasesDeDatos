package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TipoSuscripcionController {

    private VistasController controladorVistas= new VistasController();


    public void menuUsuarioView(ActionEvent event) throws IOException {
        controladorVistas.menuUsuarioView(event);
    }
}
