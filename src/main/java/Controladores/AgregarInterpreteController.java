package Controladores;

import Modelo.BaseDatos;
import Modelo.Pais;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AgregarInterpreteController {
    private VistasController controladorVistas= new VistasController();
    @FXML
    private ChoiceBox<String> paisChoiceBox;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField nombreArtisticoTextField;
    @FXML
    private TextField idTextField;
    BaseDatos baseDatos= new BaseDatos();

    public void agregarArtista(ActionEvent event) throws IOException {
        String nombre=nombreTextField.getText();
        String nombreArtistico=nombreArtisticoTextField.getText();
        String nombrePais= paisChoiceBox.getValue();
        int idPais= buscarIdPais(nombrePais);
        baseDatos.agregarArtistaDB(idPais,nombre, nombreArtistico);
        controladorVistas.menuAdministradorView(event);

    }
    public void buscarPaises(MouseEvent event){
        ObservableList<Pais> paises=baseDatos.buscarPaises();
        for(Pais p:paises){
            if (!paisChoiceBox.getItems().contains(p.getNombre())) {
                paisChoiceBox.getItems().add(p.getNombre());
            }
        }
    }

    public int buscarIdPais(String pais){
        ObservableList<Pais> paises=baseDatos.buscarPaises();
        for(Pais p:paises){
            if(p.getNombre().equals(pais)){
                return p.getId();
            }
        }
        return 0;
    }
    public void menuAdministradorView(ActionEvent event) throws IOException {
        controladorVistas.menuAdministradorView(event);
    }
}
