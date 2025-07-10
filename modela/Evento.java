package modela;

public class Evento {
    String dependenciaOrigen;
    String dependenciaDestino;
    String fechaHora;
    String descripcion;
    // Constructor, getters y setters
    public Evento(String dependenciaOrigen, String dependenciaDestino, String fechaHora, String descripcion) {
        this.dependenciaOrigen = dependenciaOrigen;
        this.dependenciaDestino = dependenciaDestino;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
    }
    public String getDependenciaOrigen() {
        return dependenciaOrigen;
    }
    public void setDependenciaOrigen(String dependenciaOrigen) {
        this.dependenciaOrigen = dependenciaOrigen;
    }
    public String getDependenciaDestino() {
        return dependenciaDestino;
    }
    public void setDependenciaDestino(String dependenciaDestino) {
        this.dependenciaDestino = dependenciaDestino;
    }
    public String getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
