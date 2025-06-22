package sistemaSivarDog;

import java.time.LocalDateTime;

public class Citas {
 private final Mascota mascota;
 private final Veterinario veterinario;
 private final LocalDateTime fechaHora;
 private final String motivoConsulta;
 private  EstadoCita estado;
 private String diagnostico;

 public Citas(Mascota mascota, Veterinario veterinario, LocalDateTime fechaHora, String motivoConsulta) {
     if (mascota == null) {
         throw new IllegalArgumentException("Mascota no puede ser nula");
     } else if (veterinario == null) {
         throw new IllegalArgumentException("Veterinario no puede ser nulo");
     } else if (fechaHora == null) {
         throw new IllegalArgumentException("Fecha y hora no puede ser nula");
     } else if (fechaHora.isBefore(LocalDateTime.now())) {
         throw new IllegalArgumentException("La cita no puede ser en el pasado");
     } else if (motivoConsulta != null && !motivoConsulta.isBlank()) {
         this.mascota = mascota;
         this.veterinario = veterinario;
         this.fechaHora = fechaHora;
         this.motivoConsulta = motivoConsulta;
         this.estado = Citas.EstadoCita.PROGRAMADA;
         this.diagnostico = "";
     } else {
         throw new IllegalArgumentException("Motivo de consulta obligatorio");
     }
 }


 public String toString() {
     String var10000 = this.mascota.getNombre();
     return "\nCita:\nMascota:" + var10000 + "\nVeterinario:" + this.veterinario.getNombreCompleto() + "\nFechaHora:" + this.fechaHora + "\nMotivoConsulta: '"
             + this.motivoConsulta + "'\nEstado:" + this.estado + "\nDiagnostico: '" + this.diagnostico + "'}";
 }

 public enum EstadoCita {
     PROGRAMADA,
     COMPLETADA,
     CANCELADA
 }

 public void cancelarCita() {
     if (this.estado == EstadoCita.PROGRAMADA) {
         this.estado = EstadoCita.CANCELADA;
     } else {
         throw new IllegalStateException("Solo puedes cancelar citas programadas.");
     }
 }

 public Veterinario getVeterinario() {
     return veterinario;
 }

 public LocalDateTime getFechaHora() {
     return fechaHora;
 }

 public String getMotivoConsulta() {
     return motivoConsulta;
 }

 public void setDiagnostico(String diagnostico) {
     this.diagnostico = diagnostico;
 }

 public EstadoCita getEstado() {
     return estado;
 }
}
