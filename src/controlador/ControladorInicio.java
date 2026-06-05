package controlador;

import general.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.PersonaArreglo; // Import actualizado
import vista.frmIniciar;

public class ControladorInicio {
    private PersonaArreglo modelo; // Ahora recibe el arreglo polimórfico
    private frmIniciar vista;

    public ControladorInicio(PersonaArreglo modelo, frmIniciar vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dni = vista.txtDni.getText();
                    String pass = vista.txtContrasena.getText();
                    
                    if (dni.trim().isEmpty() || pass.trim().isEmpty()) {
                        throw new Exception("Por favor, ingrese su DNI y Contraseña.");
                    }
                    
                    // Buscamos a la Persona (puede ser Usuario o Cliente)
                    Sistema.personaConectada = modelo.ingresar(dni, pass);
                    
                    if (Sistema.personaConectada != null) {
                        JOptionPane.showMessageDialog(vista, "¡Bienvenido al sistema de Conciertos!");
                        
                        vista.dispose(); 
                        vista.frmVentas vVentas = new vista.frmVentas();
                        controlador.ControladorVentas ctrlVentas = new controlador.ControladorVentas(vVentas);
                        ctrlVentas.iniciar();
                        
                    } else {
                        JOptionPane.showMessageDialog(vista, "Error: DNI o contraseña incorrectos", "Aviso", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (Exception ex) {
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