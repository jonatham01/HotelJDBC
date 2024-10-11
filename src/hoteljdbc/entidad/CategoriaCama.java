
package hoteljdbc.entidad;

public class CategoriaCama {
    
    private int idCategoriaCama;
    private String tipo;
    private String medidas;
    private String fotoUrl;
    private String color;

    public CategoriaCama(int idCategoriaCama, String tipo, String medidas, String fotoUrl, String color) {
        this.idCategoriaCama = idCategoriaCama;
        this.tipo = tipo;
        this.medidas = medidas;
        this.fotoUrl = fotoUrl;
        this.color = color;
    }

    public int getIdCategoriaCama() {
        return idCategoriaCama;
    }

    public void setIdCategoriaCama(int idCategoriaCama) {
        this.idCategoriaCama = idCategoriaCama;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
