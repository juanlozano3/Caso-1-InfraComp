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
        OperarioInternoProduccion_cinta operarioInternoDepositar = new OperarioInternoProduccion_cinta(depositoProduccion, cintaTransportadora, 1);
        OperarioInternoCinta_distribucion operarioInternoRetirar = new OperarioInternoCinta_distribucion(depositoDistribucion, cintaTransportadora, 2);

        // Inicialización de operarios distribuidores
        OperarioDistribuidor operarioDistribuidorA = new OperarioDistribuidor("A", depositoDistribucion, 1);
        OperarioDistribuidor operarioDistribuidorA2 = new OperarioDistribuidor("A", depositoDistribucion, 2);
        OperarioDistribuidor operarioDistribuidorB = new OperarioDistribuidor("B", depositoDistribucion, 1);
        OperarioDistribuidor operarioDistribuidorB2 = new OperarioDistribuidor("B", depositoDistribucion, 2);

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
        //Estado de todos los Threads
        System.out.println("Operario Productor A 1: " + operarioProductorA.getState());
        System.out.println("Operario Productor A 2: " + operarioProductorA2.getState());
        System.out.println("Operario Productor B 1: " + operarioProductorB.getState());
        System.out.println("Operario Productor B 2: " + operarioProductorB2.getState());
        System.out.println("Operario Interno Depositar: " + operarioInternoDepositar.getState());
        System.out.println("Operario Interno Retirar: " + operarioInternoRetirar.getState());
        System.out.println("Operario Distribuidor A 1: " + operarioDistribuidorA.getState());
        System.out.println("Operario Distribuidor A 2: " + operarioDistribuidorA2.getState());
        System.out.println("Operario Distribuidor B 1: " + operarioDistribuidorB.getState());
        System.out.println("Operario Distribuidor B 2: " + operarioDistribuidorB2.getState());

        // Imprimir el número total de productos producidos
        System.out.println("Número total de productos producidos: " + OperarioProductor.getCantidadProducidos() + " Se incluyen los 4 productos de fin de producción");
        // Imprimir el número total de productos distribuidos
        System.out.println("Número total de productos distribuidos: " + OperarioDistribuidor.getContador());
        System.out.println("Operación finalizada");
    }
}