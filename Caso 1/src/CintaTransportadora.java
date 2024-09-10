public class CintaTransportadora extends Thread {
    private boolean enCinta;  // Indica si hay un producto en la cinta (true: hay producto, false: no)
    private Producto productoEnCinta;  // Producto actualmente en la cinta

    public CintaTransportadora() {
        this.enCinta = false;
    }

    // Método sincronizado para transportar un producto en la cinta
    public void transportar(Producto producto) {
        
        // Espera semi-activa si ya hay un producto en la cinta
        while (enCinta) {
            System.out.println("Operario Interno Producción a Cinta esperando para transportar debido a cinta ocupada...");
            Thread.yield();  // Ceder el control temporalmente al sistema operativo
            System.out.println("Operario Interno Producción a Cinta ya puede ingresar el producto a la cinta...");
        }
        
        productoEnCinta = producto;
        enCinta = true;
        System.out.println("Tipo del producto ingresado a la cinta: " + productoEnCinta.getTipo());
    }

    // Método sincronizado para retirar un producto de la cinta
    public Producto retirar() {
        // Espera semi-activa si no hay producto en la cinta
        while (!enCinta) {
            System.out.println("Operario Interno Cinta a Distribución esperando para retirar de la cinta debido a cinta vacía...");
            Thread.yield();  // Ceder el control temporalmente al sistema operativo
            System.out.println("Operario Interno Cinta a Distribución ya puede retirar un producto de la cinta...");
        }
        
        enCinta = false;
        Producto productoRetirado = productoEnCinta;
        productoEnCinta = null;  // Limpia la referencia al producto en la cinta
        System.out.println("Tipo del producto retirado de la cinta: " + productoRetirado.getTipo());
        return productoRetirado;
    }
}