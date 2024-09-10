import java.util.ArrayList;
public class DepositoProduccion {
    //private int almacenados;
    private int capDepProd;
    private ArrayList <Producto> productos;

    public DepositoProduccion(int capDepProd) {
        //this.almacenados = 0;
        this.capDepProd = capDepProd;
        this.productos = new ArrayList<Producto>();
    }

    public synchronized Boolean darLleno() {
        return productos.size() == capDepProd;
    }


    public synchronized void almacenar(Producto producto) {
        while(darLleno()){
            try {
                System.out.println("Deposito de Produccion lleno, esperando a que se retiren productos");
                this.wait();
                System.out.println("Hilo despertado para almacenar en Deposito de Produccion");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restaura el estado de interrupción
                System.out.println("Hilo interrumpido durante almacenar");
            }
        }
        productos.add(producto);
        System.out.println("Cantidad de productos almacenados: " + productos.size());
        System.out.println("lista:" + productos);
        this.notifyAll();
    }


    public synchronized Producto retirar(){

        Producto prod = productos.get(0);
        productos.remove(0);
        System.out.println("Se retiro el producto: " + prod.getTipo() + ", cantidad de productos Almacenados en: " + productos.size());
        this.notifyAll();
        return prod;
    }
    


    public synchronized Boolean conProductos(){
        return !productos.isEmpty();
    }
    
}