import java.util.ArrayList;
public class DepositoProduccion {
    private int almacenados;
    private int capDepProd;
    private ArrayList <Producto> productos;

    public DepositoProduccion(int Almacenados, int capDepProd, Producto[] productos) {
        this.almacenados = 0;
        this.capDepProd = capDepProd;
    }

    public Boolean darLleno() {
        return almacenados == capDepProd;
    }
    public void almacenar(Producto producto) {
        almacenados++;
        productos.add(producto);
    }
    public Producto retirar(){
        almacenados--;
        Producto prod = productos.get(0);
        productos.remove(0);
        return prod;
    }
    
    public Boolean conProductos(){
        return almacenados > 0;
    }
    
}
