package sistemaSivarDog;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

public class Persona {
 private final String nombreCompleto;
 public String numeroIdentificacion;
 public String telefono;
 public String correoElectronico;

 public Persona(String nombreCompleto, String numeroIdentificacion, String telefono, String correoElectronico) {
     if (nombreCompleto != null && !nombreCompleto.isBlank()) {
         if (numeroIdentificacion != null && !numeroIdentificacion.isBlank()) {
             if (telefono != null && telefono.length() >= 8) {
                 if (correoElectronico != null && correoElectronico.contains("@")) {
                     this.nombreCompleto = nombreCompleto;
                     this.numeroIdentificacion = numeroIdentificacion;
                     this.telefono = telefono;
                     this.correoElectronico = correoElectronico;
                 } else {
                     throw new IllegalArgumentException("Correo electrónico inválido.");
                 }
             } else {
                 throw new IllegalArgumentException("El teléfono debe tener al menos 8 dígitos.");
             }
         } else {
             throw new IllegalArgumentException("El número de identificación no puede estar vacío.");
         }
     } else {
         throw new IllegalArgumentException("El nombre no puede estar vacío.");
     }
 }

 public String getNombreCompleto() {
     return this.nombreCompleto;
 }
}
