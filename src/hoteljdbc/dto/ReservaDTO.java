
package hoteljdbc.dto;

import java.time.LocalDate;
import java.util.Date;

public class ReservaDTO {
    

    private Date fechaReserva;
    private String estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int idCliente;
    private long idFactura;

    public ReservaDTO( Date fechaReserva, String estado, LocalDate fechaInicio, LocalDate fechaFin, int idCliente, long idFactura) {
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idCliente = idCliente;
        this.idFactura = idFactura;
    }


    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(long idFactura) {
        this.idFactura = idFactura;
    }
    
}
