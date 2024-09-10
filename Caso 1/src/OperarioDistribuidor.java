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

    public synchronized void extraer() {
        while (depositoDistribucion.getProductos().isEmpty()) {
            try {
                System.out.println("Operario Distribuidor " + this.id + " esperando a que haya productos en el depósito de distribución");
                this.wait();  // Espera hasta que haya productos disponibles
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restaura el estado de interrupción
                System.out.println("Hilo interrumpido durante retirar en depósito de distribución");
            }
        }
        if (depositoDistribucion.getProductos().get(0).getTipo().equals("fin_" + tipo) || depositoDistribucion.getProductos().get(0).getTipo().equals(tipo)) {
            Producto productoExtraido = depositoDistribucion.retirar();  // Retirar un producto del depósito
            if (productoExtraido.getTipo().equals("fin_" + tipo)) {
                enOperacion = false;
                System.out.println("Operario Distribuidor " + this.id + " terminó de distribuir " + tipo);
            }
        }
        notifyAll();
        
    }
}