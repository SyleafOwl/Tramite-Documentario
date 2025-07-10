package modela;
public class Interesado {
    private String dni;
    private String nombres;
    private String telefono;
    private String email;
    private String tipo; // "ulima" o "externo"

    public Interesado(String dni, String nombres, String telefono, String email, String tipo) {
        this.dni = dni;
        this.nombres = nombres;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
