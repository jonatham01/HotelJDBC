
package hoteljdbc.dto;


import java.util.Date;

public class RecepcionDTO {
    private Date fecha;
    private int idHabitacion;
    private String Categoria;

    public RecepcionDTO(Date fecha, int idHabitacion, String categoria) {
        this.fecha = fecha;
        this.idHabitacion = idHabitacion;
        Categoria = categoria;
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

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
}
