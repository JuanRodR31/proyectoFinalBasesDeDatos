package Modelo;


public class Usuario implements PersonaBase {
    private String nombre;
    private String apellido;
    private String nickname;
    private String password;
    private String rol;
    private Pais pais;

    public Usuario(String nombre, String apellido, String nickname, String password, String rol, Pais pais) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname=nickname;
        this.password=password;
        this.rol=rol;
        this.pais=pais;
    }
    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Usuario verificarLogin(String username, String password){
        BaseDatos baseDatos=new BaseDatos();
       Usuario usuario=baseDatos.buscarUsuario(username,password);
       return usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                ", pais=" + pais +
                '}';
    }
}
