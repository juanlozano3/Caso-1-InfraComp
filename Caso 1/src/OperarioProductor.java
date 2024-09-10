public class OperarioProductor extends Thread{
    private String tipo;
    private static DepositoProduccion depositoProduccion;
    private int numProductos;
    private int id;
    private static int cantidadProducidos;

    public OperarioProductor(String tipo, DepositoProduccion depositoProduccion, int numProductos, int pid) {
        this.tipo = tipo;
        this.depositoProduccion = depositoProduccion;
        this.numProductos = numProductos;
        this.id = pid;
        this.cantidadProducidos = 0;
    }

    @Override
    public void run() {

        for(int i = 0; i < numProductos; i++){
            Producto producto = producir();
            cantidadProducidos++;
            almacenar(producto);
        }

        // Crear producto tipo FIN_A o FIN_B
        Producto productoFin = new Producto(tipo.equals("A") ? "fin_A" : "fin_B");
        almacenar(productoFin);
        cantidadProducidos++;
        System.out.println("Operario Productor " + this.id +" terminó de producir " + tipo + " con " + cantidadProducidos + " productos");
    }
    
    public Producto producir(){
        return new Producto(tipo);
    }

    public void almacenar(Producto producto){
        depositoProduccion.almacenar(producto);
    }

    public static int getCantidadProducidos() {
        return cantidadProducidos;
    }

}