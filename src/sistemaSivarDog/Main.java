package sistemaSivarDog;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaVeterinaria sistema = new SistemaVeterinaria();
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            sistema.mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    sistema.registrarCliente(sc);
                    break;
                case 2:
                    sistema.registrarMascota(sc);
                    break;
                case  3:
                    sistema.registrarVeterinario(sc);
                    break;
                case  4:
                    sistema.registrarCita(sc);
                    break;
                case  5:
                    sistema.agregarTratamiento(sc);
                    break;
                case  6:
                    sistema.registarPago(sc);
                    break;
                case  7:
                    sistema.mostrarMascotasAgregadas();
                    break;
               case  8:
                   sistema.mostrarCitas();
                    break;
                case 9:
                    sistema.mostrarReportePagos();
                    break;
                case 10:
                    sistema.cancelarCita(sc);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema. ¡Gracias!");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
            System.out.println();

        } while (opcion != 0);

        sc.close();
    }
}