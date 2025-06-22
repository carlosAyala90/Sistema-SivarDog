package sistemaSivarDog;

public class Pago {
 private final Cliente cliente;
 private final double montoTotal;
 private final metodoPago metodoDePago;
 private estadoPago estadoDePago;

 public Pago(Cliente cliente, double montoTotal, metodoPago metodoDePago) {
     if (montoTotal <= 0.0) {
         throw new IllegalArgumentException("El monto debe ser mayor que cero.");
     } else {
         this.cliente = cliente;
         this.montoTotal = montoTotal;
         this.metodoDePago = metodoDePago;
         this.estadoDePago = Pago.estadoPago.PENDIENTE;
     }
 }
 public void registrarPago() {
     if (this.estadoDePago == Pago.estadoPago.PENDIENTE) {
         this.estadoDePago = Pago.estadoPago.PAGADO;
     } else {
         System.out.println("El estado ya está registrado");
     }
 }


 public double getMontoTotal() {
     return this.montoTotal;
 }

 public metodoPago getMetodoDePago() {
     return this.metodoDePago;
 }

 public estadoPago getEstadoDePago() {
     return this.estadoDePago;
 }

 public Cliente getCliente() {
     return cliente;
 }

 public  enum metodoPago {
     EFECTIVO,
     TARJETA
 }

 public  enum estadoPago {
     PENDIENTE,
     PAGADO
 }
}