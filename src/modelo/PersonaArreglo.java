package modelo;

public class PersonaArreglo {
    private final Persona[] personas;
    private int indice;

    public PersonaArreglo(int tamaño) {
        this.personas = new Persona[tamaño];
        this.indice = 0;
    }

    public boolean add(Persona p) {
        if (this.indice < this.personas.length) {
            this.personas[this.indice] = p;
            this.indice++;
            return true;
        }
        return false;
    }

    // Método para el Login
    public Persona ingresar(String dni, String contrasena) {
        for (int i = 0; i < this.indice; i++) {
            if (this.personas[i].getDni().equals(dni) && this.personas[i].getContrasena().equals(contrasena)) {
                return this.personas[i];
            }
        }
        return null;
    }

    // ¡NUEVO: Método polimórfico usando instanceof igual al del profesor!
    public int totalPersonasPorTipo(int tipo) {
        int num_usuarios = 0, num_clientes = 0;
        
        for (int i = 0; i < this.indice; i++) {
            if (this.personas[i] instanceof Usuario) {
                num_usuarios++;
            }
            if (this.personas[i] instanceof Cliente) {
                num_clientes++;
            }
        }
        
        if (tipo == 0) return num_usuarios;
        else return num_clientes;
    }
}