package Controladores;

import Modelo.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AgregarCancionController {
    @FXML
    private ChoiceBox<String> principalChoiceBox;
    @FXML
    private ChoiceBox<String> secundariosChoiceBox;
    @FXML
    private ChoiceBox<String> idiomaTituloChoiceBox;
    @FXML
    private TextField tituloTextField;
    @FXML
    private ChoiceBox<String> generoChoiceBox;
    @FXML
    private TextField duracionTextField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    BaseDatos baseDatos= new BaseDatos();
    private Album albumActual;
    VistasController controladorVistas=new VistasController();


    public void agregarCancion(ActionEvent event) throws IOException {
        String principal=principalChoiceBox.getValue();
        String secundario= secundariosChoiceBox.getValue();
        String genero= generoChoiceBox.getValue();
        String titulo= tituloTextField.getText();
        String duracion= duracionTextField.getText();
        String idioma= idiomaTituloChoiceBox.getValue();
        int idCancion = baseDatos.obtenerProximoIdCancion();


        if (principal != null && secundario != null && genero != null && idioma != null) {
            //System.out.println("Para cancion:" +idCancion+"  "+ duracion+"  "+ buscarIdGenero(genero)+"   "+buscarIdInterprete(principal));
            baseDatos.agregarCancion(idCancion, duracion, buscarIdGenero(genero), buscarIdInterprete(principal));
            //System.out.println("Para IdiomasXCancion:" +buscarIdIdioma(idioma)+"  "+ idCancion+"  "+ titulo);

            baseDatos.agregarIdiomasXCancion(buscarIdIdioma(idioma), idCancion, titulo);
            baseDatos.agregarInterpretesXCancion(idCancion, buscarIdInterprete(principal));
            baseDatos.agregarInterpretesXCancion(idCancion, buscarIdInterprete(secundario));
            baseDatos.agregarCancionesXAlbum(idCancion, albumActual.getIdAlbum());
        } else {
            // Manejar el caso en el que no se hayan seleccionado todos los valores
            System.out.println("Por favor, seleccione todos los valores.");
        }

        controladorVistas.agregarCancionView( event, albumActual);
    }

    public void buscarInterpretes(MouseEvent event){
        ObservableList<Interprete> interpretes=baseDatos.buscarInterpretes();
            for(Interprete i:interpretes){
                if (!principalChoiceBox.getItems().contains(i.getNombreArtistico())) {
                    principalChoiceBox.getItems().add(i.getNombreArtistico());
                }
                if (!secundariosChoiceBox.getItems().contains(i.getNombreArtistico())) {
                    secundariosChoiceBox.getItems().add(i.getNombreArtistico());
                }
            }
    }
    public void buscarGenero(MouseEvent event){
        ObservableList<Genero> generos=baseDatos.buscarGeneros();
        for(Genero g : generos){
            if (!generoChoiceBox.getItems().contains(g.getNombre())) {
                generoChoiceBox.getItems().add(g.getNombre());
            }
        }

    }

    public int buscarIdGenero(String genero){
        ObservableList<Genero> generos=baseDatos.buscarGeneros();
        for(Genero g: generos){
            if(g.getNombre().equals(genero)){
                return g.getId();
            }
        }
        return 0;
    }
    public void buscarIdiomas(MouseEvent event){
        ObservableList<Idioma> idiomas=baseDatos.buscarIdiomas();
        for(Idioma i:idiomas){
            if (!idiomaTituloChoiceBox.getItems().contains(i.getNombreIdioma())) {
                idiomaTituloChoiceBox.getItems().add(i.getNombreIdioma());
            }
        }
    }
    public int buscarIdIdioma(String nombre){
        ObservableList<Idioma> idiomas=baseDatos.buscarIdiomas();
        for(Idioma i: idiomas){
            if(i.getNombreIdioma().equals(nombre)){
                return i.getId();
            }
        }
        return 0;
    }


    public void menuAdministradorView(ActionEvent event) throws IOException {
        controladorVistas.menuAdministradorView(event);
    }
    public void setAlbumActual(Album album) {
        this.albumActual = album;
    }
    public int buscarIdInterprete(String nombre){
        ObservableList<Interprete> interpretes=baseDatos.buscarInterpretes();
        System.out.println("DESDE ACA");
        for(Interprete i: interpretes){
            System.out.println(i);
            if(i.getNombreArtistico().equals(nombre)){
                return i.getId();
            }
        }
        return 0;
    }

}
