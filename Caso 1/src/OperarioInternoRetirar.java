public class OperarioInternoRetirar extends Thread{
    private static DepositoDistribucion depositoDistribucion;
    private static DepositoProduccion depositoProduccion;
    private static CintaTransportadora cintaTransportadora;
    private int marcadosFinA;
    private int marcadosFinB;
    private boolean enOperacion;
    private int id;


    public OperarioInternoRetirar(DepositoDistribucion depositoDistribucion, CintaTransportadora cintaTransportadora, int pid) {
        this.depositoDistribucion = depositoDistribucion;
        this.cintaTransportadora = cintaTransportadora;
        this.marcadosFinA = 0;
        this.marcadosFinB = 0;
        this.enOperacion = true;
        this.id = pid;
    }

    @Override
    public void run() {
        while(enOperacion){
            cinta_depositoDist();
        }
    }

    public synchronized void cinta_depositoDist(){
        System.out.println("Operario Interno " +this.id + " de distribución comenzó a retirar de la cinta");
        Producto producto = cintaTransportadora.retirar();
        depositoDistribucion.almacenar(producto);
        System.out.println("Operario Interno " +this.id + " de distribución terminó de retirar de la cinta y almaceno en deposito de distribución");
        if (producto.getTipo().equals("fin_A")){
            marcadosFinA++;
        } 
        else if (producto.getTipo().equals("fin_B")){
            marcadosFinB++;
        }
        if (marcadosFinA == 2 && marcadosFinB == 2){
            enOperacion = false;
            System.out.println("Operario Interno " +this.id + " de distribución terminó de retirar de la cinta");
        }
    }
}
