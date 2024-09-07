import java.util.ArrayList;
public class DepositoProduccion {
    private int almacenados;
    private int capDepProd;
    private ArrayList <Producto> productos;

    public DepositoProduccion(int Almacenados, int capDepProd) {
        this.almacenados = 0;
        this.capDepProd = capDepProd;
        this.productos = new ArrayList<Producto>();
    }

    public Boolean darLleno() {
        return almacenados == capDepProd;
    }
    public synchronized void almacenar(Producto producto) {
        while(darLleno()){
            try {
                this.wait();
            } catch (InterruptedException e) {}
        }
        almacenados++;
        productos.add(producto);
        this.notifyAll();
    }
    public synchronized Producto retirar(){
        while (almacenados == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        almacenados--;
        Producto prod = productos.get(almacenados);
        productos.remove(almacenados);
        this.notifyAll();
        return prod;
    }
    
    public Boolean conProductos(){
        return almacenados > 0;
    }
    
}
