package hoteljdbc.entidad;

public class CategoriaCamaHabitacion {
    private int codigoCategoriaCama;
    private int codigoCategoriaHabitacion;
    private short cantidad;

    public CategoriaCamaHabitacion(int codigoCama, int codigoHabitacion, short cantidad) {
        this.codigoCategoriaCama = codigoCama;
        this.codigoCategoriaHabitacion = codigoHabitacion;
        this.cantidad = cantidad;
    }

    public int getCodigoCategoriaCama() {
        return codigoCategoriaCama;
    }

    public void setCodigoCategoriaCama(int codigoCategoriaCama) {
        this.codigoCategoriaCama = codigoCategoriaCama;
    }

    public int getCodigoCategoriaHabitacion() {
        return codigoCategoriaHabitacion;
    }

    public void setCodigoCategoriaHabitacion(int codigoCategoriaHabitacion) {
        this.codigoCategoriaHabitacion = codigoCategoriaHabitacion;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }
    
}
