import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Ingrese el número de productos a producir: ");
        int numProductos = Integer.parseInt(myObj.nextLine());  // Read user input
        System.out.println("Ingrese la capacidad del Depósito de Producción");  
        int capDepProd = Integer.parseInt(myObj.nextLine());  
        System.out.println("Ingrese la capacidad del Depósito de Distribución");  
        int capDepDist = Integer.parseInt(myObj.nextLine());

        // Cerrar el scanner después de la entrada del usuario
        myObj.close();

        // Inicialización de depósitos y cinta transportadora
        DepositoProduccion depositoProduccion = new DepositoProduccion(capDepProd);
        DepositoDistribucion depositoDistribucion = new DepositoDistribucion(capDepDist);
        CintaTransportadora cintaTransportadora = new CintaTransportadora();

        // Inicialización de operarios productores
        OperarioProductor operarioProductorA = new OperarioProductor("A", depositoProduccion, numProductos, 1);
        OperarioProductor operarioProductorA2 = new OperarioProductor("A", depositoProduccion, numProductos, 2);
        OperarioProductor operarioProductorB = new OperarioProductor("B", depositoProduccion, numProductos, 1);
        OperarioProductor operarioProductorB2 = new OperarioProductor("B", depositoProduccion, numProductos, 2);

        // Inicialización de operarios internos
        OperarioInternoDepositar operarioInternoDepositar = new OperarioInternoDepositar(depositoProduccion, cintaTransportadora, 1);
        OperarioInternoRetirar operarioInternoRetirar = new OperarioInternoRetirar(depositoDistribucion, cintaTransportadora, 2);

        // Inicialización de operarios distribuidores
        OperarioDistribuidor operarioDistribuidorA = new OperarioDistribuidor("A", depositoDistribucion, numProductos, 1);
        OperarioDistribuidor operarioDistribuidorA2 = new OperarioDistribuidor("A", depositoDistribucion, numProductos, 2);
        OperarioDistribuidor operarioDistribuidorB = new OperarioDistribuidor("B", depositoDistribucion, numProductos, 1);
        OperarioDistribuidor operarioDistribuidorB2 = new OperarioDistribuidor("B", depositoDistribucion, numProductos, 2);

        // Iniciar los hilos de los operarios
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

        // Unir los hilos para asegurar que el main espere a que todos terminen
        operarioProductorA.join();
        operarioProductorA2.join();
        operarioProductorB.join();
        operarioProductorB2.join();

        operarioInternoDepositar.join();
        operarioInternoRetirar.join();

        operarioDistribuidorA.join();
        operarioDistribuidorA2.join();
        operarioDistribuidorB.join();
        operarioDistribuidorB2.join();

        // Confirmación de que todos los hilos han terminado
        System.out.println("Todos los operarios han terminado su trabajo.");
    }
}
