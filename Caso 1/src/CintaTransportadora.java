public class CintaTransportadora {
    private Integer enCinta;
    private Producto productoEnCinta;

    public CintaTransportadora() {
        this.enCinta = 0;
    }

    public synchronized void transportar(Producto producto) {
        while (enCinta == 1) {
            try {
                this.wait(); //YIELD()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        enCinta = 1;
        productoEnCinta = producto;
        this.notifyAll();
    }
    
    public synchronized Producto retirar() {
        while (enCinta == 0) {
            try {
                wait(); //YIELD()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        enCinta = 0;
        notifyAll();
        return productoEnCinta;

    }
    
}