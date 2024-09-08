public class CintaTransportadora extends Thread {
    private Integer enCinta;
    private static Producto productoEnCinta;

    public CintaTransportadora() {
        this.enCinta = 0;
    }

    public synchronized void transportar(Producto producto) {
        System.out.println("Transportando producto: " + producto.getTipo() + ". En la cinta hay" + enCinta);
        while (enCinta == 1) {
            // try {
            //     wait(); //YIELD
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            Thread.yield();
        }
        enCinta = 1;
        productoEnCinta = producto;
        System.out.println("Producto en la cinta: " + productoEnCinta);
        this.notifyAll();
    }
    
    public synchronized Producto retirar() {
        System.out.println("Retirando producto de la cinta");
        while (enCinta == 0) {
            // try {
            //     wait(); //YIELD
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            Thread.yield();
        }
        enCinta = 0;
        this.notifyAll();
        return productoEnCinta;

    }
    
}