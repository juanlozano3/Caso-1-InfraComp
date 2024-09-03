public class CintaTransportadora {
    private Integer enCinta = 0;

    public CintaTransportadora(Integer enCinta) {
        this.enCinta = enCinta;
    }

    public synchronized void poner(Producto producto) {
        while (enCinta == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        enCinta = 1;
        notifyAll();
    }
    
    public synchronized void sacar() {
        while (enCinta == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        enCinta = 0;
        notifyAll();
    }
    
}