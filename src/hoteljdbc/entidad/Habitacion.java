package hoteljdbc.entidad;

public class Habitacion {
    private int idHabitacion;
    private int piso;
    private int telefono;
    private int idCategoria;

    public Habitacion(int idHabitacion, int piso, int telefono, int idCategoria) {
        this.idHabitacion = idHabitacion;
        this.piso = piso;
        this.telefono = telefono;
        this.idCategoria = idCategoria;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }



    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
