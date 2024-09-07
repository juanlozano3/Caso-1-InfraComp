public class OperarioProductor extends Thread{
    private String tipo;
    private static DepositoProduccion depositoProduccion;
    private int numProductos;
    private int id;

    public OperarioProductor(String tipo, DepositoProduccion depositoProduccion, int numProductos, int pid) {
        this.tipo = tipo;
        this.depositoProduccion = depositoProduccion;
        this.numProductos = numProductos;
        this.id = pid;
    }

    @Override
    public void run() {
        System.out.println("Operario Productor " + this.id +" comenzó a producir " + tipo);
        for(int i = 0; i <= numProductos; i++){
            Producto producto = producir();
            almacenar(producto);
            System.out.println("Operario Productor " + this.id +" produjo " + tipo);
        }
        // Crear producto tipo FIN_A o FIN_B
        Producto productoFin = new Producto(tipo.equals("A") ? "fin_A" : "fin_B");
        almacenar(productoFin);  
        System.out.println("Operario Productor " + this.id +"terminó de producir " + tipo);
    }
    
    public synchronized Producto producir(){
        return new Producto(tipo);
    }

    public void almacenar(Producto producto){
        depositoProduccion.almacenar(producto);
    }

}
