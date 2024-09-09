public class CintaTransportadora extends Thread {
    private boolean enCinta;  // Indica si hay un producto en la cinta (true: hay producto, false: no)
    private Producto productoEnCinta;  // Producto actualmente en la cinta

    public CintaTransportadora() {
        this.enCinta = false;
    }

    // Método sincronizado para transportar un producto en la cinta
    public synchronized void transportar(Producto producto) {
        System.out.println("Transportando producto: " + producto.getTipo() + ". En la cinta hay " + (enCinta ? "1" : "0"));
        
        // Espera si ya hay un producto en la cinta
        while (enCinta) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restablecer el estado de interrupción
                System.out.println("Hilo interrumpido durante transportar");
            }
        }
        
        enCinta = true;
        productoEnCinta = producto;
        System.out.println("Producto en la cinta: " + productoEnCinta.getTipo());
        this.notifyAll();  // Notifica que hay un nuevo producto en la cinta
    }

    // Método sincronizado para retirar un producto de la cinta
    public synchronized Producto retirar() {
        System.out.println("Retirando producto de la cinta");
        
        // Espera si no hay producto en la cinta
        while (!enCinta) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restablecer el estado de interrupción
                System.out.println("Hilo interrumpido durante retirar");
            }
        }
        
        enCinta = false;
        Producto productoRetirado = productoEnCinta;
        productoEnCinta = null;  // Limpia la referencia al producto en la cinta
        this.notifyAll();  // Notifica que la cinta está vacía
        return productoRetirado;
    }
}
