package Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.security.MessageDigest;
import java.sql.*;

public class BaseDatos {
    public Usuario buscarUsuario(String username, String password){
        // password = encriptarConSHA256(password);
        //System.out.println("Contraseña encriptada: " + password);
        String sql= "SELECT nickname,contrasena, rol from Usuario where nickname = ? AND contrasena = ?";
        Usuario usuario=null;
        try{
            Connection conex = DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps= conex.prepareStatement(sql);
            //Se le asignan los valores a los parametros
            ps.setString(1,username);
            ps.setString(2, password);
            //Ejecutar consulta
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

                usuario= new Usuario() ;
                usuario.setNickname(rs.getString("nickname"));
                usuario.setPassword(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return usuario;
    }
    public static String encriptarConSHA256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Pais> buscarPaises(){
        ObservableList<Pais> paises = FXCollections.observableArrayList();
        try{
            String sql= "SELECT id,nombre FROM pais";
            Connection conex = DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps= conex.prepareStatement(sql);
            //Se le asignan los valores a los parametros

            //Ejecutar consulta
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Pais pais = new Pais();
                pais.setId(rs.getInt("id"));
                pais.setNombre(rs.getString("nombre"));
                paises.add(pais);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return paises;
    }

    public void registrarUsuarioDB(Usuario usuario){
        String sql= "INSERT INTO USUARIO(Pais_id, Nombre, Apellido, Nickname,rol, contrasena) values(?,?,?,?,?,?)";
        try(Connection conex = DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps= conex.prepareStatement(sql);) {
            ps.setInt(1, usuario.getPais().getId());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getNickname());
            ps.setString(5, usuario.getRol());
            ps.setString(6, usuario.getPassword());

            int rowsAffected = ps.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas

            if (rowsAffected > 0) {
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registro de usuario");
                alert.setHeaderText("Se registró exitosamente ");
                alert.setContentText("Ahora elija un tipo de suscripción");
                alert.show();
            } else {
                System.out.print("No se pudo registrar el usuario");
            }



        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void agregarArtistaDB(int codigoPais, String nombreReal, String nombreArtistico){
        String sql= "INSERT INTO interprete (Id, Nombre, NombreArtistico,  Pais_Id) values(cancion_seq.nextval,?,?,?)";
        try(Connection conex= DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps = conex.prepareStatement(sql);) {
            ps.setString(1, nombreReal);
            ps.setString(2, nombreArtistico);
            ps.setInt(3, codigoPais);

            int rowsAffected = ps.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas

            if (rowsAffected > 0) {
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("Se agregó el artista a la base de datos");
                alert.setContentText("Se volverá al menú principal");
                alert.show();
            } else {
                System.out.print("No se pudo registrar el artista");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AgregarAlbumDB(Album album) {
        String sql = "INSERT INTO Album (IdAlbum, Titulo, fechaLanzamiento, EmpresaDiscografica_Id, tipoAlbum) VALUES (?, ?, ?, ?, ?)";
        try (Connection conex = DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(sql)) {

            ps.setInt(1, album.getIdAlbum());
            ps.setString(2, album.getTitulo());
            ps.setDate(3, album.getFechaLanzamiento());
            ps.setInt(4, album.getEmpresaDiscografica().getId());
            ps.setString(5, album.getTipoAlbum());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Album agregado");
                alert.setHeaderText("Se agregó el album a la base de datos");
                alert.setContentText("Añada las canciones del album");
                alert.show();
            } else {
                System.out.print("No se pudo registrar el artista");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<EmpresaDiscografica> buscarEmpresasDiscograficas(){
        ObservableList<EmpresaDiscografica> empresas = FXCollections.observableArrayList();
        try{
            String sql= "SELECT id,nombre FROM empresaDiscografica";
            Connection conex = DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps= conex.prepareStatement(sql);
            //Se le asignan los valores a los parametros

            //Ejecutar consulta
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                EmpresaDiscografica empresaDiscografica = new EmpresaDiscografica();
                empresaDiscografica.setId(rs.getInt("id"));
                empresaDiscografica.setNombre(rs.getString("nombre"));
                empresas.add(empresaDiscografica);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return empresas;
    }

    public ObservableList<Interprete> buscarInterpretes(){
        ObservableList<Interprete> interpretes = FXCollections.observableArrayList();
        try{
            String sql= "SELECT Id, Nombre, NombreArtistico,  Pais_Id FROM interprete";
            Connection conex = DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps= conex.prepareStatement(sql);
            //Se le asignan los valores a los parametros

            //Ejecutar consulta
            if(ps!=null){
                ResultSet rs = ps.executeQuery();

                    while(rs.next()){
                        Interprete interprete=new Interprete();
                        interprete.setId((rs.getInt("id")));
                        interprete.setNombre(rs.getString("nombre"));
                        interprete.setNombreArtistico(rs.getString("nombreArtistico"));
                        Pais pais = new Pais();
                        pais.setId(rs.getInt("Pais_id"));
                        interprete.setPais(pais);
                        interpretes.add(interprete);
                    }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return interpretes;
    }


    public void agregarCancion(int id, String duracion, int genero_id, int idPrincipal){
        String sql= "INSERT INTO cancion (Id, duracion, genero_id,  idInterpretePrincipal) values(?,?,?,?)";
        try(Connection conex= DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps = conex.prepareStatement(sql);) {
            ps.setInt(1, id);
            ps.setString(2, duracion);
            ps.setInt(3, genero_id);
            ps.setInt(4, idPrincipal);

            int rowsAffected = ps.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas

            if (rowsAffected > 0) {
                System.out.println("Cancion exito");
                /*
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("Se agregó La canción a la base de datos");
                //alert.setContentText("Se volverá al menú principal");
                alert.show();*/
            } else {
                System.out.print("No se pudo registrar el artista");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void agregarInterpretesXCancion(int cancion_id, int interprete_id){
        String sql= "INSERT INTO InterpretesXCancion (cancion_id, interprete_id) values(?,?)";
        try(Connection conex= DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps = conex.prepareStatement(sql);) {
            ps.setInt(1, cancion_id);
            ps.setInt(2, interprete_id);
            int rowsAffected = ps.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas
            if (rowsAffected > 0) {
                System.out.println("Interpretes exito");
                /*
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("Se agregaron los artistas a la canción de la base de datos");
                //alert.setContentText("Se volverá al menú principal");
                alert.show();

                 */
            } else {
                System.out.print("No se pudo registrar el artista");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void agregarIdiomasXCancion(int idioma_id, int cancion_id, String nombreCancion){
        String sql= "INSERT INTO IdiomasXCancion (idioma_id, cancion_id, nombreCancion) values(?,?,?)";
        try(Connection conex= DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps = conex.prepareStatement(sql);) {
            ps.setInt(1, idioma_id);
            ps.setInt(2, cancion_id);
            ps.setString(3,nombreCancion);
            int rowsAffected = ps.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas
            if (rowsAffected > 0) {
                System.out.println("Idomas exito");
                /*
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("Se agrego un nuevo titulo a la cancion");
                //alert.setContentText("Se volverá al menú principal");
                alert.show();*/
            } else {
                System.out.print("No se pudo registrar el artista");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void agregarCancionesXAlbum(int cancion_id, int idAlbum){
        String sql= "INSERT INTO CancionesXAlbum (cancion_id, idAlbum) values(?,?)";
        try(Connection conex= DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps = conex.prepareStatement(sql);) {
            ps.setInt(1, cancion_id);
            ps.setInt(2,idAlbum);
            int rowsAffected = ps.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas
            if (rowsAffected > 0) {
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("Se agrego La cancion al album");
                alert.setContentText("Todas las tablas fueron actualizadas");
                alert.show();
            } else {
                System.out.print("No se pudo registrar el artista");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void registrarSuscripcionDB (Usuario usuario, int idSuscripcion){
        String sql= "INSERT INTO SuscripcionXUsuario (Usuario_Nickname, Suscripcion_id) values(?,?)" ;
        try(Connection conex = DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps= conex.prepareStatement(sql);) {
            ps.setString(1, usuario.getNickname());
            ps.setInt(2, idSuscripcion);
            try{
                ps.execute();
                System.out.print("Se registro la suscripcion");
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ObservableList<Genero> buscarGeneros(){

        ObservableList<Genero> generos = FXCollections.observableArrayList();
        try{
            String sql= "SELECT id, nombre from genero ";
            Connection conex = DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps= conex.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Genero genero1=new Genero();
                genero1.setId((rs.getInt("id")));
                genero1.setNombre(rs.getString("nombre"));

                generos.add(genero1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return generos;
    }

    public ObservableList<Idioma> buscarIdiomas() {

        ObservableList<Idioma> idiomas = FXCollections.observableArrayList();
        try {
            String sql = "SELECT id, nombreIdioma from idioma ";
            Connection conex = DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps = conex.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Idioma idioma = new Idioma();
                idioma.setId((rs.getInt("id")));
                idioma.setNombreIdioma(rs.getString("nombreIdioma"));

                idiomas.add(idioma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idiomas;
    }

    public int obtenerProximoIdCancion() {
        int idCancion = -1;
        String sql = "SELECT cancion_seq.NEXTVAL FROM dual";
        try (Connection conex= DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                idCancion = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idCancion;
    }
    public int obtenerProximoIdAlbum() {
        int idAlbum = -1;
        String sql = "SELECT album_seq.NEXTVAL FROM dual";
        try (Connection conex= DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                idAlbum = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAlbum;
    }
    /*
    public ObservableList<Cancion> buscarCanciones(String cancion){
        ObservableList<Cancion> canciones = FXCollections.observableArrayList();
        try{
            String sql= "SELECT c.titulo , a.titulo, i.nombreartistico" +
                    "FROM cancionesxalbum ca\n" +
                    "JOIN cancion c ON ca.cancion_id = c.id\n" +
                    "JOIN idiomasxcancion idc ON c.id = idc.cancion_id\n" +
                    "JOIN album a ON ca.album_idalbum = a.idalbum\n" +
                    "LEFT JOIN interpretesxcancion ic ON c.id = ic.cancion_id\n" +
                    "LEFT JOIN interprete i ON ic.interprete_id = i.id\n" +
                    "WHERE c.titulo = '?'";

            Connection conex = DriverManager.getConnection(ConnectionDB.THINCONN, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps= conex.prepareStatement(sql);
            //Se le asignan los valores a los parametros

            //Ejecutar consulta
            if(ps!=null){
                ResultSet rs = ps.executeQuery();

                while(rs.next()){

                    Cancion cancion= new Cancion();
                    cancion.setNombre((rs.getString("c.titulo")));
                    cancion.setAlbum((rs.getString("a.titulo")));
                    cancion.setInterpretePrincipal((rs.getString("i.nombreartistico")));

                    canciones.add(cancion);
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return canciones;
    }
    */




}
