package Modelo;

public class Idioma {
    private int id;
    private String nombreIdioma;

    public Idioma(int id, String nombreIdioma) {
        this.id = id;
        this.nombreIdioma=nombreIdioma;
    }
    public Idioma(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }
}
