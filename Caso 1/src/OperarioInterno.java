public class OperarioInterno {
    private DepositoDistribucion depositoDistribucion;
    private DepositoProduccion depositoProduccion;
    private CintaTransportadora cintaTransportadora;

    public OperarioInterno(DepositoDistribucion depositoDistribucion, DepositoProduccion depositoProduccion, CintaTransportadora cintaTransportadora) {
        this.depositoDistribucion = depositoDistribucion;
        this.depositoProduccion = depositoProduccion;
        this.cintaTransportadora = cintaTransportadora;
    }

    public void depProd_cinta(){
        while(depositoProduccion.vacio()){
            Producto producto = depositoProduccion.retirar();       
        }
    }
}
