import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class PrimeNumbersSearchClient implements PrimeChecker {

    public boolean check(BigDecimal number) throws RemoteException {
        BigDecimal i = new BigDecimal (2);
        Scanner keyboard = new Scanner(System.in);
        int a = keyboard.nextInt();
        int b = keyboard.nextInt();
        int c = keyboard.nextInt();
        /* while i less than sqrt */
        int D = b*b - 4 * a * c;
        double x1 = (-b + Math.sqrt(D)) / 2 * a;
        double x2 = (-b - Math.sqrt(D)) / 2 * a;
        System.out.println("x1 = " + x1 + ", x2 = " + x2);
        return true;
    }

    public static void main(String[] args) {
        PrimeNumbersSearchClient client = new PrimeNumbersSearchClient();

        try {
            Registry registry = LocateRegistry.getRegistry(null, 12345);
            ClientRegister server = (ClientRegister)registry.lookup("ClientRegister");

            PrimeChecker stub = (PrimeChecker)UnicastRemoteObject.exportObject(client, 0);
            server.register(stub);

        } catch (Exception e) {
            System.out.println ("Error occured: " + e.getMessage());
            System.exit (1);
        }
    }
}