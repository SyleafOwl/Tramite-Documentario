import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        // Crear administrador
        Administrador admin = new Administrador();

        // Registrar interesados
        Interesado interno = new Interesado("12345678", "Juan Pérez", "987654321", "juan@ulima.edu.pe", "ulima");
        Interesado externo = new Interesado("87654321", "María Gómez", "987123456", "maria@gmail.com", "externo");

        // Registrar expedientes
        admin.registrarExpediente("EXP-001", "alta", "Solicitud de certificado", "DOC-REF-001", interno);
        admin.registrarExpediente("EXP-002", "media", "Reclamo de notas", "DOC-REF-002", externo);

        // Registrar movimientos
        admin.registrarMovimiento("EXP-001", "Secretaría Académica", "Recepción de documentos");
        admin.registrarMovimiento("EXP-002", "Decanato", "Revisión de requisitos");

        // Finalizar expediente
        admin.finalizarExpediente("EXP-001");
        admin.finalizarExpediente("EXP-002");

        // Obtener alertas de expedientes no atendidos
        ArrayList<Expediente> alertas = admin.obtenerExpedientesNoAtendidos();
        System.out.println("Expedientes no atendidos:");
        for (Expediente exp : alertas) {
            System.out.println(exp.getId() + " - " + exp.getAsunto() + " (" + exp.getPrioridad() + ")");
        }

        admin.mostrarExpedientes();
    }
}
