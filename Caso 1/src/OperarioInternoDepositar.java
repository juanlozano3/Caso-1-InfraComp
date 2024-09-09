public class OperarioInternoDepositar extends Thread {
    private static DepositoProduccion depositoProduccion;
    private static CintaTransportadora cintaTransportadora;
    private int marcadosFinA;
    private int marcadosFinB;
    private boolean enOperacion;
    private int id;

    public OperarioInternoDepositar(DepositoProduccion depositoProduccion, CintaTransportadora cintaTransportadora, int pid) {
        this.depositoProduccion = depositoProduccion;
        this.cintaTransportadora = cintaTransportadora;
        this.marcadosFinA = 0;
        this.marcadosFinB = 0;
        this.enOperacion = true;
        this.id = pid;
    }

    @Override
    public void run() {
        while (enOperacion) {
            depProd_cinta();
        }
    }

    public void depProd_cinta() {
        // Mientras haya productos en el depósito
        while (enOperacion && depositoProduccion.conProductos()) {
            System.out.println("Operario Interno " + this.id + " de producción comenzó a retirar de produccion y a depositar en la cinta");

            Producto producto = depositoProduccion.retirar();
            System.out.println("Tipo prodsssss retirado: " + producto.getTipo());

            // Transportar producto a la cinta
            cintaTransportadora.transportar(producto);
            System.out.println("Operario Interno " + this.id + " de producción terminó de depositar en la cinta");

            // Verificar el tipo de producto para marcar fin_A y fin_B
            if (producto.getTipo().equals("fin_A")) {
                marcadosFinA++;
            } else if (producto.getTipo().equals("fin_B")) {
                marcadosFinB++;
            }

            // Condición para detener la operación
            if (marcadosFinA == 2 && marcadosFinB == 2) {
                enOperacion = false;
                System.out.println("Operario Interno " + this.id + " de producción terminó su operación.");
                break;
            }
        }

        // Si no hay productos disponibles
        if (!depositoProduccion.conProductos() && enOperacion) {
            System.out.println("Operario Interno " + this.id + " no encontró productos, cediendo el control temporalmente.");
            Thread.yield();  // Ceder el control temporalmente si no hay productos
        }
    }
}