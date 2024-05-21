package Controladores;

import Modelo.Album;
import Modelo.BaseDatos;
import Modelo.EmpresaDiscografica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class AgregarAlbumController {
    public Button agregarAlbum;
    @FXML
    private ChoiceBox<String> empresaDiscograficaChoiceBox;
    @FXML
    private TextField nombreTextField;
    @FXML
    private DatePicker fechaTextField;
    @FXML
    private ChoiceBox<String> tipoChoiceBox;
    BaseDatos baseDatos= new BaseDatos();

    public void agregarAlbum(ActionEvent event) throws IOException {
        String nombreEmpresaDiscografica=empresaDiscograficaChoiceBox.getValue();
        String nombre= nombreTextField.getText();
        Date fecha = Date.valueOf(fechaTextField.getValue());
        String tipo=tipoChoiceBox.getValue();

        int idEmpresaDiscografica= buscarIdEmpresaDiscografica(nombreEmpresaDiscografica);
        EmpresaDiscografica empresaDiscografica= new EmpresaDiscografica(idEmpresaDiscografica, nombreEmpresaDiscografica);
        int idAlbum=baseDatos.obtenerProximoIdAlbum();
        Album album= new Album(idAlbum,nombre, fecha, empresaDiscografica, tipo);

        baseDatos.AgregarAlbumDB(album);
        agregarCancionView(event , album);
    }
    public void buscarTipos(MouseEvent event){
        ObservableList<String> tipos= FXCollections.observableArrayList();
        if (!tipoChoiceBox.getItems().contains("album") || !tipoChoiceBox.getItems().contains("ep")) {
            tipoChoiceBox.getItems().add("album");
            tipoChoiceBox.getItems().add("ep");
        }

    }
    public void buscarEmpresasDiscograficas(MouseEvent event){
        ObservableList<EmpresaDiscografica> empresas=baseDatos.buscarEmpresasDiscograficas();
        for(EmpresaDiscografica e:empresas){
            if (!empresaDiscograficaChoiceBox.getItems().contains(e.getNombre())) {
                empresaDiscograficaChoiceBox.getItems().add(e.getNombre());
            }
        }
    }

    public int buscarIdEmpresaDiscografica(String empresa){
        ObservableList<EmpresaDiscografica> empresas=baseDatos.buscarEmpresasDiscograficas();
        for(EmpresaDiscografica e:empresas){
            if(e.getNombre().equals(empresa)){
                return e.getId();
            }
        }
        return 0;
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
}
