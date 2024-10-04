package TrabajoFinal_FdP2;

public abstract class Trabajador {
    private String nombres;
    private String apellidos;
    private String dni;
    private String domicilio;
    private String correo;
    private String fechaIngreso;
    private double sueldoBase;
    private double bono;
    private int horasExtra;
    private int diasFaltos;

    public Trabajador(String nombres, String apellidos, String dni, String domicilio, String correo, String fechaIngreso, double sueldoBase, double bono, int horasExtra, int diasFaltos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.domicilio = domicilio;
        this.correo = correo;
        this.fechaIngreso = fechaIngreso;
        this.sueldoBase = sueldoBase;
        this.bono = bono;
        this.horasExtra = horasExtra;
        this.diasFaltos = diasFaltos;
    }

    // Getters
    public String getNombres() { return nombres; }

    public String getApellidos() { return apellidos; }

    public String getDni() { return dni; }

    public double getSueldoBase() { return sueldoBase; }

    public double getBono() { return bono; }

    public int getHorasExtra() { return horasExtra; }

    public int getDiasFaltos() { return diasFaltos; }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setBono(double bono) {
        this.bono = bono;
    }

    public void setDiasFaltos(int diasFaltos) {
        this.diasFaltos = diasFaltos;
    }

    public abstract double calcularSueldoNeto();

    public String mostrarDatosSinSueldoNeto() {
        return "DNI: " + dni +
                "\nNombres: " + nombres +
                "\nApellidos: " + apellidos +
                "\nDomicilio: " + domicilio +
                "\nCorreo: " + correo +
                "\nFecha de Ingreso: " + fechaIngreso +
                "\nSueldo Base: " + sueldoBase +
                "\nBono: " + bono +
                "\nHoras Extra: " + horasExtra +
                "\nDías Faltos: " + diasFaltos;
    }
}