package hoteljdbc.entidad;

import java.util.Date;

public class RecepcionHabitacion {
    private int codigoRecepcionHabitacion;
    private Date fecha;
    private int idHabitacion;
    private CategoriaRecepcion Categoria;

    public RecepcionHabitacion(int codigoRecepcionHabitacion, Date fecha, int idHabitacion, CategoriaRecepcion categoria) {
        this.codigoRecepcionHabitacion = codigoRecepcionHabitacion;
        this.fecha = fecha;
        this.idHabitacion = idHabitacion;
        Categoria = categoria;
    }

    public int getCodigoRecepcionHabitacion() {
        return codigoRecepcionHabitacion;
    }

    public void setCodigoRecepcionHabitacion(int codigoRecepcionHabitacion) {
        this.codigoRecepcionHabitacion = codigoRecepcionHabitacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public CategoriaRecepcion getCategoria() {
        return Categoria;
    }

    public void setCategoria(CategoriaRecepcion categoria) {
        Categoria = categoria;
    }
}
