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
        while (enOperacion) {

            while (!depositoProduccion.conProductos()) {
                System.out.println("Operario Interno de Producción a Cinta esperando a que haya productos en el depósito de producción para retirar...");
                Thread.yield();
                System.out.println("Operario Interno de Producción a Cinta ya puede retirar del depósito de producción");
            }
            Producto producto = depositoProduccion.retirar();

            // Transportar producto a la cinta
            cintaTransportadora.transportar(producto);

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
            }
        }
    }
}