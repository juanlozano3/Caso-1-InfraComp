
import java.util.ArrayList;

public class DepositoDistribucion {
    private final int capDepDist;  // Capacidad máxima del depósito de distribución
    private final ArrayList<Producto> productos;  // Lista de productos en el depósito

    public DepositoDistribucion(int capDepDist) {
        this.capDepDist = capDepDist;
        this.productos = new ArrayList<>();
    }

    // Método sincronizado para almacenar un producto en el depósito
    public synchronized void almacenar(Producto producto) {
        productos.add(producto);  // Almacena el producto
        System.out.println("Producto almacenado en depósito de distribución. Total almacenados: " + productos.size());
        this.notifyAll();  // Notifica a los hilos en espera que hay productos disponibles
    }

    // Método sincronizado para verificar si el depósito está lleno
    public synchronized boolean darLleno() {
        return productos.size() == capDepDist;
    }

    // Método sincronizado para retirar un producto del depósito
    public synchronized Producto retirar(String tipo) {
        //System.out.println("En método RETIRAR de depósito de distribución. Almacenados: " + productos.size());
        while (productos.isEmpty() || !productos.get(0).getTipo().contains(tipo)) {
            try {
                System.out.println("Depósito de distribución vacío o no es el tipo. Esperando a que haya productos...");
                this.wait();  // Espera hasta que haya productos disponibles
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restaura el estado de interrupción
                System.out.println("Hilo interrumpido durante retirar en depósito de distribución");
            }
        }
        
        Producto producto = productos.remove(0);  // Retira el primer producto de la lista
        System.out.println("Producto retirado de depósito de distribución. Total almacenados: " + productos.size());
        this.notifyAll();  // Notifica a los hilos en espera que hay espacio disponible
        return producto;
    }

    // Método para obtener la lista de productos
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    // Método sincronizado para obtener el número de productos almacenados
    public synchronized int getAlmacenados() {
        return productos.size();
    }

    public int getCapDepDist() {
        return capDepDist;
    }
}
