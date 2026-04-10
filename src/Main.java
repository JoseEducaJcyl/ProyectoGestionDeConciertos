import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("MENU DE OPCIONES");
        System.out.println("----------------");
        System.out.println("1. Gestionar artistas.");
        System.out.println("2. Gestionar conciertos.");
        System.out.println("3. Gestionar entradas.");
        System.out.println("4. Salir");
        int opcion = sc.nextInt();
        sc.nextLine();
        boolean terminado = false;
        while (!terminado) {
            switch (opcion) {
                case 1:
                    menuArtistas(sc);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        }
    }
    public static void menuArtistas(Scanner sc) {
        boolean terminado = false;
        while (!terminado) {

        }
    }
}