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
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restaura el estado de interrupción
                System.out.println("Hilo interrumpido durante almacenar");
            }
        }
        productos.add(producto);
        System.out.println("Almacenados en: " + productos.size());
        System.out.println("lista:" + productos);
        this.notifyAll();
    }


    public synchronized Producto retirar(){
        System.out.println("En metodo RETIRAR almacenados: " + productos.size() + " productos");

        Producto prod = productos.get(0);
        productos.remove(0);
        //almacenados--;
        System.out.println("Termino Retirar, Almacenados en: " + productos.size());
        System.out.println("Tipo prod retirado: " + prod.getTipo());
        this.notifyAll();
        return prod;
    }
    


    public synchronized Boolean conProductos(){
        System.out.println("Deposito de Produccion tiene " + productos + " productos");
        return !productos.isEmpty();
    }
    
}