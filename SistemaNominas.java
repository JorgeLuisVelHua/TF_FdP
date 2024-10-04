package TrabajoFinal_FdP2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaNominas {
    public List<Trabajador> listaTrabajadores = new ArrayList<>();
    public void agregarTrabajador(Trabajador t) throws ExcepcionDNIRegistrado {
        for (Trabajador trabajador : listaTrabajadores) {
            if (trabajador.getDni().equals(t.getDni())) {
                throw new ExcepcionDNIRegistrado("El DNI " + t.getDni() + " ya está registrado.");
            }
        }
        listaTrabajadores.add(t);
    }

    public void mostrarTrabajadores(Scanner scanner) {
        if (listaTrabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
            return;
        }

        for (int i = 0; i < listaTrabajadores.size(); i++) {
            Trabajador t = listaTrabajadores.get(i);
            System.out.println((i + 1) + ". " + t.getNombres() + " " + t.getApellidos() + " (" + t.getClass().getSimpleName() + ")");
        }

        System.out.print("Seleccione un trabajador para ver más detalles (o ingrese 0 para regresar): ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        if (seleccion > 0 && seleccion <= listaTrabajadores.size()) {
            Trabajador seleccionado = listaTrabajadores.get(seleccion - 1);
            System.out.println("\nDetalles del trabajador:");
            System.out.println(seleccionado.mostrarDatosSinSueldoNeto());
        } else if (seleccion == 0) {
            System.out.println("Regresando al menú anterior...");
        } else {
            System.out.println("Selección inválida.");
        }
    }

    public void generarReciboPago(Trabajador trabajador) {
        double bonoFinal = trabajador.getBono();
        double descuentos = calcularDescuentos(trabajador);

        System.out.println("\nRecibo de Pago");
        System.out.println("Nombre: " + trabajador.getNombres() + " " + trabajador.getApellidos());
        System.out.println("DNI: " + trabajador.getDni());
        System.out.println("Sueldo Base: " + trabajador.getSueldoBase());
        System.out.println("Bono: " + bonoFinal);
        System.out.println("Descuentos: " + descuentos);
        System.out.println("Sueldo Neto: " + trabajador.calcularSueldoNeto());
        System.out.println("-----------------------------");
    }

    private double calcularDescuentos(Trabajador trabajador) {
        double sueldoPorDia = trabajador.getSueldoBase() / 24;
        double descuento = sueldoPorDia * trabajador.getDiasFaltos();
        return descuento;
    }


    public Trabajador buscarTrabajadorPorDni(String dni) throws ExcepcionDNIRegistrado {
        if (listaTrabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados en el sistema.");
            throw new ExcepcionDNIRegistrado("No se encontró el trabajador.");
        }

        for (Trabajador t : listaTrabajadores) {
            if (t.getDni().equals(dni)) {
                return t;
            }
        }

        throw new ExcepcionDNIRegistrado("El trabajador con DNI " + dni + " no está registrado.");
    }


    public static void ingresarNuevoTrabajador(Scanner scanner, SistemaNominas sistemaNominas) {
        System.out.println("\n--- Ingresar Nuevo Trabajador ---");
        System.out.print("Ingrese los nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese los apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese el DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese el domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Ingrese el correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese la fecha de ingreso (dd/mm/yyyy): ");
        String fechaIngreso = scanner.nextLine();
        System.out.print("Ingrese el sueldo base: ");
        double sueldoBase = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Seleccione el tipo de trabajador (1: Administrativo, 2: Operativo, 3: Técnico): ");
        int tipoTrabajador = scanner.nextInt();
        scanner.nextLine();


        int horasExtra = 0;
        int diasFaltos = 0;

        try {
            Trabajador trabajador;
            switch (tipoTrabajador) {
                case 1:
                    trabajador = new TrabajadorAdministrativo(nombres, apellidos, dni, domicilio, correo, fechaIngreso, sueldoBase, 0, horasExtra, diasFaltos);
                    break;
                case 2:
                    trabajador = new TrabajadorOperativo(nombres, apellidos, dni, domicilio, correo, fechaIngreso, sueldoBase, 0, horasExtra, diasFaltos);
                    break;
                case 3:
                    trabajador = new TrabajadorTecnico(nombres, apellidos, dni, domicilio, correo, fechaIngreso, sueldoBase, 0, horasExtra, diasFaltos);
                    break;
                default:
                    System.out.println("Tipo de trabajador no válido.");
                    return;
            }
            sistemaNominas.agregarTrabajador(trabajador);
            System.out.println("Trabajador añadido exitosamente.");
        } catch (ExcepcionDNIRegistrado e) {
            System.out.println(e.getMessage());
        }
    }

    static void buscarTrabajadorPorDNI(Scanner scanner, SistemaNominas sistemaNominas) {
        if (sistemaNominas.listaTrabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
            return;
        }

        for (int i = 0; i < sistemaNominas.listaTrabajadores.size(); i++) {
            Trabajador t = sistemaNominas.listaTrabajadores.get(i);
            System.out.println((i + 1) + ". " + t.getNombres() + " " + t.getApellidos() + " (" + t.getDni() + ")");
        }

        System.out.print("\nIngrese el DNI del trabajador a visualizar: ");
        String dni = scanner.nextLine();

        try {
            Trabajador trabajador = sistemaNominas.buscarTrabajadorPorDni(dni);
            System.out.println("\nDatos actuales del trabajador:");
            System.out.println(trabajador.mostrarDatosSinSueldoNeto());
        } catch (ExcepcionDNIRegistrado e) {
            System.out.println(e.getMessage());
        }
    }



    static void modificarDatosTrabajador(Scanner scanner, SistemaNominas sistemaNominas) {
        for (int i = 0; i < sistemaNominas.listaTrabajadores.size(); i++) {
            Trabajador t = sistemaNominas.listaTrabajadores.get(i);
            System.out.println((i + 1) + ". " + t.getNombres() + " " + t.getApellidos() + " (" + t.getDni() + ")");
        }

        System.out.print("\nIngrese el DNI del trabajador a modificar: ");
        String dni = scanner.nextLine();

        try {
            Trabajador trabajador = sistemaNominas.buscarTrabajadorPorDni(dni);
            System.out.println("\nDatos actuales del trabajador:");
            System.out.println(trabajador.mostrarDatosSinSueldoNeto());

            System.out.println("Ingrese el nuevo nombre (o presione Enter para mantener el actual): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()){
                trabajador.setNombres(nuevoNombre);
            }
            System.out.println("Ingrese el nuevo apellido (o presione Enter para mantener el actual): ");
            String nuevoApellido = scanner.nextLine();
            if (!nuevoApellido.isEmpty()){
                trabajador.setApellidos(nuevoApellido);
            }
            System.out.println("Ingrese el nuevo DNI (o presione Enter para mantener el actual): ");
            String nuevoDNI = scanner.nextLine();
            if (!nuevoDNI.isEmpty()){
                trabajador.setDni(nuevoDNI);
            }
            System.out.println("Ingrese el nuevo domicilio (o presione Enter para mantener el actual): ");
            String nuevoDomicilio = scanner.nextLine();
            if (!nuevoDomicilio.isEmpty()){
                trabajador.setDomicilio(nuevoDomicilio);
            }
            System.out.println("Ingrese el nuevo correo (o presione Enter para mantener el actual): ");
            String nuevoCorreo = scanner.nextLine();
            if (!nuevoCorreo.isEmpty()){
                trabajador.setCorreo(nuevoCorreo);
            }

            System.out.println("___________________________________________________________");
            System.out.println("El sueldo base actual es de: " + trabajador.getSueldoBase());
            System.out.println("Las horas extra actuales son: " + trabajador.getHorasExtra());
            System.out.println("Los días faltos actuales son: " + trabajador.getDiasFaltos());
            System.out.println("Si desea modificarlos, por favor utilice la opción de gestión de salarios y beneficios.");

            System.out.println("Datos del trabajador actualizados correctamente.");
        } catch (ExcepcionDNIRegistrado e) {
            System.out.println(e.getMessage());
        }
    }
    public void generarReciboPago(Scanner scanner, SistemaNominas sistemaNominas) {
        for (int i = 0; i < sistemaNominas.listaTrabajadores.size(); i++) {
            Trabajador t = sistemaNominas.listaTrabajadores.get(i);
            System.out.println((i + 1) + ". " + t.getNombres() + " " + t.getApellidos() + " (" + t.getDni() + ")");
        }

        System.out.print("\nIngrese el DNI del trabajador para generar el recibo de pago: ");
        String dni = scanner.nextLine();

        try {
            Trabajador trabajador = sistemaNominas.buscarTrabajadorPorDni(dni);
            sistemaNominas.generarReciboPago(trabajador);
            System.out.println("Recibo de pago generado correctamente.");
        } catch (ExcepcionDNIRegistrado e) {
            System.out.println(e.getMessage());
        }
    }

    public void asignarSalario(Scanner scanner, SistemaNominas sistemaNominas) {

        for (int i = 0; i < sistemaNominas.listaTrabajadores.size(); i++) {
            Trabajador t = sistemaNominas.listaTrabajadores.get(i);
            System.out.println((i + 1) + ". " + t.getNombres() + " " + t.getApellidos() + " (" + t.getDni() + ")");
        }

        System.out.print("Ingrese el DNI del trabajador para actualizar el sueldo base: ");
        String dni = scanner.nextLine();

        Trabajador trabajador;
        try {
            trabajador = buscarTrabajadorPorDni(dni);
            System.out.print("Ingrese el nuevo sueldo base: ");
            double nuevoSueldoBase = scanner.nextDouble();
            trabajador.setSueldoBase(nuevoSueldoBase);
            System.out.println("Sueldo base actualizado para " + trabajador.getNombres() + " " + trabajador.getApellidos() + ": " + nuevoSueldoBase);
        } catch (ExcepcionDNIRegistrado e) {
            System.out.println(e.getMessage());
        }
    }

    public void asignarBono(Scanner scanner, SistemaNominas sistemaNominas) {
        for (int i = 0; i < sistemaNominas.listaTrabajadores.size(); i++) {
            Trabajador t = sistemaNominas.listaTrabajadores.get(i);
            System.out.println((i + 1) + ". " + t.getNombres() + " " + t.getApellidos() + " (" + t.getDni() + ")");
        }

        System.out.print("Ingrese el DNI del trabajador para asignar o actualizar el bono: ");
        String dni = scanner.nextLine();

        Trabajador trabajador;
        try {
            trabajador = buscarTrabajadorPorDni(dni);
            System.out.print("Ingrese el monto del bono: ");
            double nuevoBono = scanner.nextDouble();
            trabajador.setBono(nuevoBono);
            System.out.println("Bono actualizado para " + trabajador.getNombres() + " " + trabajador.getApellidos() + ": " + nuevoBono);
        } catch (ExcepcionDNIRegistrado e) {
            System.out.println(e.getMessage());
        }
    }

    public void agregarDescuentos(Scanner scanner, SistemaNominas sistemaNominas) {
        for (int i = 0; i < sistemaNominas.listaTrabajadores.size(); i++) {
            Trabajador t = sistemaNominas.listaTrabajadores.get(i);
            System.out.println((i + 1) + ". " + t.getNombres() + " " + t.getApellidos() + " (" + t.getDni() + ")");
        }

        System.out.print("Ingrese el DNI del trabajador para actualizar los días faltos: ");
        String dni = scanner.nextLine();

        Trabajador trabajador;
        try {
            trabajador = sistemaNominas.buscarTrabajadorPorDni(dni);
            System.out.print("Ingrese la cantidad de días que faltó: ");
            int diasFaltos = scanner.nextInt();
            scanner.nextLine();

            if (diasFaltos < 0) {
                System.out.println("La cantidad de días faltos no puede ser negativa.");
                return;
            }

            trabajador.setDiasFaltos(diasFaltos);
            System.out.println("Días faltos actualizados para " + trabajador.getNombres() + " " + trabajador.getApellidos() + ": " + diasFaltos);
            
            double descuento = calcularDescuentos(trabajador);
            System.out.println("Descuento aplicado por días faltos: " + descuento);
        } catch (ExcepcionDNIRegistrado e) {
            System.out.println(e.getMessage());
        }
    }

}