package hoteljdbc.dto;

import java.util.Date;

public class CamaDTO {
    private String reciboUrl;
    private Date fechaCama;
    private int idCategoriaCama;

    public CamaDTO(String reciboUrl, Date fechaCama, int idCategoriaCama) {
        this.reciboUrl = reciboUrl;
        this.fechaCama = fechaCama;
        this.idCategoriaCama = idCategoriaCama;    }

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
}

