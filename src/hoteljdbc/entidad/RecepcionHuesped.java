package hoteljdbc.entidad;

import java.util.Date;

public class RecepcionHuesped{
    private int idRecepcion;
    private int idHuesped;
    private Date fechaRecepcion;

    public RecepcionHuesped(int idRecepcion, int idHuesped, Date fechaRecepcion) {
        this.idRecepcion = idRecepcion;
        this.idHuesped = idHuesped;
        this.fechaRecepcion = fechaRecepcion;
    }

    public int getIdRecepcion() {
        return idRecepcion;
    }

    public void setIdRecepcion(int idRecepcion) {
        this.idRecepcion = idRecepcion;
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
}
