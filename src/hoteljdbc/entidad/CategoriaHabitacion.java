package hoteljdbc.entidad;

public class CategoriaHabitacion {

    private int idCategoriaHabitacion;
    private String nombre;
    private double precioNoche;
    private String fotoUrl;

    public CategoriaHabitacion(int idCategoriaHabitacion, String nombre, double precioNoche, String fotoUrl) {
        this.idCategoriaHabitacion = idCategoriaHabitacion;
        this.nombre = nombre;
        this.precioNoche = precioNoche;
        this.fotoUrl = fotoUrl;
    }

    public int getIdCategoriaHabitacion() {
        return idCategoriaHabitacion;
    }

    public void setIdCategoriaHabitacion(int idCategoriaHabitacion) {
        this.idCategoriaHabitacion = idCategoriaHabitacion;
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
