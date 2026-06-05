package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.UsuarioArreglo;
import vista.frmIniciar;

public class ControladorInicio {
    private UsuarioArreglo modelo;
    private frmIniciar vista;

    public ControladorInicio(UsuarioArreglo modelo, frmIniciar vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. Leemos lo que el usuario escribió
                    String dni = vista.txtDni.getText();
                    String pass = vista.txtContrasena.getText();
                    
                    // 2. Validamos que no estén vacíos usando una excepción genérica
                    if (dni.trim().isEmpty() || pass.trim().isEmpty()) {
                        throw new Exception("Por favor, ingrese su DNI y Contraseña.");
                    }
                    
                    // 3. Buscamos en nuestra "Base de Datos" global
                    Sistema.usuarioConectado = modelo.ingresar(dni, pass);
                    
                    // 4. Validamos si lo encontró o no
                    if (Sistema.usuarioConectado != null) {
                        JOptionPane.showMessageDialog(vista, "¡Bienvenido al sistema de Conciertos!");
                        
                        // Cerramos la ventana de Login
                        vista.dispose(); 
                        
                        // Abrimos la ventana de Ventas
                        vista.frmVentas vVentas = new vista.frmVentas();
                        controlador.ControladorVentas ctrlVentas = new controlador.ControladorVentas(vVentas);
                        ctrlVentas.iniciar();
                        
                    } else {
                        JOptionPane.showMessageDialog(vista, "Error: DNI o contraseña incorrectos", "Aviso", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (Exception ex) {
                    // Captura el error de campos vacíos
                    JOptionPane.showMessageDialog(vista, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    
    public void iniciar() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}