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
        Producto productoExtraido = depositoDistribucion.retirar();  // Retirar un producto del depósito
        //Tamañp de la lista de productos en el depósito de distribución
        System.out.println("Tamaño de la lista de productos en el depósito de distribución: " + depositoDistribucion.getAlmacenados());
        if (productoExtraido.getTipo().equals(tipo)) {
            System.out.println("Operario Distribuidor " + this.id + " comenzó a distribuir " + tipo);
            System.out.println("Operario Distribuidor " + this.id + " retiró del depósito de distribución " + tipo);
        }
        
        // Verificar si se ha encontrado el producto de terminación (FIN_A o FIN_B)
        if (productoExtraido.getTipo().equals("fin_" + tipo)) {
            enOperacion = false;
            System.out.println("Operario Distribuidor " + this.id + " terminó de distribuir " + tipo);
        }
    }
}