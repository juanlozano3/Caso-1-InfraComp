public class OperarioInterno {
    private DepositoDistribucion depositoDistribucion;
    private DepositoProduccion depositoProduccion;
    private CintaTransportadora cintaTransportadora;

    public OperarioInterno(DepositoDistribucion depositoDistribucion, DepositoProduccion depositoProduccion, CintaTransportadora cintaTransportadora) {
        this.depositoDistribucion = depositoDistribucion;
        this.depositoProduccion = depositoProduccion;
        this.cintaTransportadora = cintaTransportadora;
    }

    public synchronized void depProd_cinta(){
        while(depositoProduccion.conProductos()){
            Producto producto = depositoProduccion.retirar();
            
        }
    }

}
