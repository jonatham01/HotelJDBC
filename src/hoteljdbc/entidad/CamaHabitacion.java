package hoteljdbc.entidad;

public class CamaHabitacion {
    private int idCama;
    private int idHabitacion;
    private String estado;

    public CamaHabitacion(int idCama, int idHabitacion, String estado) {
        this.idCama = idCama;
        this.idHabitacion = idHabitacion;
        this.estado = estado;
    }

    public int getIdCama() {
        return idCama;
    }

    public void setIdCama(int idCama) {
        this.idCama = idCama;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
