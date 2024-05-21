package Controladores;

import Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    private VistasController controladorVistas= new VistasController();

    @FXML
    public void registroView(ActionEvent event)throws IOException{
        controladorVistas.registroView(event);
    }
    public void verificarLogin(ActionEvent event) throws IOException {
        Usuario u= new Usuario();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        u=u.verificarLogin(username, password);
        if(u!=null){
            if(u.getNickname().equals(username) && u.getPassword().equals(password)){

                if(u.getRol().equals("user")){
                    menuUsuarioView(event);
                }else if(u.getRol().equals("admin")){
                    menuAdministradorView(event);
                }
            }
        }else{
            System.out.println("F bro, no estas registado");
        }
    }
    public void menuUsuarioView(ActionEvent event) throws IOException {
        controladorVistas.menuUsuarioView(event);
    }
    public void menuAdministradorView(ActionEvent event) throws IOException {
        controladorVistas.menuAdministradorView(event);
    }


}
