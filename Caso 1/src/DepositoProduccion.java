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
        productos.add(producto);
        almacenados++;
        System.out.println("Almacenados en: " + almacenados);
        System.out.println("lista:" + productos);
        this.notifyAll();
    }


    public synchronized Producto retirar(){
        System.out.println("En metodo RETIRAR almacenados: " + almacenados + " productos");
        while (almacenados == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Producto prod = productos.get(0);
        productos.remove(0);
        almacenados--;
        System.out.println("Termino Retirar, Almacenados en: " + almacenados);
        this.notifyAll();
        return prod;
    }
    


    public synchronized Boolean conProductos(){
        System.out.println("Deposito de Produccion tiene " + almacenados + " productos");
        return almacenados > 0;
    }
    
}
