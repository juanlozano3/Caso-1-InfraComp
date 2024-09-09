public class OperarioInternoRetirar extends Thread {
    private static DepositoDistribucion depositoDistribucion;  
    private static CintaTransportadora cintaTransportadora;  
    private int marcadosFinA;
    private int marcadosFinB;
    private boolean enOperacion;
    private final int id;

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

        // Retirar el producto de la cinta
        Producto producto = cintaTransportadora.retirar();  // Este método espera hasta que haya un producto

        // Almacenar el producto en el depósito de distribución
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
