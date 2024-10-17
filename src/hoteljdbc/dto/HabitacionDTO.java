package hoteljdbc.dto;

public class HabitacionDTO {

    private int piso;
    private int telefono;
    private int idCategoria;

    public HabitacionDTO( int piso, int telefono, int idCategoria) {
        this.piso = piso;
        this.telefono = telefono;
        this.idCategoria = idCategoria;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }



    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
