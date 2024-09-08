public class CintaTransportadora {
    private Integer enCinta;
    private static Producto productoEnCinta;

    public CintaTransportadora() {
        this.enCinta = 0;
    }

    public synchronized void transportar(Producto producto) {
        while (enCinta == 1) {
            Thread.yield();
        }
        enCinta = 1;
        productoEnCinta = producto;
        this.notifyAll();
    }
    
    public synchronized Producto retirar() {
        while (enCinta == 0) {
            Thread.yield();
        }
        enCinta = 0;
        this.notifyAll();
        return productoEnCinta;

    }
    
}