// Importes necesarios para el programa
import java.sql.*;
import java.util.Scanner;

// Clase Main para la ejecucion del programa principal
public class Main {
    public static void main(String[] args) throws SQLException {
        // Datos para conectarse a la base de datos
        String url = "jdbc:oracle:thin:@//localhost:1521/xe";
        String usuario = "JAVA";
        String contrasenia = "12345";

        // Creacion de un Scanner para leer la opción del usuario
        Scanner sc = new Scanner(System.in);

        // Variable de control para el bucle del menú principal
        boolean terminado = false;

        // Bucle principal que muestra el menú hasta que el usuario decida salir
        while (!terminado) {
            // Mostrar el menú de opciones principal
            System.out.println("MENU DE OPCIONES");
            System.out.println("----------------");
            System.out.println("1. Gestionar artistas.");
            System.out.println("2. Gestionar conciertos.");
            System.out.println("3. Gestionar entradas.");
            System.out.println("4. Salir");

            // Leer la opción seleccionada por el usuario
            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer del scanner

            // Ejecutar la opción correspondiente usando un switch
            switch (opcion) {
                case 1:
                    // Llamar al menú de gestión de artistas
                    MenuArtista.menuArtista(sc, url, usuario, contrasenia);
                    break;
                case 2:
                    // Llamar al menú de gestión de conciertos
                    MenuConciertos.menuConciertos(sc, url, usuario, contrasenia);
                    break;
                case 3:
                    // Llamar al menú de gestión de entradas
                    MenuEntrada.menuEntradas(sc, url, usuario, contrasenia);
                    break;
                case 4:
                    // Salir del programa
                    System.out.println("Saliendo...");
                    terminado = true;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
        sc.close();
    }
}
