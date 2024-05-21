package Controladores;

import Modelo.BaseDatos;
import Modelo.Pais;
import Modelo.Usuario;
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

public class RegistroController {
    private VistasController controladorVistas= new VistasController();
    BaseDatos baseDatos=new BaseDatos();
    @FXML
    private ChoiceBox<String> paisChoiceBox;
    @FXML
    private TextField nombresTextField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private TextField nicknameTextField;
    @FXML
    private TextField passwordTextField;


    public void loginView(ActionEvent event) throws IOException{
        controladorVistas.loginView(event);
    }
    public void registrarUsuario(ActionEvent event) throws IOException {
        String nombres=nombresTextField.getText();
        String apellidos=apellidosTextField.getText();
        String nickname=nicknameTextField.getText();
        String password=passwordTextField.getText();
        String nombrePais= paisChoiceBox.getValue();
        int idPais= buscarIdPais(nombrePais);
        Pais pais= new Pais(nombrePais, idPais);
        Usuario usuario= new Usuario(nombres, apellidos, nickname, password, "user", pais);
        baseDatos.registrarUsuarioDB(usuario);

        TipoSuscripcionView( event);


    }
    public void TipoSuscripcionView(ActionEvent event) throws IOException {
        controladorVistas.tipoSuscripcionView(event);
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
}
