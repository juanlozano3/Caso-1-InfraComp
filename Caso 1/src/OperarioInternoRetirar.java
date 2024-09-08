public class OperarioInternoRetirar extends Thread {
    private static DepositoDistribucion depositoDistribucion;
    private static CintaTransportadora cintaTransportadora;
    private int marcadosFinA;
    private int marcadosFinB;
    private boolean enOperacion;
    private int id;

    public OperarioInternoRetirar(DepositoDistribucion depositoDistribucion, CintaTransportadora cintaTransportadora, int pid) {
        this.depositoDistribucion = depositoDistribucion;
        this.cintaTransportadora = cintaTransportadora;
        this.marcadosFinA = 0;
        this.marcadosFinB = 0;
        this.enOperacion = true;
        this.id = pid;
    }

    @Override
    public void run() {
        while (enOperacion) {
            cinta_depositoDist();
        }
    }

    public void cinta_depositoDist() {
        System.out.println("Operario Interno " + this.id + " de distribución comenzó a retirar de la cinta");

        // Intentar retirar el producto de la cinta
        Producto producto = cintaTransportadora.retirar();

        // Si no hay producto disponible, ceder el control temporalmente
        while (producto == null) {
            System.out.println("Operario Interno " + this.id + " cediendo el control temporalmente con yield() porque no hay productos en la cinta.");
            Thread.yield(); // Ceder el control temporalmente
            producto = cintaTransportadora.retirar(); // Intentar retirar nuevamente
        }

        depositoDistribucion.almacenar(producto);
        System.out.println("Operario Interno " + this.id + " de distribución terminó de retirar de la cinta y almacenó en el depósito de distribución");

        // Verificar el tipo de producto y contar fin_A y fin_B
        if (producto.getTipo().equals("fin_A")) {
            marcadosFinA++;
        } else if (producto.getTipo().equals("fin_B")) {
            marcadosFinB++;
        }

        // Condición para detener la operación
        if (marcadosFinA == 2 && marcadosFinB == 2) {
            enOperacion = false;
            System.out.println("Operario Interno " + this.id + " de distribución terminó su operación.");
        }
    }
}
