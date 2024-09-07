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
        Almacenados++;
        productos.add(producto);
        this.notifyAll();
    }

    public Boolean darLleno() {
        return Almacenados == capDepDist;
    }

    public synchronized Producto retirar() {
        while (Almacenados == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Producto producto = productos.get(Almacenados - 1);
        productos.remove(Almacenados - 1);
        Almacenados--;
        this.notifyAll();
        return producto;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public int getAlmacenados() {
        return Almacenados;
    }
}
