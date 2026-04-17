import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:oracle:thin:@//localhost:1521/xe";
        String usuario = "JAVA";
        String contrasenia = "12345";
        Scanner sc = new Scanner(System.in);

        boolean terminado = false;
        while (!terminado) {
            System.out.println("MENU DE OPCIONES");
            System.out.println("----------------");
            System.out.println("1. Gestionar artistas.");
            System.out.println("2. Gestionar conciertos.");
            System.out.println("3. Gestionar entradas.");
            System.out.println("4. Salir");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    MenuArtista.menuArtista(sc,url,usuario,contrasenia);;
                    break;
                case 2:
                    MenuConciertos.menuConciertos(sc,url,usuario,contrasenia);
                    break;
                case 3:
                    MenuEntrada.menuEntradas(sc,url,usuario,contrasenia);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    terminado = true;
                    break;
                default:
                    break;
            }
        }
    }
}