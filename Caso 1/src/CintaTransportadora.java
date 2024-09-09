public class CintaTransportadora extends Thread {
    private boolean enCinta;  // Indica si hay un producto en la cinta (true: hay producto, false: no)
    private Producto productoEnCinta;  // Producto actualmente en la cinta

    public CintaTransportadora() {
        this.enCinta = false;
    }

    // Método sincronizado para transportar un producto en la cinta
    public synchronized void transportar(Producto producto) {
        System.out.println("Transportando producto: " + producto.getTipo() + ". En la cinta hay " + (enCinta ? "1" : "0"));
        productoEnCinta = producto;
        enCinta = true;

        System.out.println("Producto en la cinta: " + productoEnCinta.getTipo());
    }

    // Método sincronizado para retirar un producto de la cinta
    public synchronized Producto retirar() {
        Producto productoRetirado = productoEnCinta;
        enCinta = false;
        productoEnCinta = null;
        System.out.println("Producto retirado de la cinta: " + productoRetirado.getTipo());
        notifyAll();  // Notifica que se retiró un producto
        return productoRetirado;
    }


    public synchronized boolean enCinta() {
        return enCinta;
    }
}
