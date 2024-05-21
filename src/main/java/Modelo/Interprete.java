package Modelo;

public class Interprete {
    private int id;
    private String nombre;
    private String nombreArtistico;
    private Pais pais;

    public Interprete(int id, String nombre, String nombreArtistico, Pais pais) {
        this.id = id;
        this.nombre=nombre;
        this.nombreArtistico=nombreArtistico;
        this.pais=pais;
    }

    public Interprete() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Interprete{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nombreArtistico='" + nombreArtistico + '\'' +
                ", pais=" + pais +
                '}';
    }
}
