package hoteljdbc.entidad;

public class CategoriaCamaHabitacion {
    private int codigoCama;
    private int codigoHabitacion;
    private short cantidad;

    public CategoriaCamaHabitacion(int codigoCama, int codigoHabitacion, short cantidad) {
        this.codigoCama = codigoCama;
        this.codigoHabitacion = codigoHabitacion;
        this.cantidad = cantidad;
    }

    public int getCodigoCama() {
        return codigoCama;
    }

    public void setCodigoCama(int codigoCama) {
        this.codigoCama = codigoCama;
    }

    public int getCodigoHabitacion() {
        return codigoHabitacion;
    }

    public void setCodigoHabitacion(int codigoHabitacion) {
        this.codigoHabitacion = codigoHabitacion;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }
}
