package Modelo;
import java.util.Date;

public class Album {
    private int idAlbum;
    private String titulo;
    private Date fechaLanzamiento;
    private EmpresaDiscografica empresaDiscografica;
    private String tipoAlbum;

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public java.sql.Date getFechaLanzamiento() {
        return (java.sql.Date) fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public EmpresaDiscografica getEmpresaDiscografica() {
        return empresaDiscografica;
    }

    public void setEmpresaDiscografica(EmpresaDiscografica empresaDiscografica) {
        this.empresaDiscografica = empresaDiscografica;
    }

    public String getTipoAlbum() {
        return tipoAlbum;
    }

    public void setTipoAlbum(String tipoAlbum) {
        this.tipoAlbum = tipoAlbum;
    }

    public Album(int idAlbum, String titulo, Date fechaLanzamiento, EmpresaDiscografica empresaDiscografica, String tipoAlbum) {
        this.idAlbum = idAlbum;
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.empresaDiscografica= empresaDiscografica;
        this.tipoAlbum = tipoAlbum;
    }

}