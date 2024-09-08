import java.util.ArrayList;

public class DepositoDistribucion {
    private int Almacenados;
    private int capDepDist;
    private ArrayList<Producto> productos;

    public DepositoDistribucion(int Almacenados, int capDepDist) {
        this.Almacenados = 0;
        this.capDepDist = capDepDist;
        this.productos = new ArrayList<Producto>();
    }

    public synchronized void almacenar(Producto producto) {
        while (Almacenados == capDepDist) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productos.add(producto);
        Almacenados++;
        this.notifyAll();
    }

    public Boolean darLleno() {
        return Almacenados == capDepDist;
    }

    public synchronized Producto retirar() {
        System.out.println("En Metodo Retirar Deposito Distribucion, Almacenados: " + Almacenados);
        while (Almacenados == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Producto producto = productos.get(0);
        productos.remove(0);
        Almacenados--;
        this.notifyAll();
        return producto;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public synchronized int getAlmacenados() {
        //System.out.println("Deposito de Distribucion tiene " + Almacenados + " productos");
        return Almacenados;
    }
}
