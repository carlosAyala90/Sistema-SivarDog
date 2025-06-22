package sistemaSivarDog;

import java.util.ArrayList;
import java.util.List;

public class Mascota {
 private final String nombre;
 public final String especie;
 private final String raza;
 private final int edad;
 public final List<String> historialMedico;
 private final List<Citas> citas;
 private final ArrayList<Tratamiento> tratamientos;

 public Mascota(String nombre, String especie, String raza, int edad) {
     if (nombre != null && !nombre.isBlank()) {
         if (especie != null && !especie.isBlank()) {
             this.nombre = nombre;
             this.especie = especie;
             this.raza = raza;
             this.edad = edad;
             this.historialMedico = new ArrayList<>();
             this.citas = new ArrayList<>();
             this.tratamientos = new ArrayList<>();
         } else {
             throw new IllegalArgumentException("La especie de la mascota es obligatoria");
         }
     } else {
         throw new IllegalArgumentException("El nombre de la mascota es obligatorio");
     }
 }

 public String getNombre() {
     return this.nombre;
 }


 public String getRaza() {
     return this.raza;
 }

 public int getEdad() {
     return this.edad;
 }


 public void agregarCita(Citas citas) {
     if (citas == null) {
         System.out.println("No se puede agregar una cita nula");
     } else {
         this.citas.add(citas);
     }
 }


 public void agregarTratamiento(Tratamiento tratamiento) {
     this.tratamientos.add(tratamiento);
 }

 public ArrayList<Tratamiento> getTratamientos() {
     return this.tratamientos;
 }

 public List<Citas> getCitas() {
     return this.citas;
 }
}