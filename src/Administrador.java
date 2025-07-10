import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Administrador {
    private ArrayList<Expediente> expedientes;

    public Administrador() {
        this.expedientes = new ArrayList<>();
    }

    // Registrar nuevo expediente
    public void registrarExpediente(String id, String prioridad, String asunto,
                                    String documentoReferencia, Interesado interesado) {
        Expediente nuevo = new Expediente(id, prioridad, asunto, documentoReferencia, interesado);
        expedientes.add(nuevo);
    }

    // Registrar movimiento del expediente
    public void registrarMovimiento(String idExpediente, String dependencia, String accion) {
        Expediente exp = buscarExpediente(idExpediente);
        if (exp != null) {
            exp.agregarSeguimiento(dependencia, accion);
        }
    }

    // Finalizar expediente
    public void finalizarExpediente(String idExpediente) {
        Expediente exp = buscarExpediente(idExpediente);
        if (exp != null) {
            exp.finalizarExpediente();
        }
    }

    // Proporcionar seguimiento al interesado
    public ArrayList<String> obtenerSeguimiento(String idExpediente) {
        Expediente exp = buscarExpediente(idExpediente);
        return exp != null ? exp.getSeguimientos() : null;
    }

    // Alertas de expedientes no atendidos
    public ArrayList<Expediente> obtenerExpedientesNoAtendidos() {
        ArrayList<Expediente> noAtendidos = new ArrayList<>();
        for (Expediente exp : expedientes) {
            if (exp.getFechaFin() == null) {
                noAtendidos.add(exp);
            }
        }

        // Ordenar por prioridad y antigüedad
        Collections.sort(noAtendidos, new Comparator<Expediente>() {
            @Override
            public int compare(Expediente e1, Expediente e2) {
                int prioridadComp = prioridadToInt(e2.getPrioridad()) - prioridadToInt(e1.getPrioridad());
                if (prioridadComp != 0) return prioridadComp;
                return e1.getFechaInicio().compareTo(e2.getFechaInicio());
            }

            private int prioridadToInt(String prioridad) {
                switch (prioridad.toLowerCase()) {
                    case "alta": return 3;
                    case "media": return 2;
                    case "baja": return 1;
                    default: return 0;
                }
            }
        });

        return noAtendidos;
    }

    // Método auxiliar para buscar expediente
    private Expediente buscarExpediente(String id) {
        for (Expediente exp : expedientes) {
            if (exp.getId().equals(id)) {
                return exp;
            }
        }
        return null;
    }
    public void mostrarExpedientes() {
        for (Expediente exp : expedientes) {
            System.out.println(exp);
        }
    }
}