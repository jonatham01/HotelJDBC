
package hoteljdbc.dto;

import java.util.Date;

public class FacturaDTO {
    
    private double total;
    private double subtotal;
    private double iva;
    private double inc;
    private double descuento;
    private String categoria;
    private Date fechaHora;

    public FacturaDTO( double total, double subtotal, double iva, double inc, double descuento, String categoria, Date fechaHora) {
        this.total = total;
        this.subtotal = subtotal;
        this.iva = iva;
        this.inc = inc;
        this.descuento = descuento;
        this.categoria = categoria;
        this.fechaHora = fechaHora;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getInc() {
        return inc;
    }

    public void setInc(double inc) {
        this.inc = inc;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
}
