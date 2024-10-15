package hoteljdbc.entidad;

import java.util.Date;

public class Cama {
    private int idCama;
    private String reciboUrl;
    private Date fechaCama;
    private int idCategoriaCama;
    private String descripcionCama;

    public Cama(int idCama, String reciboUrl, Date fechaCama, int idCategoriaCama, String descripcionCama) {
        this.idCama = idCama;
        this.reciboUrl = reciboUrl;
        this.fechaCama = fechaCama;
        this.idCategoriaCama = idCategoriaCama;
        this.descripcionCama = descripcionCama;
    }

    public int getIdCama() {
        return idCama;
    }

    public void setIdCama(int idCama) {
        this.idCama = idCama;
    }

    public String getReciboUrl() {
        return reciboUrl;
    }

    public void setReciboUrl(String reciboUrl) {
        this.reciboUrl = reciboUrl;
    }

    public Date getFechaCama() {
        return fechaCama;
    }

    public void setFechaCama(Date fechaCama) {
        this.fechaCama = fechaCama;
    }

    public int getIdCategoriaCama() {
        return idCategoriaCama;
    }

    public void setIdCategoriaCama(int idCategoriaCama) {
        this.idCategoriaCama = idCategoriaCama;
    }

    public String getDescripcionCama() {
        return descripcionCama;
    }

    public void setDescripcionCama(String descripcionCama) {
        this.descripcionCama = descripcionCama;
    }


}
