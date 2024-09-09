public class CintaTransportadora extends Thread {
    private boolean enCinta;  // Indica si hay un producto en la cinta (true: hay producto, false: no)
    private Producto productoEnCinta;  // Producto actualmente en la cinta

    public CintaTransportadora() {
        this.enCinta = false;
    }

    // Método sincronizado para transportar un producto en la cinta
    public void transportar(Producto producto) {
        System.out.println("Transportando producto: " + producto.getTipo() + ". En la cinta hay " + (enCinta ? "1" : "0"));
        
        // Espera semi-activa si ya hay un producto en la cinta
        while (enCinta) {
            //System.out.println("Cinta ocupada, esperando para transportar...");
            Thread.yield();  // Ceder el control temporalmente al sistema operativo
        }
        
        enCinta = true;
        productoEnCinta = producto;

        System.out.println("Producto en la cinta: " + productoEnCinta.getTipo());
    }

    // Método sincronizado para retirar un producto de la cinta
    public Producto retirar() {
        System.out.println("Retirando producto de la cinta");
        // Espera semi-activa si no hay producto en la cinta
        while (!enCinta) {
            //System.out.println("Cinta vacía, esperando para retirar...");
            Thread.yield();  // Ceder el control temporalmente al sistema operativo
        }
        
        enCinta = false;
        Producto productoRetirado = productoEnCinta;
        productoEnCinta = null;  // Limpia la referencia al producto en la cinta
        System.out.println("Producto retirado de la cinta: " + productoRetirado.getTipo());
        return productoRetirado;
    }
}