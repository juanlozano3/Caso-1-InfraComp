import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Ingrese el numero de productos a producir: ");
        int numProductos = Integer.parseInt(myObj.nextLine());  // Read user input
        System.out.println("Ingrese la capacidad del Deposito de Produccion");  // Output user input
        int capDepProd = Integer.parseInt(myObj.nextLine());  // Read user input
        System.out.println("Ingrese la capacidad del Deposito de Distribucion");  // Output user input
        int capDepDist = Integer.parseInt(myObj.nextLine());  // Read user input

        DepositoProduccion depositoProduccion = new DepositoProduccion(0, capDepProd);
        DepositoDistribucion depositoDistribucion = new DepositoDistribucion(0, capDepDist);
        CintaTransportadora cintaTransportadora = new CintaTransportadora();


        OperarioProductor operarioProductorA = new OperarioProductor("A", depositoProduccion, numProductos, 1);
        OperarioProductor operarioProductorA2 = new OperarioProductor("A", depositoProduccion, numProductos, 2);
        OperarioProductor operarioProductorB = new OperarioProductor("B", depositoProduccion, numProductos, 1);
        OperarioProductor operarioProductorB2 = new OperarioProductor("B", depositoProduccion, numProductos, 2);


        OperarioInternoDepositar operarioInternoDepositar = new OperarioInternoDepositar(depositoProduccion, cintaTransportadora, 1);
        OperarioInternoRetirar operarioInternoRetirar = new OperarioInternoRetirar(depositoDistribucion, cintaTransportadora, 2);

        OperarioDistribuidor operarioDistribuidorA = new OperarioDistribuidor("A", depositoDistribucion, numProductos, 1);
        OperarioDistribuidor operarioDistribuidorA2 = new OperarioDistribuidor("A", depositoDistribucion, numProductos, 2);
        OperarioDistribuidor operarioDistribuidorB = new OperarioDistribuidor("B", depositoDistribucion, numProductos,1);
        OperarioDistribuidor operarioDistribuidorB2 = new OperarioDistribuidor("B", depositoDistribucion, numProductos, 2);

        // Thread threadOperarioProductorA = new Thread(operarioProductorA);
        // Thread threadOperarioProductorA2 = new Thread(operarioProductorA2);
        // Thread threadOperarioProductorB = new Thread(operarioProductorB);
        // Thread threadOperarioProductorB2 = new Thread(operarioProductorB2);

        // Thread threadOperarioInternoDepositar = new Thread(operarioInternoDepositar);
        // Thread threadOperarioInternoRetirar = new Thread(operarioInternoRetirar);

        // Thread threadOperarioDistribuidorA = new Thread(operarioDistribuidorA);
        // Thread threadOperarioDistribuidorA2 = new Thread(operarioDistribuidorA2);
        // Thread threadOperarioDistribuidorB = new Thread(operarioDistribuidorB);
        // Thread threadOperarioDistribuidorB2 = new Thread(operarioDistribuidorB2);

        operarioProductorA.start();
        operarioProductorA2.start();
        operarioProductorB.start();
        operarioProductorB2.start();

        operarioInternoDepositar.start();
        operarioInternoRetirar.start();

        operarioDistribuidorA.start();
        operarioDistribuidorA2.start();
        operarioDistribuidorB.start();
        operarioDistribuidorB2.start();

        }

}
