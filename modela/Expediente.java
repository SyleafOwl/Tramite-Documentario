package modela;
import java.util.Date;
import estructura.ListaSimple;

public class Expediente {
    private String id;
    private String prioridad;
    private String asunto;
    private String documentoReferencia;
    private Interesado interesado;
    private Date fechaInicio;
    private Date fechaFin;
    private ListaSimple<String> seguimientos;
    private ListaSimple<String> documentosGenerados;
    private String comprobanteCierre = "";

    public Expediente(String id, String prioridad, String asunto,
                      String documentoReferencia, Interesado interesado) {
        this.id = id;
        this.prioridad = prioridad;
        this.asunto = asunto;
        this.documentoReferencia = documentoReferencia;
        this.interesado = interesado;
        this.fechaInicio = new Date(); // Fecha actual al crear
        this.fechaFin = null;
        this.seguimientos = new ListaSimple<>();
        this.documentosGenerados = new ListaSimple<>();
    }

    // Métodos para seguimientos
    public void agregarSeguimiento(String dependencia, String accion) {
        String seguimiento = new Date() + " - " + dependencia + ": " + accion;
        this.seguimientos.agregar(seguimiento);
    }

    public void finalizarExpediente() {
        this.fechaFin = new Date();
    }

    // Métodos para documentos
    public void agregarDocumentoGenerado(String nombreDocumento) {
        this.documentosGenerados.agregar(nombreDocumento);
    }

    // Aqui puse todos los get y set por si se necesita adelante xd

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(String documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
    }

    public Interesado getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ListaSimple<String> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(ListaSimple<String> seguimientos) {
        this.seguimientos = seguimientos;
    }

    public ListaSimple<String> getDocumentosGenerados() {
        return documentosGenerados;
    }

    public void setDocumentosGenerados(ListaSimple<String> documentosGenerados) {
        this.documentosGenerados = documentosGenerados;
    }

    public void setComprobanteCierre(String comprobante) {
        this.comprobanteCierre = comprobante;
    }
    public String getComprobanteCierre() {
        return comprobanteCierre;
    }

    @Override
    public String toString() {
        return id + "." + interesado.getNombres() + "." + interesado.getDni();
    }
}