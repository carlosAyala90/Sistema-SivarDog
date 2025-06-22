package sistemaSivarDog;

public class Tratamiento {
    protected Citas cita;
    private final String descripcion;
    private final double costo;
    protected int duracionDias;
    protected boolean enCurso;
    private  Pago pago;
    private boolean pagado = false;

    public Tratamiento(Citas cita, String descripcion, double costo, int duracionDias) {
        if (costo < (double)0.0F) {
            throw new IllegalArgumentException("El costo no puede ser negativo.");
        } else if (duracionDias <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor que cero.");
        } else {
            this.cita = cita;
            this.descripcion = descripcion;
            this.costo = costo;
            this.duracionDias = duracionDias;
            this.enCurso = false;
        }
    }

    public void marcarComoPagado() {
        this.pagado = true;
    }
    public boolean estaPagado() {
        return this.pagado;
    }


    public String getDescripcion() {
        return this.descripcion;
    }

    public double getCosto() {
        return this.costo;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public String getEstadoPago() {
        return (pago != null && pago.getEstadoDePago() == Pago.estadoPago.PAGADO) ? "PAGADO" : "PENDIENTE";
    }

}
