package ventaentradas;

import controlador.ControladorInicio;
import general.Sistema;
import modelo.Usuario;
import modelo.Zona;
import vista.frmIniciar;

public class VentaEntradas {

    public static void main(String[] args) {
        // 1. Agregamos objetos de DISTINTAS clases al mismo arreglo (Polimorfismo)
        general.Sistema.personas.add(new modelo.Usuario("Jorge", "Zavaleta", "12345678", "secreto123", true));
        general.Sistema.personas.add(new modelo.Cliente("Maria", "Gomez", "87654321", "cliente1", 0)); // Un cliente
        general.Sistema.personas.add(new modelo.Cliente("Luis", "Perez", "11112222", "cliente2", 0));  // Otro cliente
        
        // Zonas de prueba
        general.Sistema.zonas.add(new modelo.Zona("VIP", 50, 200));     
        general.Sistema.zonas.add(new modelo.Zona("General", 100, 50)); 
        
        // 2. Instanciamos la vista y el controlador
        vista.frmIniciar fInicio = new vista.frmIniciar();
        
        // Le pasamos el nuevo arreglo de personas
        controlador.ControladorInicio controlador = new controlador.ControladorInicio(general.Sistema.personas, fInicio);
        
        // 3. ¡Arrancamos el programa!
        controlador.iniciar();
    }
}