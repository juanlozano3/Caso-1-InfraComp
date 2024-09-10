public class OperarioDistribuidor extends Thread {
    private String tipo;  
    private static DepositoDistribucion depositoDistribucion;  
    private final int numProductos;
    private boolean enOperacion;
    private final int id;
    private static int contador = 0;

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
        Producto productoExtraido = depositoDistribucion.retirar(tipo);  // Este método espera hasta que haya un producto
        contador++;
        if (productoExtraido.getTipo().equals("fin_" + tipo)) {
            enOperacion = false;
            System.out.println("Operario Distribuidor " + this.id + " con tipo " + tipo + " terminó su operación.");
        }
    }

    public static int getContador() {
        return contador;
    }
}