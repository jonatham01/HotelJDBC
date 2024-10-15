package hoteljdbc.entidad;

public class ReservaHabitacion {
    private long idReserva;
    private int idHabitacion;
    private short cantidad;

    public ReservaHabitacion(long idReserva, int idHabitacion, short cantidad) {
        this.idReserva = idReserva;
        this.idHabitacion = idHabitacion;
        this.cantidad = cantidad;
    }

    public double getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }
}
