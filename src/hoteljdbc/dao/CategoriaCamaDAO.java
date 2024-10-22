
package hoteljdbc.dao;

import hoteljdbc.Conexion;
import hoteljdbc.entidad.CategoriaCama;
import hoteljdbc.servicio.CategoriaCamaServicio;



public class CategoriaCamaDAO {
    
    private CategoriaCama mensaje = null;
    String mensajeEliminacion;
    private CategoriaCamaServicio servicio;
    private Conexion conexion;
    private CategoriaCama categoria;
    

    public CategoriaCamaDAO() {
        this.conexion = new Conexion();
        this.servicio = new CategoriaCamaServicio();
    }
    
    public CategoriaCama crear(CategoriaCama entidad){
        try{
            mensaje = servicio.crearCategoriaCama(conexion.conectar(),entidad);
            conexion.desconectar();
            return mensaje;
        }catch(Exception e){
            return null;
        }
        
    }
    
     public CategoriaCama mostrar(int id){
        try{
            categoria = servicio.mostrarCategoriaCama(conexion.conectar(), id);
            conexion.desconectar();
            return categoria;
        }catch(Exception e){
            return null;
        }
        
    }
     
    public CategoriaCama modificar(CategoriaCama entidad, int id){
        try{
            mensaje = servicio.modificarCategoriaCama(conexion.conectar(),entidad, id);
            conexion.desconectar();
            return mensaje;
        }catch(Exception e){
            return null;
        }
        
    }
    
    public String borrar(int idCategoria){
        try{
            mensajeEliminacion = servicio.eliminarCategoriaCama(conexion.conectar(),idCategoria);
            conexion.desconectar();
            return mensajeEliminacion;
        }catch(Exception e){
            return null;
        }
        
    }
     
     
    public static void main(String[] args) {
        CategoriaCama categoria1 = new CategoriaCama(
                1,
                "Doble",
                "120 x 190",
                "url del hotel",
                "blanco"
        );
        CategoriaCama categoria2 = new CategoriaCama(
                1,
                "Sencilla",
                "120 x 190",
                "url del hotel",
                "Negro"
        );
        CategoriaCamaDAO cat = new CategoriaCamaDAO();
        CategoriaCama categoria = cat.crear(categoria1);
        System.out.println("Se creo la siguiente categoria de cama: ");
        System.out.println("TIPO: " + categoria.getTipo() );  
        System.out.println("COLOR: " + categoria.getColor() );
        System.out.println("MEDIDAS: " + categoria.getMedidas() );
        System.out.println("IMAGEN URL: " + categoria.getFotoUrl());           
        System.out.println(cat.mostrar(1));
        int id = categoria.getIdCategoriaCama();
        categoria = cat.modificar(categoria2,id);
        System.out.println("Se modifico la siguiente categoria de cama: ");
        System.out.println("TIPO: " + categoria.getTipo() );  
        System.out.println("COLOR: " + categoria.getColor() );
        System.out.println("MEDIDAS: " + categoria.getMedidas() );
        System.out.println("IMAGEN URL: " + categoria.getFotoUrl());
        //borrar categoriaCama
        System.out.println(cat.borrar(categoria.getIdCategoriaCama()));
    }
    
}
