public class DepositoDistribucion {
    private int Almacenados;
    private int capDepDist;
    private Producto[] productos;

    public DepositoDistribucion(int Almacenados, int capDepDist, Producto[] productos) {
        this.Almacenados = 0;
        this.capDepDist = capDepDist;
    }

    public Boolean darLleno() {
        return Almacenados == capDepDist;
    }
}
