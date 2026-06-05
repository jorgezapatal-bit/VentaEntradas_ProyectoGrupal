package general;

import modelo.ZonaArreglo;
import modelo.UsuarioArreglo;
import modelo.Usuario;
import modelo.VentaArreglo; // IMPORTANTE: Agregar esto

public class Sistema {
    public static UsuarioArreglo usuarios = new UsuarioArreglo(100);
    public static Usuario usuarioConectado = null;
    public static ZonaArreglo zonas = new ZonaArreglo(4); 
    
    // NUEVO: Nuestra base de datos para guardar el registro de todas las ventas
    public static VentaArreglo ventas = new VentaArreglo(100); 
}