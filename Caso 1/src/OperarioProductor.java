public class OperarioProductor implements Runnable {
    private String tipo;
    private DepositoProduccion depositoProduccion;
    private int cantidad;

    public OperarioProductor(String tipo, DepositoProduccion depositoProduccion, int cantidad) {
        this.tipo = tipo;
        this.depositoProduccion = depositoProduccion;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        for(int i = 0; i <= cantidad; i++){
            Producto producto = producir();
            almacenar(producto);
        }
        // Crear producto tipo FIN_A o FIN_B
        Producto productoFin = new Producto(tipo.equals("A") ? "FIN_A" : "FIN_B");
        almacenar(productoFin);  
        System.out.println("Operario Productor terminó de producir " + tipo);
    }
    
    public synchronized Producto producir(){
        return new Producto(tipo);
    }

    public synchronized void almacenar(Producto producto){
        while(depositoProduccion.darLleno()){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        depositoProduccion.almacenar(producto);
        notifyAll();
    }

}
