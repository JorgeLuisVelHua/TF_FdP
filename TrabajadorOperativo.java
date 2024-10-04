package TrabajoFinal_FdP2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class TrabajadorOperativo extends Trabajador {

    public TrabajadorOperativo(String nombres, String apellidos, String dni, String domicilio, String correo, String fechaIngreso, double sueldoBase, double bono, int horasExtra, int diasFaltos) {
        super(nombres, apellidos, dni, domicilio, correo, fechaIngreso, sueldoBase, bono, horasExtra, diasFaltos);
    }

    @Override
    public double calcularSueldoNeto() {
        double bonoFinal = getBono() +getSueldoBase();
        double descuento = (getSueldoBase()/24)*getDiasFaltos() ;
        return bonoFinal - descuento;
    }

    @Override
    public String toString() {
        return "Trabajador Operativo\n" + super.mostrarDatosSinSueldoNeto() + "\nSueldo Neto: " + calcularSueldoNeto();
    }
}