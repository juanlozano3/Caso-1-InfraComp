public class OperarioInternoCinta_distribucion extends Thread {
    private static DepositoDistribucion depositoDistribucion;  
    private static CintaTransportadora cintaTransportadora;  
    private int marcadosFinA;
    private int marcadosFinB;
    private boolean enOperacion;
    private final int id;

    public OperarioInternoCinta_distribucion(DepositoDistribucion depositoDistribucion, CintaTransportadora cintaTransportadora, int pid) {
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
        // Retirar el producto de la cinta
        
        Producto producto = cintaTransportadora.retirar();  // Este método espera hasta que haya un producto

        while (depositoDistribucion.getProductos().size() == depositoDistribucion.getCapDepDist()) {
            System.out.println("Operario Interno de Cinta a Distribución esperando a que haya espacio en el depósito de distribución para almacenar...");
            Thread.yield();  // Cede el control temporalmente al sistema operativo
            System.out.println("Operario Interno de Cinta a Distribución ya puede almacenar en el depósito de distribución...");
        }
        // Almacenar el producto en el depósito de distribución
        depositoDistribucion.almacenar(producto);

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