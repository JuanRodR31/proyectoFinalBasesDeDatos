module com.example.proyectofinalbasesdedatos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.proyectofinalbasesdedatos to javafx.fxml;
    opens Modelo to javafx.fxml;
    exports com.example.proyectofinalbasesdedatos;
    exports Modelo;
    exports Controladores;
    opens Controladores to javafx.fxml;

}