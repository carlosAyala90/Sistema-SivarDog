package sistemaSivarDog;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaVeterinaria {
 private final ArrayList<Cliente> clientes = new ArrayList<>();
 private final ArrayList<Veterinario> veterinarios = new ArrayList<>();
 private final List<Pago> listaPagos = new ArrayList<>();

 public SistemaVeterinaria() {
     this.cargarDatosIniciales();
 }

 public void mostrarMenu() {
     System.out.println("===== Sistema de Gestión de Clínica Veterinaria SivarDog=====");
     System.out.println("1. Registrar Cliente");
     System.out.println("2. Registrar Mascota");
     System.out.println("3. Registrar Veterinario");
     System.out.println("4. Programar Cita");
     System.out.println("5. Registrar Tratamiento");
     System.out.println("6. Registrar Pago");
     System.out.println("7. Reporte de Mascotas.");
     System.out.println("8. Reporte de Citas");
     System.out.println("9. Reporte de Pagos");
     System.out.println("10. Cancelar Cita");
     System.out.println("0. Salir");
 }

 public void registrarCliente(Scanner sc) {
     System.out.println("\n--- Registrar Cliente ---");

     String nombre;
     do {
         System.out.print("Ingrese nombre completo: ");
         nombre = sc.nextLine();
         if (nombre.trim().isEmpty()) {
             System.out.println("El nombre no puede estar vacío.");
         }
     } while (nombre.trim().isEmpty());

     String id;
     do {
         System.out.print("Ingrese número de identificacion (con su guion): ");
         id = sc.nextLine();
         if (!id.matches("\\d{8}-\\d")) {
             System.out.println("ERROR, El DUI contiene 9 digitos y su guion.");
         }
     } while (!id.matches("\\d{8}-\\d"));

     String telefono;
     do {
         System.out.print("Ingrese teléfono (sin guion): ");
         telefono = sc.nextLine();
         if (!telefono.matches("\\d{8}")) {
             System.out.println("Teléfono inválido. Debe tener 8 Numeros.");
         }
     } while (!telefono.matches("\\d{8}"));

     String correo;
     do {
         System.out.print("Ingrese correo electronico: ");
         correo = sc.nextLine();
         if (!correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Gracias a un meme descubri esta madre jajaja
             System.out.println("Correo invalido.");
         }
     } while (!correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")); // regex de email

     // Si todas las validaciones pasan se registra el cliente
     Cliente nuevoCliente = new Cliente(nombre, id, telefono, correo);
     clientes.add(nuevoCliente);
     System.out.println("Cliente registrado exitosamente.");
 }


 public void registrarMascota(Scanner sc) {
     System.out.println("\n--- Registrar Mascota ---");
     if (this.clientes.isEmpty()) {
         System.out.println("No hay clientes registrados. Primero registre un cliente.");
     } else {
         System.out.println("Seleccione el cliente al que desea registrar una mascota:");

         for (int i = 0; i < this.clientes.size(); ++i) {
             System.out.println((i + 1) + ". " + this.clientes.get(i).getNombreCompleto());
         }

         System.out.print("Opción: ");
         int clienteOpcion = sc.nextInt();
         sc.nextLine();
         if (clienteOpcion >= 1 && clienteOpcion <= this.clientes.size()) {
             Cliente cliente = this.clientes.get(clienteOpcion - 1);
             System.out.print("Nombre de la mascota: ");
             String nombreMascota = sc.nextLine();
             System.out.print("Especie (perro, gato, etc.): ");
             String especie = sc.nextLine();
             System.out.print("Raza: ");
             String raza = sc.nextLine();
             System.out.print("Edad: ");
             int edad = sc.nextInt();
             sc.nextLine();

             // Si todas las validaciones pasan se registra la mascota
             Mascota mascota = new Mascota(nombreMascota, especie, raza, edad);
             cliente.agregarMascota(mascota);
             System.out.println("Mascota registrada con éxito.");
         } else {
             System.out.println("Opción inválida.");
         }
     }
 }

 public void registrarVeterinario(Scanner sc) {

     String nombre;
     do {
         System.out.print("Ingrese el nombre completo: ");
         nombre = sc.nextLine();
         if (nombre.trim().isEmpty()) {
             System.out.println("El nombre no puede estar vacío.");
         }
     } while (nombre.trim().isEmpty());

     String id;
     do {
         System.out.print("Ingrese número de identificacion (con su guion): ");
         id = sc.nextLine();
         if (!id.matches("\\d{8}-\\d")) {
             System.out.println("ERROR, El DUI contiene 9 digitos y su guion.");
         }
     } while (!id.matches("\\d{8}-\\d"));

     String telefono;
     do {
         System.out.print("Ingrese teléfono (sin guion): ");
         telefono = sc.nextLine();
         if (!telefono.matches("\\d{8}")) {
             System.out.println("Teléfono inválido. Debe tener el formato 0000-0000.");
         }
     } while (!telefono.matches("\\d{8}"));

     String correo;
     do {
         System.out.print("Ingrese correo electronico: ");
         correo = sc.nextLine();
         if (!correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Gracias a un meme descubri esta madre jajaja
             System.out.println("Correo inválido.");
         }
     } while (!correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")); // regex de email

     String especialidad;
     do {
         System.out.print("Ingrese su especialidad: ");
         especialidad = sc.nextLine();
         if (especialidad.trim().isEmpty()) {
             System.out.println("La especialidads no puede estar vacia.");
         }
     } while (especialidad.trim().isEmpty());

//Si todas las validaciones pasan se registra el veterinario
     Veterinario veterinario = new Veterinario(nombre, id, telefono, correo, especialidad);
     this.veterinarios.add(veterinario);
     System.out.println("Veterinario registrado exitosamente.");
 }

 public void registrarCita(Scanner sc) {
     if (this.clientes.isEmpty()) {
         System.out.println("No hay clientes registrados. Primero registre un cliente.");
     } else if (this.veterinarios.isEmpty()) {
         System.out.println("No hay veterinarios registrados. Primero registre un veterinario.");
     } else {
         System.out.println("\n--- Registrar Cita ---");
         System.out.println("Seleccione el cliente:");

         for (int i = 0; i < this.clientes.size(); ++i) {
             System.out.println((i + 1) + ". " + this.clientes.get(i).getNombreCompleto());
         }

         System.out.print("Opción: ");
         int clienteOpcion = sc.nextInt();
         sc.nextLine();
         if (clienteOpcion >= 1 && clienteOpcion <= this.clientes.size()) {
             Cliente cliente = this.clientes.get(clienteOpcion - 1);
             if (cliente.getListaMascotas().isEmpty()) {
                 System.out.println("Este cliente no tiene mascotas registradas.");
             } else {
                 System.out.println("Seleccione la mascota:");

                 for (int i = 0; i < cliente.getListaMascotas().size(); ++i) {
                     System.out.println((i + 1) + ". " + cliente.getListaMascotas().get(i).getNombre());
                 }

                 System.out.print("Opción: ");
                 int mascotaOpcion = sc.nextInt();
                 sc.nextLine();
                 if (mascotaOpcion >= 1 && mascotaOpcion <= cliente.getListaMascotas().size()) {
                     Mascota mascota = cliente.getListaMascotas().get(mascotaOpcion - 1);
                     System.out.println("Seleccione el veterinario:");

                     for (int i = 0; i < this.veterinarios.size(); ++i) {
                         System.out.println((i + 1) + ". " + this.veterinarios.get(i).getNombreCompleto() + " - " + this.veterinarios.get(i).getEspecialidad());
                     }

                     System.out.print("Opción: ");
                     int vetOpcion = sc.nextInt();
                     sc.nextLine();
                     if (vetOpcion >= 1 && vetOpcion <= this.veterinarios.size()) {
                         Veterinario veterinario = this.veterinarios.get(vetOpcion - 1);
                         System.out.print("Ingrese fecha y hora de la cita (formato YYYY-MM-DD HH:MM): ");
                         String fechaHoraStr = sc.nextLine();
                         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                         LocalDateTime fechaHora;
                         try {
                             fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
                         } catch (DateTimeParseException var13) {
                             System.out.println("Formato de fecha/hora inválido. Intenta de nuevo.");
                             return;
                         }

                         System.out.print("Ingrese motivo de la cita: ");
                         String motivo = sc.nextLine();

                         Citas cita = new Citas(mascota, veterinario, fechaHora, motivo);
                         mascota.agregarCita(cita);
                         veterinario.agregarCita(cita);
                         System.out.println("Cita registrada con éxito.");
                     } else {
                         System.out.println("Opción inválida.");
                     }
                 } else {
                     System.out.println("Opción inválida.");
                 }
             }
         } else {
             System.out.println("Opción inválida.");
         }
     }
 }

 public void agregarTratamiento(Scanner sc) {
     if (this.clientes.isEmpty()) {
         System.out.println("No hay clientes registrados. Primero registre un cliente.");
     } else if (this.veterinarios.isEmpty()) {
         System.out.println("No hay veterinarios registrados. Primero registre un veterinario.");
     } else {
         System.out.println("\n--- Registrar Tratamiento ---");
         System.out.println("Seleccione el cliente:");

         for (int i = 0; i < this.clientes.size(); ++i) {
             System.out.println((i + 1) + ". " + this.clientes.get(i).getNombreCompleto());
         }

         System.out.print("Opción: ");
         int clienteOpcion = sc.nextInt();
         sc.nextLine();
         if (clienteOpcion >= 1 && clienteOpcion <= this.clientes.size()) {
             Cliente cliente = this.clientes.get(clienteOpcion - 1);
             if (cliente.getListaMascotas().isEmpty()) {
                 System.out.println("Este cliente no tiene mascotas registradas.");
             } else {
                 System.out.println("Seleccione la mascota:");

                 for (int i = 0; i < cliente.getListaMascotas().size(); ++i) {
                     System.out.println(i + 1 + ". " +  cliente.getListaMascotas().get(i).getNombre());
                 }

                 System.out.print("Opción: ");
                 int mascotaOpcion = sc.nextInt();
                 sc.nextLine();
                 if (mascotaOpcion >= 1 && mascotaOpcion <= cliente.getListaMascotas().size()) {
                     Mascota mascota = cliente.getListaMascotas().get(mascotaOpcion - 1);

                     System.out.println("Seleccione la cita para asignar el tratamiento:");

                     for (int i = 0; i < mascota.getCitas().size(); ++i) {
                         Citas cita = mascota.getCitas().get(i);
                         System.out.println((i + 1) + ". " + cita.toString());
                     }

                     System.out.print("Opción: ");
                     int citaOpcion = sc.nextInt();
                     sc.nextLine();
                     if (citaOpcion >= 1 && citaOpcion <= mascota.getCitas().size()) {
                         Citas cita = mascota.getCitas().get(citaOpcion - 1);

                         System.out.print("Ingrese diagnóstico para la cita: ");
                         String diagnostico = sc.nextLine();
                         cita.setDiagnostico(diagnostico);

                         System.out.print("Ingrese descripción del tratamiento: ");
                         String descripcion = sc.nextLine();

                         System.out.print("Ingrese costo del tratamiento: ");
                         double costo = sc.nextDouble();

                         System.out.print("Ingrese duración del tratamiento (en días): ");
                         int duracionDias = sc.nextInt();
                         sc.nextLine();



                         try {
                             Tratamiento tratamiento = new Tratamiento(cita, descripcion, costo, duracionDias);
                             mascota.agregarTratamiento(tratamiento);
                             System.out.println("Tratamiento registrado con éxito.");
                         } catch (IllegalArgumentException e) {
                             System.out.println("Error al registrar tratamiento: " + e.getMessage());
                         }

                     } else {
                         System.out.println("Opción inválida.");
                     }
                 } else {
                     System.out.println("Opción inválida.");
                 }
             }
         } else {
             System.out.println("Opción inválida.");
         }
     }
 }

 public void registarPago(Scanner sc) {
     if (this.clientes.isEmpty()) {
         System.out.println("No hay clientes registrados. Primero registre un cliente.");
     } else {
         System.out.println("\n--- Registrar Pago ---");
         System.out.println("Seleccione el cliente:");

         for (int i = 0; i < this.clientes.size(); ++i) {
             System.out.println((i + 1) + ". " + clientes.get(i).getNombreCompleto());
         }

         System.out.print("Opción: ");
         int clienteOpcion = sc.nextInt();
         sc.nextLine();

         if (clienteOpcion >= 1 && clienteOpcion <= clientes.size()) {
             Cliente cliente = clientes.get(clienteOpcion - 1);

             if (cliente.getListaMascotas().isEmpty()) {
                 System.out.println("Este cliente no tiene mascotas registradas.");
             } else {
                 System.out.println("Seleccione la mascota:");

                 for (int i = 0; i < cliente.getListaMascotas().size(); ++i) {
                     System.out.println((i + 1) + ". " + cliente.getListaMascotas().get(i).getNombre());
                 }

                 System.out.print("Opción: ");
                 int mascotaOpcion = sc.nextInt();
                 sc.nextLine();

                 if (mascotaOpcion >= 1 && mascotaOpcion <= cliente.getListaMascotas().size()) {
                     Mascota mascota = cliente.getListaMascotas().get(mascotaOpcion - 1);

                     if (mascota.getTratamientos().isEmpty()) {
                         System.out.println("Esta mascota no tiene tratamientos registrados.");


                     } else {
                         System.out.println("Seleccione el tratamiento:");


                         for (int i = 0; i < mascota.getTratamientos().size(); ++i) {
                             Tratamiento t = mascota.getTratamientos().get(i);
                             System.out.println((i + 1) + ". " + t.getDescripcion() + " - $" + t.getCosto() + " [" + t.getEstadoPago() + "]");
                         }


                         System.out.print("Opción: ");
                         int tratamientoOpcion = sc.nextInt();
                         sc.nextLine();

                         if (tratamientoOpcion >= 1 && tratamientoOpcion <= mascota.getTratamientos().size()) {
                             Tratamiento tratamiento = mascota.getTratamientos().get(tratamientoOpcion - 1);

                             if (tratamiento.getPago() != null && tratamiento.getPago().getEstadoDePago() == Pago.estadoPago.PAGADO) {
                                 System.out.println("Este Tratamiento ya ha sido Pagado. Nop se puede volver a pagar.");
                                 return;
                             }

                             double monto = -1;
                             while (monto <= 0) {
                                 System.out.print("Ingrese monto pagado: ");
                                 String entrada = sc.nextLine();

                                 try {
                                     monto = Double.parseDouble(entrada);
                                     if (monto <= 0) {
                                         System.out.println("El monto debe ser mayor que cero.");
                                     }
                                 } catch (NumberFormatException e) {
                                     System.out.println("Entrada inválida. Ingrese un numero valido (sin letras ni simbolos).");
                                 }
                             }


                             Pago.metodoPago metodoDePago = null;
                             while (metodoDePago == null) {
                                 System.out.print("Ingrese método de pago (EFECTIVO o TARJETA): ");
                                 String entradaMetodo = sc.nextLine().toUpperCase();

                                 try {
                                     metodoDePago = Pago.metodoPago.valueOf(entradaMetodo);
                                 } catch (IllegalArgumentException e) {
                                     System.out.println("Método inválido. Intente nuevamente.");
                                 }
                             }


                             Pago pago = new Pago(cliente, monto, metodoDePago);
                             pago.registrarPago(); // cambia el estado a PAGADO
                             tratamiento.setPago(pago);
                             this.listaPagos.add(pago);


                             System.out.println("Pago registrado exitosamente.");
                         } else {
                             System.out.println("Opción inválida.");
                         }
                     }
                 } else {
                     System.out.println("Opción inválida.");
                 }
             }
         } else {
             System.out.println("Opción inválida.");
         }
     }
 }

 public void mostrarCitas() {
     System.out.println("\n--- Listado de Citas ---");

     boolean hayCitas = false;

     for (Cliente cliente : clientes) {
         for (Mascota mascota : cliente.getListaMascotas()) {
             for (Citas cita : mascota.getCitas()) {
                 if (cita.getEstado() == Citas.EstadoCita.PROGRAMADA) {
                     hayCitas = true;
                     System.out.println("Cliente: " + cliente.getNombreCompleto());
                     System.out.println("Mascota: " + mascota.getNombre());
                     System.out.println("Veterinario: " + cita.getVeterinario().getNombreCompleto());
                     System.out.println("Fecha y hora: " + cita.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                     System.out.println("Motivo: " + cita.getMotivoConsulta());
                     System.out.println("Estado De la cita: PROGRAMADA");
                     System.out.println("-----------------------------------");
                 } else if (cita.getEstado() == Citas.EstadoCita.CANCELADA) {
                     hayCitas = true;
                     System.out.println("Cliente: " + cliente.getNombreCompleto());
                     System.out.println("Mascota: " + mascota.getNombre());
                     System.out.println("Veterinario: " + cita.getVeterinario().getNombreCompleto());
                     System.out.println("Fecha y hora: " + cita.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                     System.out.println("Motivo: " + cita.getMotivoConsulta());
                     System.out.println("Estado De la cita: CANCELADA");
                     System.out.println("-----------------------------------");
                 }

             }
         }
     }

     if (!hayCitas) {
         System.out.println("No hay citas registradas.");
     }
 }

 public void mostrarReportePagos() {
     System.out.println("\n--- Reporte de Pagos Registrados ---");

     if (listaPagos.isEmpty()) {
         System.out.println("No hay pagos registrados.");
         return;
     }

     for (Pago pago : listaPagos) {
         System.out.println("Cliente: " + pago.getCliente().getNombreCompleto());
         System.out.println("Monto pagado: $" + pago.getMontoTotal());
         System.out.println("Método de pago: " + pago.getMetodoDePago());
         System.out.println("Estado de pago: " + pago.getEstadoDePago());
         System.out.println("-----------------------------------");
     }
 }

 public void mostrarMascotasAgregadas() {
     System.out.println("\n--- Lista de mascotas agregadas. ---");

     for (Cliente cliente : clientes) {
         for (Mascota mascota : cliente.getListaMascotas()) {
             System.out.println("Cliente: " + cliente.getNombreCompleto());
             System.out.println("\tNombrer Mascota: " + mascota.getNombre());
             System.out.println("\tRaza: " + mascota.getRaza());
             System.out.println("\tEdad: " + mascota.getEdad() + " años");
             System.out.println("----------------------------------");
         }
     }
 }

 public void cancelarCita(Scanner sc) {
     System.out.println("\n--- Cancelar Cita ---");

     if (clientes.isEmpty()) {
         System.out.println("No hay clientes registrados.");
         return;
     }

     System.out.println("Seleccione el cliente:");
     for (int i = 0; i < clientes.size(); i++) {
         System.out.println((i + 1) + ". " + clientes.get(i).getNombreCompleto());
     }
     int clienteOpcion = sc.nextInt();
     sc.nextLine();

     if (clienteOpcion < 1 || clienteOpcion > clientes.size()) {
         System.out.println("Opción inválida.");
         return;
     }

     Cliente cliente = clientes.get(clienteOpcion - 1);

     if (cliente.getListaMascotas().isEmpty()) {
         System.out.println("Este cliente no tiene mascotas registradas.");
         return;
     }

     System.out.println("Seleccione la mascota:");
     for (int i = 0; i < cliente.getListaMascotas().size(); i++) {
         System.out.println((i + 1) + ". " + cliente.getListaMascotas().get(i).getNombre());
     }
     int mascotaOpcion = sc.nextInt();
     sc.nextLine();

     if (mascotaOpcion < 1 || mascotaOpcion > cliente.getListaMascotas().size()) {
         System.out.println("Opción inválida.");
         return;
     }

     Mascota mascota = cliente.getListaMascotas().get(mascotaOpcion - 1);

     if (mascota.getCitas().isEmpty()) {
         System.out.println("Esta mascota no tiene citas.");
         return;
     }

     System.out.println("Seleccione la cita a cancelar:");
     for (int i = 0; i < mascota.getCitas().size(); i++) {
         System.out.println((i + 1) + ". " + mascota.getCitas().get(i));
     }
     int citaOpcion = sc.nextInt();
     sc.nextLine();

     if (citaOpcion < 1 || citaOpcion > mascota.getCitas().size()) {
         System.out.println("Opción inválida.");
         return;
     }

     Citas cita = mascota.getCitas().get(citaOpcion - 1);
     try {
         cita.cancelarCita();
         System.out.println("Cita cancelada con éxito.");
     } catch (IllegalStateException e) {
         System.out.println("No se puede cancelar la cita: " + e.getMessage());
     }
 }



 private void cargarDatosIniciales() {
     Cliente cliente1 = new Cliente("Audiel Isaac", "12345678-9", "2525-2525", "auddiel@mail.com");
     Cliente cliente2 = new Cliente("Denilson Méndez", "98765432-1", "7777-8888", "denilson321@mail.com");
     this.clientes.add(cliente1);
     this.clientes.add(cliente2);

     Mascota mascota1 = new Mascota("Peter", "Perro", "Buldog", 2);
     Mascota mascota2 = new Mascota("Bruce", "Gato", "Sianes", 1);
     cliente1.agregarMascota(mascota1);
     cliente2.agregarMascota(mascota2);

     Veterinario vet1 = new Veterinario("Dra. Mariana Flores", "20406080-9", "5555-1234", "mariana@vet.com", "Dermatología");
     Veterinario vet2 = new Veterinario("Dr. Pedro Gómez", "15678342-8", "5555-5678", "pedro@vet.com", "Cardiologia");
     this.veterinarios.add(vet1);
     this.veterinarios.add(vet2);


     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
     LocalDateTime fechaHora = LocalDateTime.parse("2025-10-20 12:30", formatter);
     LocalDateTime fechaHora2 = LocalDateTime.parse("2025-10-20 13:00", formatter);
     Citas citas1 = new Citas(mascota1,vet1,fechaHora, "Solo pasa rascandose y llora al hacerlo.");
     Citas citas2 = new Citas(mascota2,vet2,fechaHora2,"Se le siente el corazón latiendo muy rápido o con saltos");
     mascota1.agregarCita(citas1);
     mascota2.agregarCita(citas2);


     Tratamiento tratamiento1 = new Tratamiento(citas1,"Ivermectina", 12.50,10);
     Tratamiento tratamiento2 = new Tratamiento(citas1," Antibiótico + Antiinflamatorio", 6.35,7);
     Tratamiento tratamiento3 = new Tratamiento(citas2,"Enalapril", 20,30);
     mascota1.agregarTratamiento(tratamiento1);
     mascota1.agregarTratamiento(tratamiento2);
     mascota2.agregarTratamiento(tratamiento3);

     Pago pago1 = new Pago(cliente1,12.50, Pago.metodoPago.TARJETA);
     pago1.registrarPago();
     tratamiento1.setPago(pago1);
     this.listaPagos.add(pago1);
 }


}