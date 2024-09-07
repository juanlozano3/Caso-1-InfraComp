public class OperarioDistribuidor extends Thread {
    private String tipo;
    private static DepositoDistribucion depositoDistribucion;
    private int numProductos;
    private boolean enOperacion;
    private int id;

    public OperarioDistribuidor(String tipo, DepositoDistribucion depositoDistribucion, int numProductos, int pid) {
        this.tipo = tipo;
        this.depositoDistribucion = depositoDistribucion;
        this.numProductos = numProductos;
        this.enOperacion = true;
        this.id = pid;
    }

    @Override
    public void run() {
        while (enOperacion) {
            extraer();
        }
    }

    public void extraer() {
        if (depositoDistribucion.getAlmacenados() > 0) {
            if ((depositoDistribucion.getProductos().get(depositoDistribucion.getAlmacenados()-1)).getTipo().equals(tipo)|| (depositoDistribucion.getProductos().get(depositoDistribucion.getAlmacenados()-1)).getTipo().equals("fin_"+tipo)) {
                System.out.println("Operario Distribuidor " + this.id + " comenzó a distribuir " + tipo);
                Producto productoExtraido = depositoDistribucion.retirar();
                System.out.println("Operario Distribuidor " + this.id + " retiro del deposito de distribucion " + tipo);
                if (productoExtraido.getTipo().equals("fin_A") || productoExtraido.getTipo().equals("fin_B")) {
                    enOperacion = false;
                    System.out.println("Operario Distribuidor " + this.id + " terminó de distribuir " + tipo);
                }
            }
        }
    }
}
