package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuAdministradorController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/proyectofinalbasesdedatos/LoginView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void agregarArtistaView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/proyectofinalbasesdedatos/agregarInterpreteView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void agregarAlbumView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/proyectofinalbasesdedatos/AgregarAlbumView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void mostrarDatosAuditoriaView (ActionEvent event) throws  IOException{
        root = FXMLLoader.load(getClass().getResource("com/example/proyectofinalbasesdedatos/MostrarModificacionesView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
