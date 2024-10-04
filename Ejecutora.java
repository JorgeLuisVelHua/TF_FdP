package TrabajoFinal_FdP2;

import java.util.Scanner;

public class Ejecutora {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaNominas sistemaNominas = new SistemaNominas();

        int opcion;

        do {
            System.out.println("Bienvenido");
            System.out.println();
            System.out.println("1. Datos del trabajador");
            System.out.println("2. Gestión de salarios y beneficios");
            System.out.println("3. Generación de recibos de pagos");
            System.out.println("0. Salir");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuDatosTrabajador(scanner, sistemaNominas);
                    break;
                case 2:
                    menuGestionSalarios(scanner, sistemaNominas);
                    break;
                case 3:
                    sistemaNominas.generarReciboPago(scanner, sistemaNominas);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void menuDatosTrabajador(Scanner scanner, SistemaNominas sistemaNominas) {
        int opcion;

        do {
            System.out.println("\n--- Datos del Trabajador ---");
            System.out.println("1. Ingresar nuevo trabajador");
            System.out.println("2. Mostrar trabajadores");
            System.out.println("3. Buscar trabajador por DNI");
            System.out.println("4. Modificar datos de trabajador");
            System.out.println("0. Regresar al menú principal");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    SistemaNominas.ingresarNuevoTrabajador(scanner, sistemaNominas);
                    break;
                case 2:
                    sistemaNominas.mostrarTrabajadores(scanner);
                    break;
                case 3:
                    SistemaNominas.buscarTrabajadorPorDNI(scanner, sistemaNominas);
                    break;
                case 4:
                    SistemaNominas.modificarDatosTrabajador(scanner, sistemaNominas);
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    System.out.println("_____________________________________");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void menuGestionSalarios(Scanner scanner, SistemaNominas sistemaNominas) {
        int opcion;

        do {
            System.out.println("\n--- Gestión de Salarios y Beneficios ---");
            System.out.println("1. Asignar salario");
            System.out.println("2. Asignación de bonos");
            System.out.println("3. Gestión de descuentos");
            System.out.println("0. Regresar al menú principal");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    sistemaNominas.asignarSalario(scanner, sistemaNominas);
                    break;
                case 2:
                    sistemaNominas.asignarBono(scanner, sistemaNominas);
                    break;
                case 3:
                    sistemaNominas.agregarDescuentos(scanner, sistemaNominas);
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

}
