package Controladores;

import Modelo.Album;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VistasController {

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
    public void registroView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/proyectofinalbasesdedatos/RegistroView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void agregarInterpreteView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/proyectofinalbasesdedatos/agregarInterpreteView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void buscarCancionView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/proyectofinalbasesdedatos/buscarCancionVista.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void menuAdministradorView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/proyectofinalbasesdedatos/MenuAdministradorView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void menuUsuarioView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/proyectofinalbasesdedatos/MenuUsuarioView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void tipoSuscripcionView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/proyectofinalbasesdedatos/TipoSuscripcionView.fxml")));
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

    public void agregarCancionView(ActionEvent event, Album album) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinalbasesdedatos/AgregarCancionView.fxml"));
        Parent root = loader.load();

        // Obtén el controlador y pasa el objeto Album
        AgregarCancionController agregarCancionController = loader.getController();
        agregarCancionController.setAlbumActual(album);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void mostrarDatosAuditoriaView (ActionEvent event) throws  IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("com/example/proyectofinalbasesdedatos/MostrarModificacionesView.fxml"));
        Parent root = loader.load();


        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
