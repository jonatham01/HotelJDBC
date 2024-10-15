package hoteljdbc.entidad;

import java.util.Date;

public class ReservaHuesped {
    private double idReserva;
    private int idHuespe;
    private Date fechaReserva;

    public ReservaHuesped(double idReserva, int idHuespe, Date fechaReserva) {
        this.idReserva = idReserva;
        this.idHuespe = idHuespe;
        this.fechaReserva = fechaReserva;
    }

    public double getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(double idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHuespe() {
        return idHuespe;
    }

    public void setIdHuespe(int idHuespe) {
        this.idHuespe = idHuespe;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
