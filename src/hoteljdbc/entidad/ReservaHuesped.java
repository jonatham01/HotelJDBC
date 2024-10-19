package hoteljdbc.entidad;

import java.util.Date;

public class ReservaHuesped {
    private long idReserva;
    private int idHuesped;
    private Date fechaReserva;

    public ReservaHuesped(long idReserva, int idHuesped, Date fechaReserva) {
        this.idReserva = idReserva;
        this.idHuesped = idHuesped;
        this.fechaReserva = fechaReserva;
    }

    public long  getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
