package hoteljdbc.entidad;

public class Cliente {
    private int cedula;
    private String nombre;
    private String tipoIdentificacion;

    public Cliente(int cedula, String nombre, String tipoIdentificacion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
}
