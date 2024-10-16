
package hoteljdbc.dto;

public class CategoriaHabitacionDTO {
    private String nombre;
    private double precioNoche;
    private String fotoUrl;

    public CategoriaHabitacionDTO(String nombre, double precioNoche, String fotoUrl) {
        this.nombre = nombre;
        this.precioNoche = precioNoche;
        this.fotoUrl = fotoUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}
