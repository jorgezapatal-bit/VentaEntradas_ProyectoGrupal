package general;

import modelo.ZonaArreglo;
import modelo.PersonaArreglo; // Cambiamos el import
import modelo.Persona;        // Cambiamos el import
import modelo.VentaArreglo;

public class Sistema {
    // Ahora usamos la clase madre Persona para aplicar Polimorfismo
    public static PersonaArreglo personas = new PersonaArreglo(100);
    public static Persona personaConectada = null; 
    
    public static ZonaArreglo zonas = new ZonaArreglo(4); 
    public static VentaArreglo ventas = new VentaArreglo(100); 
}