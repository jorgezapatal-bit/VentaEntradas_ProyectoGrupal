package ventaentradas;

import controlador.ControladorInicio;
import general.Sistema;
import modelo.Usuario;
import modelo.Zona;
import vista.frmIniciar;

public class VentaEntradas {

    public static void main(String[] args) {
        // 1. Creamos un usuario "quemado"
        Sistema.usuarios.add(new Usuario("Jorge", "Zavaleta", "12345678", "secreto123", true));
        
        // --- NUEVO: Agregamos zonas de prueba al sistema ---
        Sistema.zonas.add(new Zona("VIP", 50, 200));     // Capacidad 50, Precio 200
        Sistema.zonas.add(new Zona("General", 100, 50)); // Capacidad 100, Precio 50
        
        // 2. Instanciamos la vista y el controlador
        frmIniciar fInicio = new frmIniciar();
        ControladorInicio controlador = new ControladorInicio(Sistema.usuarios, fInicio);
        
        // 3. ¡Arrancamos el programa!
        controlador.iniciar();
    }
}