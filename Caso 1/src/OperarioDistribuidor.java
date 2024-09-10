public class OperarioDistribuidor extends Thread {
    private String tipo;  
    private static DepositoDistribucion depositoDistribucion;  
    private final int numProductos;
    private boolean enOperacion;
    private final int id;

    public OperarioDistribuidor(String tipo, DepositoDistribucion depositoDistribucion, int numProductos, int pid) {
        this.tipo = tipo;
        this.depositoDistribucion = depositoDistribucion;
        this.numProductos = numProductos;
        this.enOperacion = true;
        this.id = pid;
    }

    @Override
    public void run() {
        while (enOperacion) {
            extraer();
        }
    }

    public void extraer() {
        System.out.println("Operario Distribuidor " + this.id + " comenzó a extraer del depósito de distribución");
        Producto productoExtraido = depositoDistribucion.retirar(tipo);  // Este método espera hasta que haya un producto
        System.out.println("Operario Distribuidor " + this.id + " con tipo " + tipo + " terminó de extraer del depósito de distribución y obtuvo: " + productoExtraido.getTipo());
        if (productoExtraido.getTipo().equals("fin_" + tipo)) {
            enOperacion = false;
            System.out.println("Operario Distribuidor " + this.id + " con tipo " + tipo + " terminó su operación.");
        }
    }
}